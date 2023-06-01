package com.stage.gestiondestock_backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.*;
import java.time.Instant;
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

    @Column(name = "datevente")
    private Instant dateVente;

    @Column(name = "observation")
    private String observation;

    @OneToMany(mappedBy = "vente")
    private List<LigneVente> LigneVentes;
}

