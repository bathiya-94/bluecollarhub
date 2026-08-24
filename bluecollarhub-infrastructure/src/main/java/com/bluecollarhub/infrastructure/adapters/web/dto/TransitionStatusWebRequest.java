package com.bluecollarhub.infrastructure.adapters.web.dto;

import com.bluecollarhub.domain.gig.GigStatus;
import java.util.UUID;

public record TransitionStatusWebRequest(
    GigStatus targetStatus, 
    UUID workerId // Optional, only needed when assigning
) {}