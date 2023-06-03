package com.stage.gestiondestock_backend.model;

import javax.persistence.*;

import com.stage.gestiondestock_backend.model.enumeration.SourceMvtStk;
import com.stage.gestiondestock_backend.model.enumeration.TypeMvtStk;
import lombok.*;
import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "mvtstock")
public class MvtStock extends AbstractEntity{

    @Column(name ="datemvt")
    private Instant dateMvt;

    @Column(name ="quantite")
    private BigDecimal quantite;

    @Column(name = "actif", columnDefinition = "tinyint(1) default 1", nullable = false)
    private boolean actif;

    @Column(name = "code")
    private String code;

    @Column(name = "identreprise")
    private Integer idEntreprise;

    @ManyToOne
    @JoinColumn(name = "idarticle")
    private  Article article;

    @Column(name ="typemvt")
    @Enumerated(EnumType.STRING)
    private TypeMvtStk typeMvtStk;

    @Column(name ="sourcemvt")
    @Enumerated(EnumType.STRING)
    private SourceMvtStk sourceMvtStk;
}

