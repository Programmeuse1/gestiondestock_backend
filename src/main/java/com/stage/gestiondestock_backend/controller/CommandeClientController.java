package com.stage.gestiondestock_backend.controller;

import com.stage.gestiondestock_backend.bean.CommandeClientUpdate;
import com.stage.gestiondestock_backend.dto.CommandeClientDto;
import com.stage.gestiondestock_backend.controller.api.CommandeClientApi;
import com.stage.gestiondestock_backend.service.CommandeClientService;
import com.stage.gestiondestock_backend.service.criteria.CommandeClientCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class CommandeClientController implements CommandeClientApi {

    private CommandeClientService commandeClientService;
    @Autowired
    public CommandeClientController(CommandeClientService commandeClientService) {
        this.commandeClientService = commandeClientService;
    }

    @Override
    public ResponseEntity<CommandeClientDto> save(CommandeClientUpdate dto) {
        return ResponseEntity.ok(commandeClientService.save(dto));
    }

    @Override
    public ResponseEntity<CommandeClientDto> findById(Long id) {
        System.out.println("\nid: "+id+"\n");
        return  ResponseEntity.ok(commandeClientService.findById(id));
    }

    @Override
    public ResponseEntity<CommandeClientDto> findByCode(String code) {
        return  ResponseEntity.ok(commandeClientService.findByCode(code));
    }

    @Override
    public ResponseEntity<List<CommandeClientDto>> findAll() {
        return  ResponseEntity.ok(commandeClientService.findAll());
    }

    @Override
    public List<CommandeClientDto> listingCommmandeClient(CommandeClientCriteria commandeClientCriteria) {
        return commandeClientService.findCommandeClientListByFilter(commandeClientCriteria);
    }

    @Override
    public ResponseEntity<CommandeClientDto> delete(Long id) {
        commandeClientService.delete(id);
        return ResponseEntity.ok().build();
    }
}
