package com.stage.gestiondestock_backend.service;

import com.stage.gestiondestock_backend.Dto.UtilisateurDto;
import java.util.List;

public interface UtilisateurService {

    UtilisateurDto save(UtilisateurDto dto);

    UtilisateurDto findById(Long id);

    UtilisateurDto findByNom(String nom);

    UtilisateurDto findByEmail(String email);

    List<UtilisateurDto> findAll();

    void delete(Long id);

    UtilisateurDto currentUser();
}
