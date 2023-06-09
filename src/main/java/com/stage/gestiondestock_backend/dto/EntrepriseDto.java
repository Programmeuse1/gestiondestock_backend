package com.stage.gestiondestock_backend.dto;

import com.stage.gestiondestock_backend.model.Adresse;
import com.stage.gestiondestock_backend.model.Entreprise;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EntrepriseDto {

    private Long id;

    private String nom;

    private String code;

    private boolean actif;

    private String description;

    private Adresse adresse;

    private LocalDateTime dateEnregistrement;

    private String codeFiscal;

    private String photo;

    private String email;

    private String numTel;

    private String siteWeb;

    private List<UtilisateurDto> utilisateurs;

    public static EntrepriseDto fromEntity(Entreprise entreprise){
        if(entreprise == null){
            return null;
        }

        return EntrepriseDto.builder()
                .id(entreprise.getId())
                .nom(entreprise.getNom())
                .code(entreprise.getCode())
                .actif(entreprise.isActif())
                .description(entreprise.getDescription())
                .dateEnregistrement(entreprise.getDateEnregistrement())
                .codeFiscal(entreprise.getCodeFiscal())
                .photo(entreprise.getPhoto())
                .numTel(entreprise.getNumTel())
                .email(entreprise.getEmail())
                .build();
    }

    public static Entreprise toEntity(EntrepriseDto entrepriseDto) {
        if ( entrepriseDto == null) {
            return null;
            //TODO throw exception
        }
        Entreprise entreprise = new Entreprise();
        entreprise.setId(entrepriseDto.getId());
        entreprise.setDateEnregistrement(entrepriseDto.getDateEnregistrement());
        entreprise.setNom(entrepriseDto.getNom());
        entreprise.setCode(entrepriseDto.getCode());
        entreprise.setActif(entrepriseDto.isActif());
        entreprise.setDescription(entrepriseDto.getDescription());
        entreprise.setCodeFiscal(entrepriseDto.getCodeFiscal());
        entreprise.setPhoto(entrepriseDto.getPhoto());
        entreprise.setNumTel(entrepriseDto.getNumTel());
        entreprise.setEmail(entrepriseDto.getEmail());

        return entreprise;
    }

}
