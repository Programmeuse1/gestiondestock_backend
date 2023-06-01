package com.stage.gestiondestock_backend.service;

import com.stage.gestiondestock_backend.Dto.EntrepriseDto;

import java.util.List;

public interface EntrepriseService {

    EntrepriseDto save(EntrepriseDto dto);

    EntrepriseDto findById(Long id);

    EntrepriseDto findByNom(String nom);

    List<EntrepriseDto> findAll();

    void delete(Long id);

}
