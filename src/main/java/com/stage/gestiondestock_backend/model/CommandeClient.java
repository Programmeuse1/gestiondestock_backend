package com.stage.gestiondestock_backend.model;

import javax.persistence.*;

import com.stage.gestiondestock_backend.model.enumeration.EtatCommande;
import lombok.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "commande_client")
public class CommandeClient extends AbstractEntity{

    @Column(name ="etatcommande")
    @Enumerated(EnumType.STRING)
    private EtatCommande etatCommande;

    @Column(name = "code")
    private String code;

    @Column(name = "actif", columnDefinition = "tinyint(1) default 1", nullable = false)
    private boolean actif;

    @Column(name = "datecommande")
    private Instant dateCommande;

    @Column(name = "identreprise")
    private Integer idEntreprise;

    @ManyToOne
    @JoinColumn(name ="idclient")
    private Client client;

    @OneToMany(mappedBy ="commandeClient")
    private List<LigneCommandeClient> ligneCommandeClients;

    @PrePersist
    @PreUpdate
    public void prePersist() {
        dateCommande = dateCommande == null ? Instant.now() : dateCommande;
    }
}
