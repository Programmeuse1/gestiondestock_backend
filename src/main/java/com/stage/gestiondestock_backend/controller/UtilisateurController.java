package com.stage.gestiondestock_backend.controller;

import com.stage.gestiondestock_backend.Dto.UtilisateurDto;
import com.stage.gestiondestock_backend.controller.Api.UtilisateurApi;
import com.stage.gestiondestock_backend.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class UtilisateurController implements UtilisateurApi {

    private UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurController(
            UtilisateurService utilisateurService
    ) {
        this.utilisateurService = utilisateurService;
    }

    @Override
    public UtilisateurDto save(UtilisateurDto dto) {
        System.out.println("\nutilisateur dto: "+dto.toString()+"\n");
        return utilisateurService.save(dto);}

    @Override
    public UtilisateurDto findById(Long id) { return utilisateurService.findById(id); }

    @Override
    public UtilisateurDto findByNom(String nom) {return utilisateurService.findByNom(nom);}

    @Override
    public List<UtilisateurDto> findAll() { return utilisateurService.findAll(); }

    @Override
    public void delete(Long id) {
       utilisateurService.delete(id);

    }

    @Override
    public UtilisateurDto currentUser() {
        return utilisateurService.currentUser();
    }
}
