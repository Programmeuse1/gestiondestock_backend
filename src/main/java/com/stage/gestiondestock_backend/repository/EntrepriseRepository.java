package com.stage.gestiondestock_backend.repository;

import com.stage.gestiondestock_backend.model.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface EntrepriseRepository  extends JpaRepository<Entreprise, Long>, JpaSpecificationExecutor<Entreprise> {
    Optional<Entreprise> findEntrepriseByNom(String nom);
}