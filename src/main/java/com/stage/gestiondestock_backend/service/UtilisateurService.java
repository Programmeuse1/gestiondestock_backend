package com.stage.gestiondestock_backend.service;

import com.stage.gestiondestock_backend.dto.UtilisateurDto;
import com.stage.gestiondestock_backend.service.criteria.UtilisateurCriteria;

import java.util.List;

public interface UtilisateurService {

    UtilisateurDto save(UtilisateurDto dto);

    UtilisateurDto findById(Long id);

    UtilisateurDto findByNom(String nom);

    UtilisateurDto findByEmail(String email);

    List<UtilisateurDto> findAll();

    List<UtilisateurDto> listingUtilisateur(UtilisateurCriteria utilisateurCriteria);

    void delete(Long id);

    UtilisateurDto currentUser();
}
