package com.bluecollarhub.domain.gig;

import java.util.Objects;
import java.util.UUID;

public record GigId(UUID value) {
    public GigId {
        Objects.requireNonNull(value, "GigId value cannot be null");
    }

    public static GigId generate() {
        return new GigId(UUID.randomUUID());
    }
}