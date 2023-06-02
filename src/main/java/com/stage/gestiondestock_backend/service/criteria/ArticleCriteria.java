package com.stage.gestiondestock_backend.service.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleCriteria {
    private String designation;
    private String code;
    private Boolean actif;
    private String nombreDeResultat;
}
