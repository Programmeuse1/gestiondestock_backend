package com.stage.gestiondestock_backend.controller.api;

import com.stage.gestiondestock_backend.Dto.UtilisateurDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.stage.gestiondestock_backend.utils.Constants.APP_ROOT;

@Api("utilisateurApi")
public interface UtilisateurApi {

    @ApiOperation(value = "Enregistrer un utilisateur", notes="Cette methode permet d'enregistrer ou modifier un utilisateur",response = UtilisateurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code= 200, message = "L'objet utilisateur a ete cree/modifier"),
            @ApiResponse(code= 400, message = "L'objet utilisateur n'est pas valide")
    })
    @PostMapping(value = APP_ROOT + "/utilisateur/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE)
    UtilisateurDto save(@RequestBody UtilisateurDto dto);


    @ApiOperation(value = "Recherche d'un utilisateur par l'ID", notes="Cette methode permet de chercher un utilisateur par son ID",response = UtilisateurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code= 200, message = "L'utilisateur a ete trouve dans la BD"),
            @ApiResponse(code= 400, message = "Aucun utilisateur n'existe dans la bd avec l'ID fourni")
    })
    @GetMapping(value = APP_ROOT + "/utilisateur/id/{idUtilisateur}", produces =  MediaType.APPLICATION_JSON_VALUE)
    UtilisateurDto findById(@PathVariable("idUtilisateur") Long idUtilisateur);


    @ApiOperation(value = "Recherche d'un utilisateur par NOM", notes="Cette methode permet de chercher un utilisateur par son NOM",response =UtilisateurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code= 200, message = "L'utilisateur a ete trouve dans la BD"),
            @ApiResponse(code= 400, message = "Aucun utilisateur n'existe dans la bd avec le NOM fourni")
    })
    @GetMapping(value = APP_ROOT + "/utilisateur/nom/{nomUtilisateur}", produces =  MediaType.APPLICATION_JSON_VALUE)
    UtilisateurDto findByNom(@PathVariable ("nomUtilisateur") String nom);


    @ApiOperation(value = "Renvoi la liste des utilisateurs", notes="Cette methode permet de chercher et de renvoyer la liste des futilisateurs qui existent" + "dans la BD")
    @ApiResponses(value = {
            @ApiResponse(code= 200, message = "La liste des utilisateurs/ une liste vide"),
    })
    @GetMapping(value = APP_ROOT + "/utilisateur/all", produces =  MediaType.APPLICATION_JSON_VALUE)
    List<UtilisateurDto> findAll();


    @ApiOperation(value = "Supprimer un utilisateur", notes="Cette methode permet de supprimer un utilisateur par son ID")
    @ApiResponses(value = {
            @ApiResponse(code= 200, message = "L'utilisateur a ete supprime"),
    })
    @DeleteMapping(value = APP_ROOT + "/utilisateur/delete/{idUtilisateur}")
    void delete(@PathVariable("idUtilisateur") Long id);

    @ApiOperation(value = "Retourne l'utilisateur connecté",
            notes = "Cette méthode permet de retourner les informations de l'utilisateur connecté", response = UtilisateurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Informations de l'utilisateur retournées avec succès"),
            @ApiResponse(code = 404, message = "L'utilisateur n'a pas été trouvé")
    })
    @GetMapping(value = APP_ROOT +"/utilisateur/currentUser", produces = MediaType.APPLICATION_JSON_VALUE)
    UtilisateurDto currentUser();
}
