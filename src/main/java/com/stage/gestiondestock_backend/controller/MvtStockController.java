package com.stage.gestiondestock_backend.controller;

import com.stage.gestiondestock_backend.dto.MvtStockDto;
import com.stage.gestiondestock_backend.controller.api.MvtStockApi;
import com.stage.gestiondestock_backend.service.MvtStockService;
import com.stage.gestiondestock_backend.service.criteria.MvtStockCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MvtStockController implements MvtStockApi {

    private MvtStockService mvtStockService;
    @Autowired
    public MvtStockController(MvtStockService mvtStockService) {
        this.mvtStockService = mvtStockService;
    }

    @Override
    public MvtStockDto save(MvtStockDto dto) {
        return mvtStockService.save(dto);
    }

    @Override
    public MvtStockDto findById(Long id) {
        return mvtStockService.findById(id);
    }

    @Override
    public List<MvtStockDto> findAll() {
        return mvtStockService.findAll();
    }

    @Override
    public void delete(Long id) {
        mvtStockService.delete(id);
    }

    @Override
    public List<MvtStockDto> listingMvtStock(MvtStockCriteria mvtStockCriteria) {
        return mvtStockService.listingMvtStock(mvtStockCriteria);
    }
}
