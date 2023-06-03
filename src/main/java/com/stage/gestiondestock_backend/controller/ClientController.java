package com.stage.gestiondestock_backend.controller;

import com.stage.gestiondestock_backend.Dto.ClientDto;
import com.stage.gestiondestock_backend.controller.api.ClientApi;
import com.stage.gestiondestock_backend.service.ClientService;
import com.stage.gestiondestock_backend.service.criteria.ClientCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class ClientController implements ClientApi{

    private ClientService clientService;

    @Autowired
    public ClientController(
            ClientService clientService
    ) {
        this.clientService = clientService;
    }

    @Override
    public ClientDto save(ClientDto dto) {
        return clientService.save(dto);
    }

    @Override
    public ClientDto findById(Long id) {
        return clientService.findById(id);
    }

    @Override
    public ClientDto findByNom(String nom) {
        return clientService.findByNom(nom);
    }

    @Override
    public List<ClientDto> findAll() {
        return clientService.findAll();
    }

    @Override
    public void delete(Long id) {
        clientService.delete(id);

    }

    @Override
    public List<ClientDto> listingClient(ClientCriteria clientCriteria) {
        return clientService.listingClient(clientCriteria);
    }
}
