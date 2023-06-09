package com.stage.gestiondestock_backend.dto;

import com.stage.gestiondestock_backend.model.Adresse;
import com.stage.gestiondestock_backend.model.Utilisateur;
import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurDto {

    private Long id;

    private String nom;

    private boolean actif;

    private String code;

    private LocalDateTime dateEnregistrement;

    private String prenom;

    private String email;

    private Instant dateDeNaissance;

    private String motDePasse;

    private Adresse adresse;

    private String photo;

    private String numTel;

    private EntrepriseDto entreprise;

    private List<RolesDto> roles;

    public static UtilisateurDto fromEntity(Utilisateur utilisateur){
        if(utilisateur == null){
            return null;
        }

        return UtilisateurDto.builder()
                .id(utilisateur.getId())
                .dateEnregistrement(utilisateur.getDateEnregistrement())
                .nom(utilisateur.getNom())
                .actif(utilisateur.isActif())
                .code(utilisateur.getCode())
                .prenom(utilisateur.getPrenom())
                .dateDeNaissance(utilisateur.getDateDeNaissance())
                .motDePasse(utilisateur.getMotDePasse())
                .email(utilisateur.getEmail())
                .numTel(utilisateur.getNumTel())
                .adresse(utilisateur.getAdresse())
                .entreprise(EntrepriseDto.fromEntity(utilisateur.getEntreprise()))
                .build();
    }

    public static Utilisateur toEntity(UtilisateurDto utilisateurDto) {
        if (utilisateurDto == null) {
            return null;
        }
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(utilisateurDto.getId());
        utilisateur.setDateEnregistrement(utilisateurDto.getDateEnregistrement());
        utilisateur.setNom(utilisateurDto.getNom());
        utilisateur.setActif(utilisateurDto.isActif());
        utilisateur.setCode(utilisateurDto.getCode());
        utilisateur.setPrenom(utilisateurDto.getPrenom());
        utilisateur.setDateDeNaissance(utilisateurDto.getDateDeNaissance());
        utilisateur.setMotDePasse(utilisateurDto.getMotDePasse());
        utilisateur.setEmail(utilisateurDto.getEmail());
        utilisateur.setNumTel(utilisateurDto.getNumTel());
        utilisateur.setAdresse(utilisateurDto.getAdresse());
        utilisateur.setEntreprise(EntrepriseDto.toEntity(utilisateurDto.getEntreprise()));

        return utilisateur;
    }
}
