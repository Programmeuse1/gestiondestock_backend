package com.stage.gestiondestock_backend.service.criteria;

import com.stage.gestiondestock_backend.model.Article;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MvtStockCriteria {

    private Article article;
    private String dateEnregistrement;
    private String nombreDeResultat;
}
