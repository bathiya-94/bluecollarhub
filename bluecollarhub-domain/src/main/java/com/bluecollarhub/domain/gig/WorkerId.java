package com.bluecollarhub.domain.gig;

import java.util.Objects;
import java.util.UUID;

public record WorkerId(UUID value) {
    public WorkerId {
        Objects.requireNonNull(value, "WorkerId value cannot be null");
    }
}