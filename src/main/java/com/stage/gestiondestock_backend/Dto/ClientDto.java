package com.stage.gestiondestock_backend.Dto;

import com.stage.gestiondestock_backend.model.Adresse;
import com.stage.gestiondestock_backend.model.Client;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {

    private Long id;

    private String nom;

    private boolean actif;

    private String code;

    private String prenom ;

    private Adresse adresse;

    private String photo;

    private String numTel;

    private Integer id_Entreprise;

    public static ClientDto fromEntity(Client client){
        if(client == null){
            return null;
        }

        return ClientDto.builder()
                .id(client.getId())
                .actif(client.isActif())
                .nom(client.getNom())
                .code(client.getCode())
                .adresse(client.getAdresse())
                .prenom(client.getPrenom())
                .photo(client.getPhoto())
                .numTel(client.getNumTel())
                .id_Entreprise(client.getIdEntreprise())
                .build();
    }

    public static Client toEntity(ClientDto clientDto) {
        if (clientDto == null) {
            return null;
            //TODO throw exception
        }

        Client client =new Client();
        client.setId(clientDto.getId());
        client.setNom(clientDto.getNom());
        client.setCode(clientDto.getCode());
        client.setAdresse(clientDto.getAdresse());
        client.setActif(clientDto.isActif());
        client.setPrenom(clientDto.getPrenom());
        client.setNumTel(clientDto.getNumTel());
        return client;
    }
}

