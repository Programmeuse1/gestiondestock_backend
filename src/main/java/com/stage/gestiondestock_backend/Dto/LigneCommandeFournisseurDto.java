package com.stage.gestiondestock_backend.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stage.gestiondestock_backend.model.LigneCommandeFournisseur;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LigneCommandeFournisseurDto {

    private Long id;

    private String code;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;

    private Integer IdEntreprise;

    private ArticleDto article;

    @JsonIgnore
    private CommandeFournisseurDto commandeFournisseur;

    public static LigneCommandeFournisseurDto fromEntity(LigneCommandeFournisseur ligneCommandeFournisseur){
        if(ligneCommandeFournisseur == null){
            return null;
        }
        return LigneCommandeFournisseurDto.builder()
                .id(ligneCommandeFournisseur.getId())
                .article(ArticleDto.fromEntity(ligneCommandeFournisseur.getArticle()))
                .quantite(ligneCommandeFournisseur.getQuantite())
                .code(ligneCommandeFournisseur.getCode())
                .prixUnitaire(ligneCommandeFournisseur.getPrixUnitaire())
                .IdEntreprise(ligneCommandeFournisseur.getIdEntreprise())
                .build();
    }

    public static LigneCommandeFournisseur toEntity(LigneCommandeFournisseurDto dto) {
        if (dto == null) {
        }
        LigneCommandeFournisseur ligneCommandeFournisseur = new LigneCommandeFournisseur();
        ligneCommandeFournisseur.setId(dto.getId());
        ligneCommandeFournisseur.setArticle((ArticleDto.toEntity(dto.getArticle())));
        ligneCommandeFournisseur.setQuantite(dto.getQuantite());
        ligneCommandeFournisseur.setCode(dto.getCode());
        ligneCommandeFournisseur.setPrixUnitaire(dto.getPrixUnitaire());
        ligneCommandeFournisseur.setIdEntreprise(dto.getIdEntreprise());
        return ligneCommandeFournisseur;
    }
}
