package com.stage.gestiondestock_backend.repository;

import com.stage.gestiondestock_backend.model.LigneCommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface LigneCommandeClientRepository extends JpaRepository<LigneCommandeClient, Long>, JpaSpecificationExecutor<LigneCommandeClient> {
    Optional<LigneCommandeClient> findLigneCommandeClientByCode(String code);

    void deleteAllByCommandeClientId(Long id);
}
