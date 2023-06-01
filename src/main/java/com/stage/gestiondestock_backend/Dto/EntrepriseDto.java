package com.stage.gestiondestock_backend.Dto;

import com.stage.gestiondestock_backend.model.Adresse;
import com.stage.gestiondestock_backend.model.Entreprise;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EntrepriseDto {

    private Long id;

    private String nom;

    private String description;

    private Adresse adresse;

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
                //.id(client.getId())
                .nom(entreprise.getNom())
                .description(entreprise.getDescription())
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
        entreprise.setNom(entrepriseDto.getNom());
        entreprise.setDescription(entrepriseDto.getDescription());
        entreprise.setCodeFiscal(entrepriseDto.getCodeFiscal());
        entreprise.setPhoto(entrepriseDto.getPhoto());
        entreprise.setNumTel(entrepriseDto.getNumTel());
        entreprise.setEmail(entrepriseDto.getEmail());

        return entreprise;
    }

}
