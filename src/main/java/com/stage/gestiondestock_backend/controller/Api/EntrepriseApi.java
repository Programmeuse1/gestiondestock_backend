package com.stage.gestiondestock_backend.controller.Api;

import com.stage.gestiondestock_backend.Dto.EntrepriseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static com.stage.gestiondestock_backend.utils.Constants.APP_ROOT;

@Api("entrepriseApi")
public interface EntrepriseApi {

    @ApiOperation(value = "Enregistrer une entreprise", notes="Cette methode permet d'enregistrer ou modifier une entreprise",response = EntrepriseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code= 200, message = "L'objet entreprise a ete cree/modifier"),
            @ApiResponse(code= 400, message = "L'objet entreprise n'est pas valide")
    })
    @PostMapping(value = APP_ROOT + "/entreprise/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE)
    EntrepriseDto save(@RequestBody EntrepriseDto dto);

    @ApiOperation(value = "Recherche d'une entreprise par l'ID", notes="Cette methode permet de chercher une entreprise par son ID",response = EntrepriseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code= 200, message = "L'entreprise a ete trouve dans la BD"),
            @ApiResponse(code= 400, message = "Aucune entreprise n'existe dans la bd avec l'ID fourni")
    })
    @GetMapping(value = APP_ROOT + "/entreprise/id/{idEntreprise}", produces =  MediaType.APPLICATION_JSON_VALUE)
    EntrepriseDto findById(@PathVariable("idEntreprise") Long idEntreprise);

    @ApiOperation(value = "Recherche d'une entreprise par NOM", notes="Cette methode permet de chercher une entreprise par son NOM",response = EntrepriseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code= 200, message = "L'entreprise a ete trouve dans la BD"),
            @ApiResponse(code= 400, message = "Aucune entreprise n'existe dans la bd avec le NOM fourni")
    })
    @GetMapping(value = APP_ROOT + "/entreprise/nom/{nomEntreprise}", produces =  MediaType.APPLICATION_JSON_VALUE)
    EntrepriseDto findByNom(@PathVariable ("nomEntreprise") String nom);

    @ApiOperation(value = "Renvoi la liste des entreprises", notes="Cette methode permet de chercher et de renvoyer la liste des entreprises qui existent" + "dans la BD",responseContainer = "List<EntrepriseDto>")
    @ApiResponses(value = {
            @ApiResponse(code= 200, message = "La liste des entreprises/ une liste vide"),
    })
    @GetMapping(value = APP_ROOT + "/entreprise/all", produces =  MediaType.APPLICATION_JSON_VALUE)
    List<EntrepriseDto> findAll();

    @ApiOperation(value = "Supprimer une entreprise", notes="Cette methode permet de supprimer une entreprise par son ID",response = EntrepriseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code= 200, message = "L'entreprise a ete supprime"),
    })
    @DeleteMapping(value = APP_ROOT + "/entreprise/delete/{idEntreprise}")
    void delete(@PathVariable("idEntreprise") Long id);
}
