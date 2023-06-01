package com.stage.gestiondestock_backend.repository;

import com.stage.gestiondestock_backend.model.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EntrepriseRepository  extends JpaRepository<Entreprise, Long> {
    Optional<Entreprise> findEntrepriseByNom(String nom);
}