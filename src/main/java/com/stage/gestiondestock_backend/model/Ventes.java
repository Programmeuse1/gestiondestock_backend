package com.stage.gestiondestock_backend.model;

import javax.persistence.*;

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
@Table(name = "ventes")
public class Ventes extends AbstractEntity{

    @Column(name = "id")
    private Long id;

    @Column(name = "identreprise")
    private Integer idEntreprise;

    @Column(name = "code")
    private String code;

    @Column(name = "actif", columnDefinition = "tinyint(1) default 1", nullable = false)
    private boolean actif;

    @Column(name = "datevente")
    private Instant dateVente;

    @Column(name = "observation")
    private String observation;

    @OneToMany(mappedBy = "vente")
    private List<LigneVente> LigneVentes;

    @Column(name = "date_enregistrement")
    private LocalDateTime dateEnregistrement;

    @PrePersist
    void p() {
        dateEnregistrement = dateEnregistrement == null ? LocalDateTime.now() : dateEnregistrement;
    }
}

