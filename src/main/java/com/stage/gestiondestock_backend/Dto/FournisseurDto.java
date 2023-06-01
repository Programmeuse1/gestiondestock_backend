package com.stage.gestiondestock_backend.Dto;

import com.stage.gestiondestock_backend.model.Adresse;
import com.stage.gestiondestock_backend.model.Fournisseur;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FournisseurDto {

    private Long id;

    private String nom;

    private String prenom;

    private Adresse adresse;

    private String photo;

    private String mail;

    private String numTel;

    private Integer idEntreprise;

    private List<CommandeFournisseurDto> commandeFournisseurs ;

    public static FournisseurDto fromEntity(Fournisseur fournisseur){
        if(fournisseur == null){
            return null;
        }

        return FournisseurDto.builder()
                //.id(client.getId())
                .nom(fournisseur.getNom())
                .prenom(fournisseur.getPrenom())
                .adresse(fournisseur.getAdresse())
                .photo(fournisseur.getPhoto())
                .mail(fournisseur.getMail())
                .numTel(fournisseur.getNumTel())
                .idEntreprise(fournisseur.getIdEntreprise())
                .build();
    }

    public static Fournisseur toEntity(FournisseurDto fournisseurDto) {
        if (fournisseurDto == null) {
            return null;
            //TODO throw exception
        }
        return Fournisseur.builder()
                .nom(fournisseurDto.getNom())
                .prenom(fournisseurDto.getPrenom())
                .adresse(fournisseurDto.getAdresse())
                .photo(fournisseurDto.getPhoto())
                .mail(fournisseurDto.getMail())
                .numTel(fournisseurDto.getNumTel())
                .idEntreprise(fournisseurDto.getIdEntreprise())
                .build();
    }}
