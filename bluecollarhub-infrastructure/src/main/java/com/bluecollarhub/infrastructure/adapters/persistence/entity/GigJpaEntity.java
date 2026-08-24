package com.bluecollarhub.infrastructure.adapters.persistence.entity;

import com.bluecollarhub.domain.gig.GigStatus;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "gigs")
public class GigJpaEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private UUID clientId;

    private UUID assignedWorkerId;

    @Column(nullable = false)
    private String requiredSkill;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GigStatus status;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    public GigJpaEntity() {} // JPA default constructor

    public GigJpaEntity(UUID id, UUID clientId, UUID assignedWorkerId, String requiredSkill, String description, GigStatus status, Instant createdAt) {
        this.id = id;
        this.clientId = clientId;
        this.assignedWorkerId = assignedWorkerId;
        this.requiredSkill = requiredSkill;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getClientId() { return clientId; }
    public void setClientId(UUID clientId) { this.clientId = clientId; }
    public UUID getAssignedWorkerId() { return assignedWorkerId; }
    public void setAssignedWorkerId(UUID assignedWorkerId) { this.assignedWorkerId = assignedWorkerId; }
    public String getRequiredSkill() { return requiredSkill; }
    public void setRequiredSkill(String requiredSkill) { this.requiredSkill = requiredSkill; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public GigStatus getStatus() { return status; }
    public void setStatus(GigStatus status) { this.status = status; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}