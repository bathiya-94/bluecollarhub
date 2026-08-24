package com.bluecollarhub.application.gig.dto;

import java.util.UUID;

public record CreateGigCommand(
        UUID clientId,
        String requiredSkill,
        String description
) {}