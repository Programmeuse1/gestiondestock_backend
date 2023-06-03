package com.stage.gestiondestock_backend.service;

import com.stage.gestiondestock_backend.Dto.FournisseurDto;
import com.stage.gestiondestock_backend.service.criteria.FournisseurCriteria;
import java.util.List;

public interface FournisseurService {

    FournisseurDto save(FournisseurDto dto);

    FournisseurDto findById(Long id);

    FournisseurDto findByNom(String nom);

    List<FournisseurDto> findAll();

    List<FournisseurDto> listingFournisseur(FournisseurCriteria fournisseurCriteria);

    void delete(Long id);
}
