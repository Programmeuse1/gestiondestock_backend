package com.stage.gestiondestock_backend.repository;

import com.stage.gestiondestock_backend.model.LigneVente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LigneVenteRepository extends JpaRepository<LigneVente, Long> {
    Optional<LigneVente> findLigneVenteByCode(String code);
}
