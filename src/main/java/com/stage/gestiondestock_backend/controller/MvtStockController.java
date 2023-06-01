package com.stage.gestiondestock_backend.controller;

import com.stage.gestiondestock_backend.Dto.MvtStockDto;
import com.stage.gestiondestock_backend.controller.Api.MvtStockApi;
import com.stage.gestiondestock_backend.service.MvtStockService;
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
}
