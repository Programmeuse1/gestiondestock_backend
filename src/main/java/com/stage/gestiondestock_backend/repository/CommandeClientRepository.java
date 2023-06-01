package com.stage.gestiondestock_backend.repository;

import com.stage.gestiondestock_backend.model.CommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommandeClientRepository extends JpaRepository<CommandeClient, Long> {
    Optional<CommandeClient> findCommandeClientByCode(String code);
}
