package com.stage.gestiondestock_backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stage.gestiondestock_backend.model.LigneCommandeFournisseur;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LigneCommandeFournisseurDto {

    private Long id;

    private String code;

    private BigDecimal quantite;

    private LocalDateTime dateEnregistrement;

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
                .dateEnregistrement(ligneCommandeFournisseur.getDateEnregistrement())
                .article(ArticleDto.fromEntity(ligneCommandeFournisseur.getArticle()))
                .quantite(ligneCommandeFournisseur.getQuantite())
                .code(ligneCommandeFournisseur.getCode())
                .prixUnitaire(ligneCommandeFournisseur.getPrixUnitaire())
                .IdEntreprise(ligneCommandeFournisseur.getIdEntreprise())
                .build();
    }

    public static LigneCommandeFournisseur toEntity(LigneCommandeFournisseurDto dto) {
        if (dto == null) {
            return null;
        }
        LigneCommandeFournisseur ligneCommandeFournisseur = new LigneCommandeFournisseur();
        ligneCommandeFournisseur.setId(dto.getId());
        ligneCommandeFournisseur.setDateEnregistrement(dto.getDateEnregistrement());
        ligneCommandeFournisseur.setArticle((ArticleDto.toEntity(dto.getArticle())));
        ligneCommandeFournisseur.setQuantite(dto.getQuantite());
        ligneCommandeFournisseur.setCode(dto.getCode());
        ligneCommandeFournisseur.setPrixUnitaire(dto.getPrixUnitaire());
        ligneCommandeFournisseur.setIdEntreprise(dto.getIdEntreprise());
        return ligneCommandeFournisseur;
    }
}
