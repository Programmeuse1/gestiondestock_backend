package com.stage.gestiondestock_backend.model;

import javax.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "lignecommandeclient")
public class LigneCommandeClient extends AbstractEntity{

    @Column(name = "quantite")
    private BigDecimal quantite;

    @Column(name = "prixunitaire")
    private BigDecimal prixUnitaire;

    @Column(name = "actif", columnDefinition = "tinyint(1) default 1", nullable = false)
    private boolean actif;

    @Column(name = "code")
    private String code;

    @Column(name = "identreprise")
    private Integer idEntreprise;

    @ManyToOne
    @JoinColumn(name = "idarticle")
    private  Article article;

    @ManyToOne
    @JoinColumn(name ="idcommandeclient")
    private CommandeClient commandeClient;

    @Column(name = "date_enregistrement")
    private LocalDateTime dateEnregistrement;

    @PrePersist
    void p() {
        dateEnregistrement = dateEnregistrement == null ? LocalDateTime.now() : dateEnregistrement;
    }


}
