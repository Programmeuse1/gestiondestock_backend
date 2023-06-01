package com.stage.gestiondestock_backend.controller.api;

import com.stage.gestiondestock_backend.Dto.ClientDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static  com.stage.gestiondestock_backend.utils.Constants.APP_ROOT;
import java.util.List;

@Api("clientsApi")
public interface ClientApi {

    @ApiOperation(value = "Enregistrer un client", notes="Cette methode permet d'enregistrer ou modifier un client",response =ClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code= 200, message = "L'objet client a ete cree/modifier"),
            @ApiResponse(code= 400, message = "L'objet client n'est pas valide")
    })
    @PostMapping(value = APP_ROOT + "/client/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE)
    ClientDto save(@RequestBody ClientDto dto);

    @ApiOperation(value = "Recherche d'un client par l'ID", notes="Cette methode permet de chercher un client par son ID",response = ClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code= 200, message = "Le client a ete trouve dans la BD"),
            @ApiResponse(code= 400, message = "Aucun client n'existe dans la bd avec l'ID fourni")
    })
    @GetMapping(value = APP_ROOT + "/client/id/{idClient}", produces =  MediaType.APPLICATION_JSON_VALUE)
    ClientDto findById(@PathVariable ("idClient") Long idClient);

    @ApiOperation(value = "Recherche d'un client par NOM", notes="Cette methode permet de chercher un client par son NOM",response = ClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code= 200, message = "Le client a ete trouve dans la BD"),
            @ApiResponse(code= 400, message = "Aucun client n'existe dans la bd avec le NOM fourni")
    })
    @GetMapping(value = APP_ROOT + "/client/nom/{nomClient}", produces =  MediaType.APPLICATION_JSON_VALUE)
    ClientDto findByNom(@PathVariable ("nomClient") String nom);

   @ApiOperation(value = "Renvoi la liste des clients", notes="Cette methode permet de chercher et de renvoyer la liste des clients qui existent" + "dans la BD",responseContainer = "List<ClientDto>")
    @ApiResponses(value = {
            @ApiResponse(code= 200, message = "La liste des clients/ une liste vide"),
    })
   @GetMapping(value = APP_ROOT + "/client/all", produces =  MediaType.APPLICATION_JSON_VALUE)
   List<ClientDto> findAll();

    @ApiOperation(value = "Supprimer un client", notes="Cette methode permet de supprimer un client par son ID",response = ClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code= 200, message = "Le client a ete supprime"),
    })
    @DeleteMapping(value = APP_ROOT + "/client/delete/{idClient}")
    void delete(@PathVariable("idClient") Long id);
}
