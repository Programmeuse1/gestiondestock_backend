package com.stage.gestiondestock_backend.dto;

import com.stage.gestiondestock_backend.model.MvtStock;
import com.stage.gestiondestock_backend.model.enumeration.SourceMvtStk;
import com.stage.gestiondestock_backend.model.enumeration.TypeMvtStk;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MvtStockDto {

    private Long id;

    private Instant dateMvt;

    private LocalDateTime dateEnregistrement;

    private BigDecimal quantite;

//    entreprise
    private Integer idEntreprise;
//code
    private  ArticleDto article;

    private TypeMvtStk typeMvtStk;

    private SourceMvtStk sourceMvtStk;

    public static MvtStockDto fromEntity(MvtStock mvtStock) {
        if(mvtStock==null){
            return null;
        }

        return MvtStockDto.builder()
                .id(mvtStock.getId())
                .dateEnregistrement(mvtStock.getDateEnregistrement())
                .dateMvt(mvtStock.getDateMvt())
                .quantite(mvtStock.getQuantite())
                .article(ArticleDto.fromEntity(mvtStock.getArticle()))
                .typeMvtStk(mvtStock.getTypeMvtStk())
                .sourceMvtStk(mvtStock.getSourceMvtStk())
                .build();
    }

    public static MvtStock toEntity(MvtStockDto dto){
        if(dto == null){
            return null;
        }
        MvtStock mvtStock = new MvtStock();
        mvtStock.setId(dto.getId());
        mvtStock.setDateEnregistrement(dto.getDateEnregistrement());
        mvtStock.setArticle((ArticleDto.toEntity(dto.getArticle())));
        mvtStock.setDateMvt(dto.getDateMvt());
        mvtStock.setIdEntreprise(dto.getIdEntreprise());
        mvtStock.setQuantite(dto.getQuantite());
        mvtStock.setTypeMvtStk(dto.getTypeMvtStk());
        mvtStock.setSourceMvtStk(dto.getSourceMvtStk());
        return mvtStock;
    }

}
