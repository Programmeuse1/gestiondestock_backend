package com.stage.gestiondestock_backend.repository;

import com.stage.gestiondestock_backend.model.LigneCommandeFournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface LigneCommandeFournisseurRepository extends JpaRepository<LigneCommandeFournisseur, Long>, JpaSpecificationExecutor<LigneCommandeFournisseur> {
    Optional<LigneCommandeFournisseur> findLigneCommandeFournisseurByCode(String code);
}
