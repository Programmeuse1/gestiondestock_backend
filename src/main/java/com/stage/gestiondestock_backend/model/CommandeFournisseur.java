package com.stage.gestiondestock_backend.model;


import javax.persistence.*;

import com.stage.gestiondestock_backend.model.enumeration.EtatCommande;
import lombok.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "commande_fournisseur")
public class CommandeFournisseur extends AbstractEntity {

    @Column(name ="etatcommande")
    @Enumerated(EnumType.STRING)
    private EtatCommande etatCommande;

    @Column(name ="code")
    private String code;

    @Column(name = "actif", columnDefinition = "tinyint(1) default 1", nullable = false)
    private boolean actif;

    @Column( name ="datecommande")
    private Instant dateCommande;

    @Column(name = "identreprise")
    private Integer idEntreprise;

    @ManyToOne
    @JoinColumn(name ="idfournisseur")
    private Fournisseur fournisseur;

    @OneToMany(mappedBy = "commandeFournisseur")
    private List<LigneCommandeFournisseur> ligneCommandeFournisseurs;

    @Column(name = "date_enregistrement")
    private LocalDateTime dateEnregistrement;

    @PrePersist
    void p() {
        dateEnregistrement = dateEnregistrement == null ? LocalDateTime.now() : dateEnregistrement;
        dateCommande = dateCommande == null ? Instant.now() : dateCommande;
    }
}

