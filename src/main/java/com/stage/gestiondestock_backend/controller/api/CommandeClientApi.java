package com.stage.gestiondestock_backend.controller.api;

import com.stage.gestiondestock_backend.bean.CommandeClientUpdate;
import com.stage.gestiondestock_backend.dto.CommandeClientDto;
import com.stage.gestiondestock_backend.service.criteria.CommandeClientCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static com.stage.gestiondestock_backend.utils.Constants.APP_ROOT;

@Api("commandesclientsApi")
public interface CommandeClientApi {


    @PostMapping(APP_ROOT +"/commandesclients/create")
    ResponseEntity<CommandeClientDto> save(@RequestBody CommandeClientUpdate dto);

    @GetMapping(APP_ROOT + "/commandesclients/findbyid/{id}")
    ResponseEntity<CommandeClientDto> findById(@PathVariable("id") Long id);

    @GetMapping(APP_ROOT + "/commandesclients//findbycode/{codeCommandeClient}")
    ResponseEntity<CommandeClientDto> findByCode(@PathVariable("codeCommandeClient") String code);

    @GetMapping(APP_ROOT + "/commandesclients/all")
    ResponseEntity<List<CommandeClientDto>> findAll();

    //Debut du listing
    @ApiOperation(value = "Renvoi la liste des commande des clients en fonction des critères de recherche",
            notes="Cette methode permet de chercher et de renvoyer la liste des commande des clients qui existent" +
                    "dans la BD", responseContainer = "List<CommandeClientDto>")
    @ApiResponses(value = {
            @ApiResponse(code= 200, message = "La liste des commandeClient/ une liste vide"),
    })
    @PostMapping(value = APP_ROOT + "/commandesclients/listingCommandeClient", produces =  MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    List<CommandeClientDto> listingCommmandeClient(@RequestBody CommandeClientCriteria commandeClientCriteria);

    @DeleteMapping(APP_ROOT + "/commandesclients/delete/{idCommandeClient}")
    ResponseEntity<CommandeClientDto> delete(@PathVariable("idCommandeClient") Long id);

}
