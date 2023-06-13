package com.stage.gestiondestock_backend.dto;

import com.stage.gestiondestock_backend.model.CommandeClient;
import com.stage.gestiondestock_backend.model.enumeration.EtatCommande;
import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;
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

    private LocalDateTime dateEnregistrement;

    private Instant dateCommande;

    private Integer idEntreprise;

    private ClientDto client;

    private List<LigneCommandeClientDto> ligneCommandeClients;

    private String observation;

    public static CommandeClientDto fromEntity(CommandeClient commandeClient){
        if(commandeClient == null){
            return null;
        }

        return CommandeClientDto.builder()
                .id(commandeClient.getId())
                .dateEnregistrement(commandeClient.getDateEnregistrement())
                .etatCommande(commandeClient.getEtatCommande())
                .code(commandeClient.getCode())
                .dateCommande(commandeClient.getDateCommande())
                .idEntreprise(commandeClient.getIdEntreprise())
                .observation(commandeClient.getObservation())
                .client(ClientDto.fromEntity(commandeClient.getClient()))
                .build();
    }

    public static CommandeClient toEntity(CommandeClientDto dto) {
        if (dto== null) {
            return null;
        }
        CommandeClient commandeClient =new CommandeClient();
        commandeClient.setId(dto.getId());
        commandeClient.setDateEnregistrement(dto.getDateEnregistrement());
        commandeClient.setEtatCommande(dto.getEtatCommande());
        commandeClient.setCode(dto.getCode());
        commandeClient.setDateCommande(dto.getDateCommande());
        commandeClient.setObservation(dto.getObservation());
        return commandeClient;
    }

    @Override
    public String toString() {
        return "CommandeClientDto{" +
                "id=" + id +
                ", etatCommande=" + etatCommande +
                ", code='" + code + '\'' +
                ", dateEnregistrement=" + dateEnregistrement +
                ", dateCommande=" + dateCommande +
                ", idEntreprise=" + idEntreprise +
                ", client=" + client +
                ", observation='" + observation + '\'' +
                '}';
    }
}
