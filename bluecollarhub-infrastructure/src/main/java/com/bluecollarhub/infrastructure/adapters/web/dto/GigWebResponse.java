package com.bluecollarhub.infrastructure.adapters.web.dto;

import com.bluecollarhub.domain.gig.GigRequest;

import java.time.Instant;
import java.util.UUID;

public record GigWebResponse(
    UUID id,
    UUID clientId,
    UUID assignedWorkerId,
    String requiredSkill,
    String description,
    String status,
    Instant createdAt
) {
    // Helper method to map from the Domain aggregate to the Web Response
    public static GigWebResponse fromDomain(GigRequest gig) {
        return new GigWebResponse(
            gig.getId().value(),
            gig.getClientId().value(),
            gig.getAssignedWorkerId() != null ? gig.getAssignedWorkerId().value() : null,
            gig.getRequiredSkill(),
            gig.getDescription(),
            gig.getStatus().name(),
            gig.getCreatedAt()
        );
    }
}