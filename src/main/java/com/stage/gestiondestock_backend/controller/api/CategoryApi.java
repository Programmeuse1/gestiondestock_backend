package com.stage.gestiondestock_backend.controller.api;

import com.stage.gestiondestock_backend.Dto.CategoryDto;
import com.stage.gestiondestock_backend.service.criteria.CategoryCriteria;
import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.stage.gestiondestock_backend.utils.Constants.APP_ROOT;

@Api("categoriesApi")
public interface CategoryApi {

    @ApiOperation(value = "Enregistrer une categorie", notes="Cette methode permet d'enregistrer ou modifier une categorie",response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code= 200, message = "L'objet categorie a ete cree/modifier"),
            @ApiResponse(code= 400, message = "L'objet catgorie n'est pas valide")
    })
    @PostMapping(value = APP_ROOT + "/category/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE)
    CategoryDto save(@RequestBody  CategoryDto dto);

    @ApiOperation(value = "Recherche d'une categorie par l'ID", notes="Cette methode permet de chercher un categorie par son ID",response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code= 200, message = "La categorie a ete trouve dans la BD"),
            @ApiResponse(code= 400, message = "Aucune categorie n'existe dans la bd avec l'ID fourni")
    })
    @GetMapping(value = APP_ROOT + "/category/id/{idCategory}", produces =  MediaType.APPLICATION_JSON_VALUE)
    CategoryDto findById(@PathVariable("idCategory") Long idCategory);

    @ApiOperation(value = "Recherche d'une categorie par CODE", notes="Cette methode permet de chercher une categorie par son CODE",response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code= 200, message = "La categorie a ete trouve dans la BD"),
            @ApiResponse(code= 400, message = "Aucune categorie n'existe dans la bd avec le CODE fourni")
    })
    @GetMapping(value = APP_ROOT + "/category/code/{codeCategory}", produces =  MediaType.APPLICATION_JSON_VALUE)
    CategoryDto findByCode(@PathVariable ("codeCategory") String code);

    @ApiOperation(value = "Renvoi la liste des categories", notes="Cette methode permet de chercher et de renvoyer la liste des categories qui existent" + "dans la BD",responseContainer = "List<CategoryDto>")
    @ApiResponses(value = {
            @ApiResponse(code= 200, message = "La liste des categories/ une liste vide"),
    })
    @GetMapping(value = APP_ROOT + "/category/all", produces =  MediaType.APPLICATION_JSON_VALUE)
    List< CategoryDto> findAll();

    @ApiOperation(value = "Supprimer une categorie", notes="Cette methode permet de supprimer une categorie par son ID",response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code= 200, message = "La categorie a ete supprime"),
    })
    @DeleteMapping(value = APP_ROOT + "/category/delete/{idCategory}")
    void delete(@PathVariable("idCategory") Long id);

    //Debut du listing
    @ApiOperation(value = "Renvoi la liste des categorie en fonction des critères de recherche",
            notes="Cette methode permet de chercher et de renvoyer la liste des categories qui existent" +
                    "dans la BD", responseContainer = "List<CategoryDto>")
    @ApiResponses(value = {
            @ApiResponse(code= 200, message = "La liste des categories/ une liste vide"),
    })
    @PostMapping(value = APP_ROOT + "/category/listingCategorie", produces =  MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    List<CategoryDto> listingCategory(@RequestBody CategoryCriteria categoryCriteria);
}
