package com.stage.gestiondestock_backend.repository;

import com.stage.gestiondestock_backend.model.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface FournisseurRepository extends JpaRepository<Fournisseur, Long>, JpaSpecificationExecutor<Fournisseur> {
    Optional<Fournisseur> findFournisseurByNom(String nom);
}
