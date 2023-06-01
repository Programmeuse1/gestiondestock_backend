package com.stage.gestiondestock_backend.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stage.gestiondestock_backend.model.LigneVente;
import lombok.*;

import java.math.BigDecimal;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LigneVenteDto {

    private Long id;

    private BigDecimal quantite;

    private String code;

    private BigDecimal prixUnitaire;

    private Integer idEntreprise;

    private ArticleDto article;

    @JsonIgnore
    private VentesDto vente;

    public static LigneVenteDto fromEntity(LigneVente ligneVente){
        if(ligneVente == null){
            return null;
        }
        return LigneVenteDto.builder()
                .id(ligneVente.getId())
                .code(ligneVente.getCode())
                .article(ArticleDto.fromEntity(ligneVente.getArticle()))
                .quantite(ligneVente.getQuantite())
                .prixUnitaire(ligneVente.getPrixUnitaire())
                .build();
    }

    public static LigneVente toEntity(LigneVenteDto dto) {
        if(dto == null){
            return null;
        }
        LigneVente ligneVente = new LigneVente();
        ligneVente.setId(dto.getId());
        ligneVente.setCode(dto.getCode());
        ligneVente.setArticle((ArticleDto.toEntity(dto.getArticle())));
        ligneVente.setPrixUnitaire(dto.getPrixUnitaire());
        ligneVente.setQuantite(dto.getQuantite());
        return ligneVente;

    }
}
