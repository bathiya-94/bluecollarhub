package com.bluecollarhub.application.gig.dto;

import com.bluecollarhub.domain.gig.GigStatus;

import java.util.UUID;

public record TransitionGigCommand(
        UUID gigId,
        GigStatus targetStatus,
        UUID workerId // Optional, required for ASSIGNED state
) {}