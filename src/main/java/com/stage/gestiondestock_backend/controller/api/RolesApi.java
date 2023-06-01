package com.stage.gestiondestock_backend.controller.api;

import com.stage.gestiondestock_backend.Dto.RolesDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static com.stage.gestiondestock_backend.utils.Constants.APP_ROOT;

@Api("rolesApi")
public interface RolesApi {

   @ApiOperation(value = "Enregistrer un role", notes="Cette methode permet d'enregistrer ou modifier un role",response =  RolesDto.class)
    @ApiResponses(value = {
            @ApiResponse(code= 200, message = "L'objet role a ete cree/modifier"),
            @ApiResponse(code= 400, message = "L'objet role n'est pas valide")
    })
   @PostMapping(value = APP_ROOT + "/roles/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE)
   RolesDto save(@RequestBody  RolesDto dto);

    @ApiOperation(value = "Recherche d'un role par l'ID", notes="Cette methode permet de chercher un role par son ID",response =  RolesDto.class)
    @ApiResponses(value = {
            @ApiResponse(code= 200, message = "Le role a ete trouve dans la BD"),
            @ApiResponse(code= 400, message = "Aucun role n'existe dans la bd avec l'ID fourni")
    })
    @GetMapping(value = APP_ROOT + "/roles/id/{idRoles}", produces =  MediaType.APPLICATION_JSON_VALUE)
    RolesDto findById(@PathVariable("idRoles") Long idRoles);

    @ApiOperation(value = "Recherche d'un role par ROLENAME", notes="Cette methode permet de chercher un role par son ROLENAME",response =  RolesDto.class)
    @ApiResponses(value = {
            @ApiResponse(code= 200, message = "Le role a ete trouve dans la BD"),
            @ApiResponse(code= 400, message = "Aucun role n'existe dans la bd avec le ROLENAME fourni")
    })
    @GetMapping(value = APP_ROOT + "/roles/roleName/{roleNameRoles}", produces =  MediaType.APPLICATION_JSON_VALUE)
    RolesDto findByRoleName(@PathVariable ("roleNameRoles") String roleName);

   @ApiOperation(value = "Renvoi la liste des roles", notes="Cette methode permet de chercher et de renvoyer la liste des roles qui existent" + "dans la BD",responseContainer = "List<RolesDto>")
    @ApiResponses(value = {
            @ApiResponse(code= 200, message = "La liste des roles/ une liste vide"),
    })
   @GetMapping(value = APP_ROOT + "/roles/all", produces =  MediaType.APPLICATION_JSON_VALUE)
   List< RolesDto> findAll();

    @ApiOperation(value = "Supprimer un role", notes="Cette methode permet de supprimer un role par son ID",response =  RolesDto.class)
    @ApiResponses(value = {
            @ApiResponse(code= 200, message = "Le role a ete supprime"),
    })
    @DeleteMapping(value = APP_ROOT + "/roles/delete/{idRoles}")
    void delete(@PathVariable("idRoles") Long id);
}


//    @PostMapping(ROLES_ENDPOINT +"/create")
//    RolesDto save(@RequestBody RolesDto dto);
//
//    @GetMapping(ROLES_ENDPOINT + "/{idRoles}")
//    RolesDto findById(@PathVariable("idRoles") Long id);
//
//    @GetMapping(ROLES_ENDPOINT + "/{roleNameRoles}")
//    RolesDto findByRoleName(@PathVariable ("roleNameRoles") String roleName);
//
//    @GetMapping(ROLES_ENDPOINT + "/all")
//    List<RolesDto> findAll();
//
//    @DeleteMapping(ROLES_ENDPOINT + "/delete/{idRoles}")
//    void delete(@PathVariable("idRoles") Long id);


