package com.stage.gestiondestock_backend.repository;

import com.stage.gestiondestock_backend.model.LigneVente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface LigneVenteRepository extends JpaRepository<LigneVente, Long>, JpaSpecificationExecutor<LigneVente> {
    Optional<LigneVente> findLigneVenteByCode(String code);
}
