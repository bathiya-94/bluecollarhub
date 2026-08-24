package com.bluecollarhub.application.ports.output;

import com.bluecollarhub.domain.gig.GigId;
import com.bluecollarhub.domain.gig.GigRequest;

import java.util.Optional;

public interface GigRepository {
    GigRequest save(GigRequest gigRequest);
    Optional<GigRequest> findById(GigId id);
}