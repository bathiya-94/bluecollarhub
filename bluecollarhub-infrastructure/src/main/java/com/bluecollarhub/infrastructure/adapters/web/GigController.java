package com.bluecollarhub.infrastructure.adapters.web;

import com.bluecollarhub.application.gig.CreateGigUseCase;
import com.bluecollarhub.application.gig.TransitionGigStatusUseCase;
import com.bluecollarhub.application.gig.dto.CreateGigCommand;
import com.bluecollarhub.application.gig.dto.TransitionGigCommand;
import com.bluecollarhub.domain.gig.GigRequest;
import com.bluecollarhub.infrastructure.adapters.web.dto.CreateGigWebRequest;
import com.bluecollarhub.infrastructure.adapters.web.dto.GigWebResponse;
import com.bluecollarhub.infrastructure.adapters.web.dto.TransitionStatusWebRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/gigs")
public class GigController {

    private final CreateGigUseCase createGigUseCase;
    private final TransitionGigStatusUseCase transitionGigStatusUseCase;

    // Spring automatically injects the beans we defined in UseCaseConfig
    public GigController(CreateGigUseCase createGigUseCase, TransitionGigStatusUseCase transitionGigStatusUseCase) {
        this.createGigUseCase = createGigUseCase;
        this.transitionGigStatusUseCase = transitionGigStatusUseCase;
    }

    @PostMapping
    public ResponseEntity<GigWebResponse> createGig(@RequestBody CreateGigWebRequest request) {
        // Map Web Request -> Application Command
        CreateGigCommand command = new CreateGigCommand(
            request.clientId(),
            request.requiredSkill(),
            request.description()
        );

        // Execute Use Case
        GigRequest createdGig = createGigUseCase.execute(command);

        // Map Domain Result -> Web Response
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(GigWebResponse.fromDomain(createdGig));
    }

    @PatchMapping("/{gigId}/status")
    public ResponseEntity<GigWebResponse> transitionStatus(
            @PathVariable UUID gigId,
            @RequestBody TransitionStatusWebRequest request) {
            
        // Map Web Request -> Application Command
        TransitionGigCommand command = new TransitionGigCommand(
            gigId,
            request.targetStatus(),
            request.workerId()
        );

        // Execute Use Case
        GigRequest updatedGig = transitionGigStatusUseCase.execute(command);

        return ResponseEntity.ok(GigWebResponse.fromDomain(updatedGig));
    }
}