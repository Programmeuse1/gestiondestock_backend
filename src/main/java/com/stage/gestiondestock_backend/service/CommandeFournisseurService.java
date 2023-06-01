package com.stage.gestiondestock_backend.service;

import com.stage.gestiondestock_backend.Dto.CommandeFournisseurDto;
import java.util.List;

public interface CommandeFournisseurService {

    CommandeFournisseurDto save(CommandeFournisseurDto dto);

    CommandeFournisseurDto findById(Long id);

    CommandeFournisseurDto findByCode(String code);

    List<CommandeFournisseurDto> findAll();

    void delete(Long id);
}
