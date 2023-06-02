package com.stage.gestiondestock_backend.Dto;

import com.stage.gestiondestock_backend.model.Article;
import lombok.*;
import java.math.BigDecimal;
import java.time.Instant;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ArticleDto {

    private Long id;

    private String code;

    private boolean actif;

    private String designation;

    private BigDecimal prixUnitaireHt;

    private BigDecimal tauxTva;

    private BigDecimal prixUnitaireTtc;

    private String photo;

    private Integer idEntreprise;

    private CategoryDto category;

    public static ArticleDto fromEntity(Article article) {
        if (article == null) {
            return null;
        }

        return ArticleDto.builder()
                .id(article.getId())
                .code(article.getCode())
                .actif(article.isActif())
                .designation(article.getDesignation())
                .prixUnitaireHt(article.getPrixUnitaireHt())
                .tauxTva(article.getTauxTva())
                .prixUnitaireTtc(article.getPrixUnitaireTtc())
                .photo(article.getPhoto())
                .idEntreprise(article.getIdEntreprise())
                .category(CategoryDto.fromEntity(article.getCategory()))
                .build();
    }

    public static Article toEntity(ArticleDto articleDto) {

        if (articleDto== null) {
            return null;
            //TODO throw exception
        }
        Article article =new Article();
        article.setId(articleDto.getId());
        article.setCode(articleDto.getCode());
        article.setActif(articleDto.isActif());
        article.setDesignation(articleDto.getDesignation());
        article.setPrixUnitaireHt(articleDto.getPrixUnitaireHt());
        article.setPrixUnitaireTtc(articleDto.getPrixUnitaireTtc());
        article.setTauxTva(articleDto.getTauxTva());
        article.setIdEntreprise(articleDto.getIdEntreprise());
        article.setPhoto(articleDto.getPhoto());
        article.setCategory(CategoryDto.toEntity(articleDto.getCategory()));
        return  article;
    }
}
