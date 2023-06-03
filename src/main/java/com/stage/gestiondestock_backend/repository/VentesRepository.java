package com.stage.gestiondestock_backend.repository;

import com.stage.gestiondestock_backend.model.Ventes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.Instant;
import java.util.Optional;

public interface VentesRepository  extends JpaRepository<Ventes, Long>, JpaSpecificationExecutor<Ventes> {
    Optional<Ventes> findVentesByCode(String code);}
