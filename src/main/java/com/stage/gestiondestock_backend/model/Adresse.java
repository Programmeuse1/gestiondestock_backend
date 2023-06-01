package com.stage.gestiondestock_backend.model;


import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Adresse  {

    @Column(name = "adresse1")
    private String adresse1;

    @Column(name = "codePostale")
    private String codepostale;

    @Column(name = "adresse2")
    private String adresse2;

    @Column(name = "ville")
    private String ville;

    @Column(name = "pays")
    private String pays;
}

