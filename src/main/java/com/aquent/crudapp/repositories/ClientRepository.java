package com.aquent.crudapp.repositories;

import com.aquent.crudapp.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Operations on the "client" table.
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client getById(Integer clientId);

    Optional<Client> findById(Integer clientId);
}
