package com.stage.gestiondestock_backend.dto;

import com.stage.gestiondestock_backend.model.CommandeFournisseur;
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
public class CommandeFournisseurDto {

    private Long id;

    private EtatCommande etatCommande;

    private String code;

    private LocalDateTime dateEnregistrement;

    private Instant dateCommande;

    private Integer idEntreprise;

    private FournisseurDto fournisseur;

    private List<LigneCommandeFournisseurDto> ligneCommandeFournisseurs;

    public static CommandeFournisseurDto fromEntity(CommandeFournisseur commandeFournisseur){
        if(commandeFournisseur == null){
            return null;
        }

        return CommandeFournisseurDto.builder()
                .id(commandeFournisseur.getId())
                .dateEnregistrement(commandeFournisseur.getDateEnregistrement())
                .etatCommande(commandeFournisseur.getEtatCommande())
                .code(commandeFournisseur.getCode())
                .idEntreprise(commandeFournisseur.getIdEntreprise())
                .fournisseur(FournisseurDto.fromEntity(commandeFournisseur.getFournisseur()))
                .build();
    }

    public static CommandeFournisseur toEntity(CommandeFournisseurDto dto) {
        if (dto== null) {
            return null;
            //TODO throw exception
        }
        CommandeFournisseur commandeFournisseur =new CommandeFournisseur();
        commandeFournisseur.setId(dto.getId());
        commandeFournisseur.setDateEnregistrement(dto.getDateEnregistrement());
        commandeFournisseur.setEtatCommande(dto.getEtatCommande());
        commandeFournisseur.setCode(dto.getCode());
        commandeFournisseur.setIdEntreprise(dto.getIdEntreprise());
        return commandeFournisseur;
    }
}
