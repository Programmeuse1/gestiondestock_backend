package com.stage.gestiondestock_backend.repository;

import com.stage.gestiondestock_backend.model.LigneCommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LigneCommandeClientRepository extends JpaRepository<LigneCommandeClient, Long> {
    Optional<LigneCommandeClient> findLigneCommandeClientByCode(String code);
}
