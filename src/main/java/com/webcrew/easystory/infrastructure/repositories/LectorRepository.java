package com.webcrew.easystory.infrastructure.repositories;

import com.webcrew.easystory.domain.entities.Lector;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LectorRepository extends ClientRepository<Lector> {
}
