package com.stage.gestiondestock_backend.controller.api;

import com.stage.gestiondestock_backend.Dto.ArticleDto;
import com.stage.gestiondestock_backend.model.Article;
import com.stage.gestiondestock_backend.service.criteria.ArticleCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.stage.gestiondestock_backend.utils.Constants.APP_ROOT;

@Api("articlesApi")
public interface ArticleApi {

   @ApiOperation(value = "Enregistrer un article", notes="Cette methode permet d'enregistrer ou modifier un article",response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code= 200, message = "L'objet article a ete cree/modifier"),
            @ApiResponse(code= 400, message = "L'objet article n'est pas valide")
    })
   @PostMapping(value = APP_ROOT + "/article/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE)
   ArticleDto save(@RequestBody ArticleDto dto);

   @ApiOperation(value = "Recherche d'un article par l'ID", notes="Cette methode permet de chercher un article par son ID",response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code= 200, message = "L'article a ete trouve dans la BD"),
            @ApiResponse(code= 400, message = "Aucun article n'existe dans la bd avec l'ID fourni")
    })
   @GetMapping(value = APP_ROOT + "/article/id/{idArticle}", produces =  MediaType.APPLICATION_JSON_VALUE)
   ArticleDto findById(@PathVariable("idArticle") Long idArticle);

    @ApiOperation(value = "Recherche d'un article par CODE", notes="Cette methode permet de chercher un article par son CODE",response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code= 200, message = "L'article a ete trouve dans la BD"),
            @ApiResponse(code= 400, message = "Aucun article n'existe dans la bd avec le CODE fourni")
    })
    @GetMapping(value = APP_ROOT + "/article/code/{codeArticle}", produces =  MediaType.APPLICATION_JSON_VALUE)
    ArticleDto findByCode(@PathVariable ("codeArticle") String code);

   @ApiOperation(value = "Renvoi la liste des articles", notes="Cette methode permet de chercher et de renvoyer la liste des article qui existent" + "dans la BD",responseContainer = "List<ArticleDto>")
    @ApiResponses(value = {
            @ApiResponse(code= 200, message = "La liste des articles/ une liste vide"),
    })
    @GetMapping(value = APP_ROOT + "/article/all", produces =  MediaType.APPLICATION_JSON_VALUE)
    List<ArticleDto> findAll();

    @ApiOperation(value = "Supprimer un article", notes="Cette methode permet de supprimer un article par son ID",response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code= 200, message = "L'article a ete supprime"),
    })
    @DeleteMapping(value = APP_ROOT + "/article/delete/{idArticle}")
    void delete(@PathVariable("idArticle") Long id);

    @ApiOperation(value = "Renvoi la liste des articles en fonction des critères de recherche",
            notes="Cette methode permet de chercher et de renvoyer la liste des article qui existent" +
                    "dans la BD", responseContainer = "List<ArticleDto>")
    @ApiResponses(value = {
            @ApiResponse(code= 200, message = "La liste des articles/ une liste vide"),
    })
    @PostMapping(value = APP_ROOT + "/article/listingArticle", produces =  MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    List<ArticleDto> listingArticle(@RequestBody ArticleCriteria articleCriteria);
}
