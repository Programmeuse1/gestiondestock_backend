package com.stage.gestiondestock_backend.model;


import javax.persistence.*;

import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "client")
public class Client extends AbstractEntity{

    @Column(name = "nom")
    private String nom;

    @Column(name = "code")
    private String code;

    @Column(name = "prenom")
    private String prenom ;

    @Column(name = "actif", columnDefinition = "tinyint(1) default 1", nullable = false)
    private boolean actif;

    @Embedded
    private Adresse adresse;

    @Column(name = "photo")
    private String photo;

    @Column(name = "numtel")
    private String numTel;

    @Column(name = "email")
    private String email;

    @Column(name = "identreprise")
    private Integer idEntreprise;

    @Column(name = "date_enregistrement")
    private LocalDateTime dateEnregistrement;

    @PrePersist
    void p() {
        dateEnregistrement = dateEnregistrement == null ? LocalDateTime.now() : dateEnregistrement;
    }
}

