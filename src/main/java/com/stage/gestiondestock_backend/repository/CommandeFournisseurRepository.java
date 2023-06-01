package com.stage.gestiondestock_backend.repository;

import com.stage.gestiondestock_backend.model.CommandeFournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommandeFournisseurRepository extends JpaRepository<CommandeFournisseur, Long> {
    Optional<CommandeFournisseur> findCommandeFournisseurByCode(String code);
}
