package com.stage.gestiondestock_backend.Dto;

import com.stage.gestiondestock_backend.model.CommandeFournisseur;
import com.stage.gestiondestock_backend.model.enumeration.EtatCommande;
import lombok.*;

import java.time.Instant;
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
        commandeFournisseur.setEtatCommande(dto.getEtatCommande());
        commandeFournisseur.setCode(dto.getCode());
        commandeFournisseur.setIdEntreprise(dto.getIdEntreprise());
        return commandeFournisseur;
    }
}
