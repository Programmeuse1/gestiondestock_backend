package com.stage.gestiondestock_backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stage.gestiondestock_backend.model.LigneCommandeClient;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    private String observation;

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
                .observation(ligneCommandeClient.getObservation())
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
        ligneCommandeClient.setObservation(dto.getObservation());
        return ligneCommandeClient;

    }

    public static List<LigneCommandeClientDto> fromEntities(List<LigneCommandeClient> entities){
        if (entities == null){
            return null;
        }

        List<LigneCommandeClientDto> list = new ArrayList<>(entities.size());
        for (LigneCommandeClient ligneCommandeClient : entities){
            list.add(fromEntity(ligneCommandeClient));
        }

        return list;
    }

    public static List<LigneCommandeClient> toEntities(List<LigneCommandeClientDto> entities){
        if (entities == null){
            return null;
        }

        List<LigneCommandeClient> list = new ArrayList<>(entities.size());
        for (LigneCommandeClientDto ligneCommandeClientDto : entities){
            list.add(toEntity(ligneCommandeClientDto));
        }

        return list;
    }

    @Override
    public String toString() {
        return "LigneCommandeClientDto{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", dateEnregistrement=" + dateEnregistrement +
                ", article=" + article +
                ", observation='" + observation + '\'' +
                ", quantite=" + quantite +
                ", prixUnitaire=" + prixUnitaire +
                '}';
    }
}
