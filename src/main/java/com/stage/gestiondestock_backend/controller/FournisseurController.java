package com.stage.gestiondestock_backend.controller;

import com.stage.gestiondestock_backend.Dto.FournisseurDto;
import com.stage.gestiondestock_backend.controller.Api.FournisseurApi;
import com.stage.gestiondestock_backend.service.FournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class FournisseurController implements FournisseurApi {

    private FournisseurService fournisseurService;
    @Autowired
    public FournisseurController(
            FournisseurService fournisseurService
    ) {
        this.fournisseurService = fournisseurService;
    }

    @Override
    public FournisseurDto save(FournisseurDto dto) { return fournisseurService.save(dto); }

    @Override
    public FournisseurDto findById(Long id) { return fournisseurService.findById(id); }

    @Override
    public FournisseurDto findByNom(String nom) { return fournisseurService.findByNom(nom); }

    @Override
    public List<FournisseurDto> findAll() { return fournisseurService.findAll(); }

    @Override
    public void delete(Long id) {
       fournisseurService.delete(id);
    }
}
