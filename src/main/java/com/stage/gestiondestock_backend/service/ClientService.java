package com.stage.gestiondestock_backend.service;

import com.stage.gestiondestock_backend.Dto.ClientDto;
import java.util.List;

public interface ClientService {

    ClientDto save(ClientDto dto);

    ClientDto findById(Long id);

    ClientDto findByNom(String nom);

    List<ClientDto> findAll();

    void delete(Long id);

}
