package com.stage.gestiondestock_backend.repository;

import com.stage.gestiondestock_backend.model.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FournisseurRepository extends JpaRepository<Fournisseur, Long> {
    Optional<Fournisseur> findFournisseurByNom(String nom);
}
