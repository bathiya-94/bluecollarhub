package com.bluecollarhub.infrastructure.adapters.persistence;

import com.bluecollarhub.application.ports.output.GigRepository;
import com.bluecollarhub.domain.gig.*;
import com.bluecollarhub.infrastructure.adapters.persistence.entity.GigJpaEntity;
import com.bluecollarhub.infrastructure.adapters.persistence.repository.SpringDataGigRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GigRepositoryAdapter implements GigRepository {

    private final SpringDataGigRepository repository;

    public GigRepositoryAdapter(SpringDataGigRepository repository) {
        this.repository = repository;
    }

    @Override
    public GigRequest save(GigRequest gigRequest) {
        GigJpaEntity entity = toEntity(gigRequest);
        GigJpaEntity savedEntity = repository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public Optional<GigRequest> findById(GigId id) {
        return repository.findById(id.value())
                .map(this::toDomain);
    }

    // Mapping logic: Domain -> JPA Entity
    private GigJpaEntity toEntity(GigRequest domain) {
        return new GigJpaEntity(
                domain.getId().value(),
                domain.getClientId().value(),
                domain.getAssignedWorkerId() != null ? domain.getAssignedWorkerId().value() : null,
                domain.getRequiredSkill(),
                domain.getDescription(),
                domain.getStatus(),
                domain.getCreatedAt()
        );
    }

    // Mapping logic: JPA Entity -> Domain Aggregate (rehydration)
    private GigRequest toDomain(GigJpaEntity entity) {
        return GigRequest.rehydrate(
                new GigId(entity.getId()),
                new ClientId(entity.getClientId()),
                entity.getAssignedWorkerId() != null ? new WorkerId(entity.getAssignedWorkerId()) : null,
                entity.getRequiredSkill(),
                entity.getDescription(),
                entity.getStatus(),
                entity.getCreatedAt()
        );
    }
}