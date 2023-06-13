package com.stage.gestiondestock_backend.service;

import com.stage.gestiondestock_backend.dto.MvtStockDto;
import com.stage.gestiondestock_backend.service.criteria.MvtStockCriteria;

import java.util.List;

public interface MvtStockService {

    MvtStockDto save(MvtStockDto dto);

    MvtStockDto findById(Long id);

    List<MvtStockDto> findAll();

    List<MvtStockDto> listingMvtStock(MvtStockCriteria mvtStockCriteria);

    void delete(Long id);
}
