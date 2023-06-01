package com.stage.gestiondestock_backend.service;

import com.stage.gestiondestock_backend.Dto.CommandeClientDto;
import java.util.List;

public interface CommandeClientService {

   CommandeClientDto save(CommandeClientDto dto);

    CommandeClientDto findById(Long id);

    CommandeClientDto findByCode(String code);

    List<CommandeClientDto> findAll();

    void delete(Long id);
}
