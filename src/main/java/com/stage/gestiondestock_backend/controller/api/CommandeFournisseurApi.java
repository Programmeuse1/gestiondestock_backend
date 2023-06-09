package com.stage.gestiondestock_backend.controller.api;

import com.stage.gestiondestock_backend.dto.CommandeFournisseurDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.stage.gestiondestock_backend.utils.Constants.*;


@Api("commandesfournisseursApi")
public interface CommandeFournisseurApi {

    @ApiOperation(value = "Enregistrer une commande fournisseur", notes="Cette methode permet d'enregistrer ou modifier une commande fournisseur",response = CommandeFournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code= 200, message = "L'objet commande fournisseur a ete cree/modifier"),
            @ApiResponse(code= 400, message = "L'objet commande fournisseur n'est pas valide")
    })
    @PostMapping(value = APP_ROOT + "/commandesfournisseurs/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE)
    CommandeFournisseurDto save(@RequestBody CommandeFournisseurDto dto);

   @ApiOperation(value = "Recherche d'une commande fournisseur par l'ID", notes="Cette methode permet de chercher une commande fournisseur par son ID",response = CommandeFournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code= 200, message = "La commande fournisseur a ete trouve dans la BD"),
            @ApiResponse(code= 400, message = "Aucune commande fournisseur n'existe dans la bd avec l'ID fourni")
    })
   @GetMapping(value = APP_ROOT + "/commandesfournisseurs/id/{idCommandeFournisseur}", produces =  MediaType.APPLICATION_JSON_VALUE)
   CommandeFournisseurDto findById(@PathVariable("idCommandeFournisseur") Long idCommandeFournisseur);

    @ApiOperation(value = "Recherche d'une commande fournisseur par CODE", notes="Cette methode permet de chercher une commande fournisseur par son CODE",response =CommandeFournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code= 200, message = "La commande fournisseur a ete trouve dans la BD"),
            @ApiResponse(code= 400, message = "Aucune commande fournisseur n'existe dans la bd avec le CODE fourni")
    })
    @GetMapping(value = APP_ROOT + "/commandesfournisseurs/code/{codeCommandeFournisseur}", produces =  MediaType.APPLICATION_JSON_VALUE)
    CommandeFournisseurDto findByCode(@PathVariable ("codeCommandeFournisseur") String code);

    @ApiOperation(value = "Renvoi la liste des commandes fournisseurs", notes="Cette methode permet de chercher et de renvoyer la liste des commandes fournisseurs qui existent" + "dans la BD",responseContainer = "List<CommandeFournisseurDto>")
    @ApiResponses(value = {
            @ApiResponse(code= 200, message = "La liste des commandes fournisseurs/ une liste vide"),
    })
    @GetMapping(value = APP_ROOT + "/commandesfournisseurs/all", produces =  MediaType.APPLICATION_JSON_VALUE)
    List<CommandeFournisseurDto> findAll();

   @ApiOperation(value = "Supprimer une commande fournisseur", notes="Cette methode permet de supprimer une commande fournisseur par son ID",response = CommandeFournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code= 200, message = "La commande fournisseur a ete supprime"),
    })
   @DeleteMapping(value = APP_ROOT + "/commandesfournisseurs/delete/{idCommandeFournisseur}")
   void delete(@PathVariable("idCommandeFournisseur") Long id);
}

