package com.stage.gestiondestock_backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "number")
    private String number;
    @Column(name = "date")
    private String date;
    @Column(name = "montant")
    private double montant;
    @Column(name = "method")
    private String method;
    @Column(name = "motif")
    private String motif;
    @Column(name = "reference")
    private String reference;

    @ManyToOne(fetch = FetchType.EAGER)
    private User fromUser;

    @ManyToOne(fetch = FetchType.EAGER)
    private User toUser;

    @CreatedDate
    @Column(name = "date_creation", nullable = false, updatable = false)
    private LocalDateTime dateCreation;

    @LastModifiedDate
    @Column(name = "derniere_date_modification", nullable = false)
    private LocalDateTime derniereDateModification;
}

