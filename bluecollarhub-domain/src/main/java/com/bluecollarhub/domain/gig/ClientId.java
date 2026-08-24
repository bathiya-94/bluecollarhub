package com.bluecollarhub.domain.gig;

import java.util.Objects;
import java.util.UUID;

public record ClientId (UUID value) {
    public ClientId {
        Objects.requireNonNull(value, "value is null");
    }
}
