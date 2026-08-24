package com.bluecollarhub.application.gig;

import com.bluecollarhub.application.gig.dto.TransitionGigCommand;
import com.bluecollarhub.application.ports.output.GigRepository;
import com.bluecollarhub.domain.gig.GigId;
import com.bluecollarhub.domain.gig.GigRequest;
import com.bluecollarhub.domain.gig.WorkerId;

import java.util.Objects;

public class TransitionGigStatusUseCase {

    private final GigRepository gigRepository;

    public TransitionGigStatusUseCase(GigRepository gigRepository) {
        this.gigRepository = Objects.requireNonNull(gigRepository, "GigRepository cannot be null");
    }

    public GigRequest execute(TransitionGigCommand command) {
        GigId gigId = new GigId(command.gigId());

        GigRequest gig = gigRepository.findById(gigId)
                .orElseThrow(() -> new IllegalArgumentException("Gig not found with ID: " + command.gigId()));

        WorkerId workerId = command.workerId() != null ? new WorkerId(command.workerId()) : null;

        // Domain State Machine Transition
        gig.transitionTo(command.targetStatus(), workerId);

        return gigRepository.save(gig);
    }
}