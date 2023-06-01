package com.stage.gestiondestock_backend.Dto;

import com.stage.gestiondestock_backend.model.CommandeClient;
import com.stage.gestiondestock_backend.model.enumeration.EtatCommande;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommandeClientDto {

    private Long id;

    private EtatCommande etatCommande;

    private String code;

    private Instant dateCommande;

    private Integer idEntreprise;

    private ClientDto client;

    private List<LigneCommandeClientDto> ligneCommandeClients;

    public static CommandeClientDto fromEntity(CommandeClient commandeClient){
        if(commandeClient == null){
            return null;
        }

        return CommandeClientDto.builder()
                .id(commandeClient.getId())
                .etatCommande(commandeClient.getEtatCommande())
                .code(commandeClient.getCode())
//                .dateCommande(commandeClient.getDateCommande())
                .idEntreprise(commandeClient.getIdEntreprise())
                .client(ClientDto.fromEntity(commandeClient.getClient()))
                .build();
    }

    public static CommandeClient toEntity(CommandeClientDto dto) {
        if (dto== null) {
            return null;
            //TODO throw exception
        }
        CommandeClient commandeClient =new CommandeClient();
        commandeClient.setId(dto.getId());
        commandeClient.setEtatCommande(dto.getEtatCommande());
        commandeClient.setCode(dto.getCode());
//        commandeClient.setDateCommande(dto.getDateCommande());
        return commandeClient;
    }

}
