package com.stage.gestiondestock_backend.repository;

import com.stage.gestiondestock_backend.model.CommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface CommandeClientRepository extends JpaRepository<CommandeClient, Long>, JpaSpecificationExecutor<CommandeClient> {
    Optional<CommandeClient> findCommandeClientByCode(String code);
}
