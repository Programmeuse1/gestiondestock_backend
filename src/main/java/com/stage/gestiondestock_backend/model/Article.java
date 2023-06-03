package com.stage.gestiondestock_backend.model;

import javax.persistence.*;

import com.stage.gestiondestock_backend.utils.MethodUtils;
import lombok.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "article")
public class Article  extends AbstractEntity{

    @Column(name = "code")
    private String code;

    @Column(name = "actif", columnDefinition = "tinyint(1) default 1", nullable = false)
    private boolean actif;

    @Column(name = "designation")
    private String designation;

    @Column(name = "date_enregistrement")
    private LocalDateTime dateEnregistrement;

    @Column(name = "prixunitaireht")
    private BigDecimal prixUnitaireHt;

    @Column(name = "tauxtva")
    private BigDecimal tauxTva;

    @Column(name = "prixunitairettc")
    private BigDecimal prixUnitaireTtc;

    @Column(name = "photo")
    private String photo;

    @Column(name = "identreprise")
    private Integer idEntreprise;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    @PostPersist
    @PostUpdate
    void article() {
        code = code == null ? "ART- " + MethodUtils.format(getId().intValue(), 6) : code;
    }

    @PrePersist
    void p() {
        dateEnregistrement = dateEnregistrement == null ? LocalDateTime.now() : dateEnregistrement;
    }
}

