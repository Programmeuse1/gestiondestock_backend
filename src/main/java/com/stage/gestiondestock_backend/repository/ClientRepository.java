package com.stage.gestiondestock_backend.repository;

import com.stage.gestiondestock_backend.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ClientRepository  extends JpaRepository<Client, Long>, JpaSpecificationExecutor<Client> {
    Optional<Client> findClientByNom(String nom);
}
