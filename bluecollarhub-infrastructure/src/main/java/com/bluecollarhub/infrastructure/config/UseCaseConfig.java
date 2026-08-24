package com.bluecollarhub.infrastructure.config;

import com.bluecollarhub.application.gig.CreateGigUseCase;
import com.bluecollarhub.application.gig.TransitionGigStatusUseCase;
import com.bluecollarhub.application.ports.output.GigRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public CreateGigUseCase createGigUseCase(GigRepository gigRepository) {
        return new CreateGigUseCase(gigRepository);
    }

    @Bean
    public TransitionGigStatusUseCase transitionGigStatusUseCase(GigRepository gigRepository) {
        return new TransitionGigStatusUseCase(gigRepository);
    }
}