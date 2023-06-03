package com.stage.gestiondestock_backend.controller.api;

import com.stage.gestiondestock_backend.Dto.FournisseurDto;
import com.stage.gestiondestock_backend.service.criteria.FournisseurCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static com.stage.gestiondestock_backend.utils.Constants.APP_ROOT;

@Api("fournisseurApi")
public interface FournisseurApi {

    @ApiOperation(value = "Enregistrer un fournisseur", notes="Cette methode permet d'enregistrer ou modifier un fournisseur",response = FournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code= 200, message = "L'objet fournisseur a ete cree/modifier"),
            @ApiResponse(code= 400, message = "L'objet fournisseur n'est pas valide")
    })
    @PostMapping(value = APP_ROOT + "/fournisseur/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE)
    FournisseurDto save(@RequestBody FournisseurDto dto);

    @ApiOperation(value = "Recherche d'un fournisseur par l'ID", notes="Cette methode permet de chercher un fournisseur par son ID",response = FournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code= 200, message = "Le fournisseur a ete trouve dans la BD"),
            @ApiResponse(code= 400, message = "Aucun fournisseur n'existe dans la bd avec l'ID fourni")
    })
    @GetMapping(value = APP_ROOT + "/fournisseur/id/{idFournisseur}", produces =  MediaType.APPLICATION_JSON_VALUE)
    FournisseurDto findById(@PathVariable("idFournisseur") Long idFournisseur);

    @ApiOperation(value = "Recherche d'un fournisseur par NOM", notes="Cette methode permet de chercher un fournisseur par son NOM",response =FournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code= 200, message = "Le fournisseur a ete trouve dans la BD"),
            @ApiResponse(code= 400, message = "Aucun fournisseur n'existe dans la bd avec le NOM fourni")
    })
    @GetMapping(value = APP_ROOT + "/fournisseur/nom/{nomFournisseur}", produces =  MediaType.APPLICATION_JSON_VALUE)
    FournisseurDto findByNom(@PathVariable ("nomFournisseur") String nom);

    @ApiOperation(value = "Renvoi la liste des fournisseurs", notes="Cette methode permet de chercher et de renvoyer la liste des fournisseurs qui existent" + "dans la BD",responseContainer = "List<FournisseurDto>")
    @ApiResponses(value = {
            @ApiResponse(code= 200, message = "La liste des fournisseurs/ une liste vide"),
    })
    @GetMapping(value = APP_ROOT + "/fournisseur/all", produces =  MediaType.APPLICATION_JSON_VALUE)
    List<FournisseurDto> findAll();

    @ApiOperation(value = "Supprimer un fournisseur", notes="Cette methode permet de supprimer un fournisseur par son ID",response = FournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code= 200, message = "Le fournisseur a ete supprime"),
    })
    @DeleteMapping(value = APP_ROOT + "/fournisseur/delete/{idFournisseur}")
    void delete(@PathVariable("idFournisseur") Long id);

    //Debut du listing
    @ApiOperation(value = "Renvoi la liste des fournisseurs en fonction des critères de recherche",
            notes="Cette methode permet de chercher et de renvoyer la liste des fournisseurs qui existent" +
                    "dans la BD", responseContainer = "List<FournisseurDto>")
    @ApiResponses(value = {
            @ApiResponse(code= 200, message = "La liste des fournisseurs/ une liste vide"),
    })
    @PostMapping(value = APP_ROOT + "/fournisseur/listingFournisseur", produces =  MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    List<FournisseurDto> listingFournisseur(@RequestBody FournisseurCriteria fournisseurCriteria);
}
