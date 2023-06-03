package com.stage.gestiondestock_backend.service;

import com.stage.gestiondestock_backend.dto.MvtStockDto;
import java.util.List;

public interface MvtStockService {

    MvtStockDto save(MvtStockDto dto);

    MvtStockDto findById(Long id);

    List<MvtStockDto> findAll();

    void delete(Long id);
}
