package com.stage.gestiondestock_backend.repository;

import com.stage.gestiondestock_backend.model.Ventes;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.Instant;
import java.util.Optional;

public interface VentesRepository  extends JpaRepository<Ventes, Long> {
    Optional<Ventes> findVentesByCode(String code);}
