package com.stage.gestiondestock_backend.repository;

import com.stage.gestiondestock_backend.model.MvtStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface MvtStockRepository extends JpaRepository<MvtStock, Long>, JpaSpecificationExecutor<MvtStock> {
}
