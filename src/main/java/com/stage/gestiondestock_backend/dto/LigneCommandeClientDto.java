package com.stage.gestiondestock_backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stage.gestiondestock_backend.model.LigneCommandeClient;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LigneCommandeClientDto {

    private Long id;

    private String code;

    private LocalDateTime dateEnregistrement;

    private ArticleDto article;

    @JsonIgnore
    private  CommandeClientDto commandeClient;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;

    public static LigneCommandeClientDto fromEntity(LigneCommandeClient ligneCommandeClient){
        if(ligneCommandeClient == null){
            return null;
        }
        return LigneCommandeClientDto.builder()
                .id(ligneCommandeClient.getId())
                .dateEnregistrement(ligneCommandeClient.getDateEnregistrement())
                .article(ArticleDto.fromEntity(ligneCommandeClient.getArticle()))
                .quantite(ligneCommandeClient.getQuantite())
                .code(ligneCommandeClient.getCode())
                .prixUnitaire(ligneCommandeClient.getPrixUnitaire())
                .build();
    }

    public static LigneCommandeClient toEntity(LigneCommandeClientDto dto) {
        if(dto == null){
            return null;
        }
        LigneCommandeClient ligneCommandeClient = new LigneCommandeClient();
        ligneCommandeClient.setId(dto.getId());
        ligneCommandeClient.setDateEnregistrement(dto.getDateEnregistrement());
        ligneCommandeClient.setArticle((ArticleDto.toEntity(dto.getArticle())));
        ligneCommandeClient.setPrixUnitaire(dto.getPrixUnitaire());
        ligneCommandeClient.setCode(dto.getCode());
        ligneCommandeClient.setQuantite(dto.getQuantite());
        return ligneCommandeClient;

    }
}