package com.webcrew.easystory.command.infrastructure.repositories;

import com.webcrew.easystory.command.domain.entities.Lector;
import org.springframework.stereotype.Repository;

@Repository
public interface LectorRepository extends ClientRepository<Lector> {
}
