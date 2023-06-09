package com.stage.gestiondestock_backend.controller;

import com.stage.gestiondestock_backend.dto.EntrepriseDto;
import com.stage.gestiondestock_backend.controller.api.EntrepriseApi;
import com.stage.gestiondestock_backend.service.EntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class EntrepriseController implements EntrepriseApi {

    private EntrepriseService entrepriseService;

    @Autowired
    public EntrepriseController(
            EntrepriseService entrepriseService
    ) {
        this.entrepriseService = entrepriseService;
    }

    @Override
    public EntrepriseDto save(EntrepriseDto dto) {
        return entrepriseService.save(dto);}

    @Override
    public EntrepriseDto findById(Long id) {
        return entrepriseService.findById(id);
    }

    @Override
    public EntrepriseDto findByNom(String nom) {return entrepriseService.findByNom(nom);}

    @Override
    public List<EntrepriseDto> findAll() {
        return entrepriseService.findAll();
    }

    @Override
    public void delete(Long id) {
        entrepriseService.delete(id);

    }
}
