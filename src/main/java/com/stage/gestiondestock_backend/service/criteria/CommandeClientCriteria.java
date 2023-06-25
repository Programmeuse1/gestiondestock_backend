package com.stage.gestiondestock_backend.service.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommandeClientCriteria {

    private String code;
    private Boolean actif;
    private String nomClient;
    private String phoneClient;
    private String nombreDeResultat;

}
