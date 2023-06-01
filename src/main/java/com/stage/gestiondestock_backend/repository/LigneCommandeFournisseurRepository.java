package com.stage.gestiondestock_backend.repository;

import com.stage.gestiondestock_backend.model.LigneCommandeFournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LigneCommandeFournisseurRepository extends JpaRepository<LigneCommandeFournisseur, Long> {
    Optional<LigneCommandeFournisseur> findLigneCommandeFournisseurByCode(String code);
}
