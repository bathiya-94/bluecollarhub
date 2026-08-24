package com.bluecollarhub.infrastructure.adapters.web.dto;

import java.util.UUID;

public record CreateGigWebRequest(
        UUID clientId,
        String requiredSkill,
        String description
) {}