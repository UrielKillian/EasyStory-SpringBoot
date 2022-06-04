package com.webcrew.easystory.command.infrastructure.repositories;

import java.util.Optional;
import java.util.UUID;

import com.webcrew.easystory.command.domain.entities.Client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository<T extends Client> extends JpaRepository<T, UUID> {
    Optional<T>findByEmail(String email);
}
