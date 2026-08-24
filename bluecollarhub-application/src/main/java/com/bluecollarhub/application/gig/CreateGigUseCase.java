package com.bluecollarhub.application.gig;

import com.bluecollarhub.application.gig.dto.CreateGigCommand;
import com.bluecollarhub.application.ports.output.GigRepository;
import com.bluecollarhub.domain.gig.ClientId;
import com.bluecollarhub.domain.gig.GigRequest;

import java.util.Objects;

public class CreateGigUseCase {

    private final GigRepository gigRepository;

    public CreateGigUseCase(GigRepository gigRepository) {
        this.gigRepository = Objects.requireNonNull(gigRepository, "GigRepository cannot be null");
    }

    public GigRequest execute(CreateGigCommand command) {
        ClientId clientId = new ClientId(command.clientId());

        GigRequest newGig = GigRequest.create(
                clientId,
                command.requiredSkill(),
                command.description()
        );

        return gigRepository.save(newGig);
    }
}