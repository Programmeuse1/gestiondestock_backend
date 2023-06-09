package com.stage.gestiondestock_backend.model;

import javax.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "fournisseur")
public class Fournisseur extends AbstractEntity{


    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "actif", columnDefinition = "tinyint(1) default 1", nullable = false)
    private boolean actif;

    @Column(name = "code")
    private String code;

    @Embedded
    private Adresse adresse;

    @Column(name = "photo")
    private String photo;

    @Column(name = "mail")
    private String mail;

    @Column(name = "numTel")
    private String numTel;

    @Column(name = "identreprise")
    private Integer idEntreprise;

    @OneToMany(mappedBy ="fournisseur")
    private List<CommandeFournisseur> commandeFournisseurs ;

    @Column(name = "date_enregistrement")
    private LocalDateTime dateEnregistrement;

    @PrePersist
    void p() {
        dateEnregistrement = dateEnregistrement == null ? LocalDateTime.now() : dateEnregistrement;
    }
}
