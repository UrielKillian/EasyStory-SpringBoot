package com.webcrew.easystory.infraestructureRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.webcrew.easystory.domain.entities.Client;


import java.util.Optional;
import java.util.UUID;
@Repository
public interface ClientInfo<T extends Client> extends JpaRepository<T, UUID> {

    Optional<Client> findByIdValue(String Id);


}
