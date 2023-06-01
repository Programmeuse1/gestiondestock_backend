package com.stage.gestiondestock_backend.repository;

import com.stage.gestiondestock_backend.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository  extends JpaRepository<Client, Long> {
    Optional<Client> findClientByNom(String nom);
}
