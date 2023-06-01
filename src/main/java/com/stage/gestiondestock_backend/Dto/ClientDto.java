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
                .nom(client.getNom())
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
        return Client.builder()
                .nom(clientDto.getNom())
                .prenom(clientDto.getPrenom())
                .photo(clientDto.getPhoto())
                .numTel(clientDto.getNumTel())
                .build();
    }
}

