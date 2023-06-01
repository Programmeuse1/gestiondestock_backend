package com.stage.gestiondestock_backend.service;

import com.stage.gestiondestock_backend.Dto.FournisseurDto;
import java.util.List;

public interface FournisseurService {

    FournisseurDto save(FournisseurDto dto);

    FournisseurDto findById(Long id);

    FournisseurDto findByNom(String nom);

    List<FournisseurDto> findAll();

    void delete(Long id);
}
