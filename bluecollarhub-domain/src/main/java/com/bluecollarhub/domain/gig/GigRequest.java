package com.bluecollarhub.domain.gig;

import java.time.Instant;
import java.util.Objects;

public class GigRequest {

    private final GigId id;
    private final ClientId clientId;
    private final String requiredSkill;
    private final String description;

    private WorkerId assignedWorkerId;
    private GigStatus status;
    private final Instant createdAt;

    public GigRequest(GigId id, ClientId clientId, String requiredSkill, String description) {
        this.id = Objects.requireNonNull(id, "GigId cannot be null");
        this.clientId = Objects.requireNonNull(clientId, "ClientId cannot be null");

        if (requiredSkill == null || requiredSkill.isBlank()) {
            throw new IllegalArgumentException("Required skill cannot be empty");
        }

        this.requiredSkill = requiredSkill;
        this.description = description;
        this.status = GigStatus.REQUESTED;
        this.createdAt = Instant.now();
    }

    // Factory method for creating new requests
    public static GigRequest create(ClientId clientId, String requiredSkill, String description) {
        return new GigRequest(GigId.generate(), clientId, requiredSkill, description);
    }


    // Re-hydration factory for persistence adapters (mapping from DB)
    public static GigRequest rehydrate(GigId id, ClientId clientId, WorkerId workerId,
                                       String skill, String description,
                                       GigStatus status, Instant createdAt) {
        GigRequest request = new GigRequest(id, clientId, skill, description);
        request.assignedWorkerId = workerId;
        request.status = status;
        return request;
    }

    /**
     * Java 25 Pattern Matching State Machine Logic
     */
    public void transitionTo(GigStatus targetStatus, WorkerId workerId) {
        this.status = switch (this.status) {
            case REQUESTED -> switch (targetStatus) {
                case MATCHING -> GigStatus.MATCHING;
                case CANCELLED -> GigStatus.CANCELLED;
                default -> throw invalidTransition(targetStatus);
            };
            case MATCHING -> switch (targetStatus) {
                case ASSIGNED -> {
                    if (workerId == null) {
                        throw new IllegalArgumentException("WorkerId is required to transition to ASSIGNED state");
                    }
                    this.assignedWorkerId = workerId;
                    yield GigStatus.ASSIGNED;
                }
                case CANCELLED -> GigStatus.CANCELLED;
                default -> throw invalidTransition(targetStatus);
            };
            case ASSIGNED -> switch (targetStatus) {
                case EN_ROUTE -> GigStatus.EN_ROUTE;
                case CANCELLED -> GigStatus.CANCELLED;
                default -> throw invalidTransition(targetStatus);
            };
            case EN_ROUTE -> switch (targetStatus) {
                case IN_PROGRESS -> GigStatus.IN_PROGRESS;
                case CANCELLED -> GigStatus.CANCELLED;
                default -> throw invalidTransition(targetStatus);
            };
            case IN_PROGRESS -> switch (targetStatus) {
                case COMPLETED -> GigStatus.COMPLETED;
                default -> throw invalidTransition(targetStatus);
            };
            case COMPLETED, CANCELLED ->
                    throw new IllegalStateException("Terminal state reached. Cannot transition from " + this.status);
        };
    }

    private IllegalStateException invalidTransition(GigStatus target) {
        return new IllegalStateException("Invalid state transition from " + this.status + " to " + target);
    }

    // Encapsulated Getters
    public GigId getId() { return id; }
    public ClientId getClientId() { return clientId; }
    public WorkerId getAssignedWorkerId() { return assignedWorkerId; }
    public String getRequiredSkill() { return requiredSkill; }
    public String getDescription() { return description; }
    public GigStatus getStatus() { return status; }
    public Instant getCreatedAt() { return createdAt; }
}
