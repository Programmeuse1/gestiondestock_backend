package com.stage.gestiondestock_backend.dto;

import com.stage.gestiondestock_backend.model.Adresse;
import com.stage.gestiondestock_backend.model.Client;
import lombok.*;

import java.time.LocalDateTime;

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

    private String email;

    private LocalDateTime dateEnregistrement;

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
                .dateEnregistrement(client.getDateEnregistrement())
                .actif(client.isActif())
                .nom(client.getNom())
                .code(client.getCode())
                .email(client.getEmail())
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
        client.setDateEnregistrement(clientDto.getDateEnregistrement());
        client.setNom(clientDto.getNom());
        client.setCode(clientDto.getCode());
        client.setEmail(clientDto.getEmail());
        client.setAdresse(clientDto.getAdresse());
        client.setActif(clientDto.isActif());
        client.setPrenom(clientDto.getPrenom());
        client.setNumTel(clientDto.getNumTel());
        return client;
    }
}

