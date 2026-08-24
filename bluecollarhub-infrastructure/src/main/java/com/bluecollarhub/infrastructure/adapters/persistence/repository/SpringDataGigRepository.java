package com.bluecollarhub.infrastructure.adapters.persistence.repository;

import com.bluecollarhub.infrastructure.adapters.persistence.entity.GigJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataGigRepository extends JpaRepository<GigJpaEntity, UUID> {
}