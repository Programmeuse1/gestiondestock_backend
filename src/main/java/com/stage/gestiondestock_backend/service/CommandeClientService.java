package com.stage.gestiondestock_backend.service;

import com.stage.gestiondestock_backend.bean.CommandeClientUpdate;
import com.stage.gestiondestock_backend.dto.CommandeClientDto;
import com.stage.gestiondestock_backend.service.criteria.CommandeClientCriteria;

import java.util.List;

public interface CommandeClientService {

   CommandeClientDto save(CommandeClientUpdate dto);

    CommandeClientDto findById(Long id);

    CommandeClientDto findByCode(String code);

    List<CommandeClientDto> findAll();

    List<CommandeClientDto> findCommandeClientListByFilter(CommandeClientCriteria commandeClientCriteria);

    void delete(Long id);
}
