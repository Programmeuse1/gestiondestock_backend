package com.stage.gestiondestock_backend.Validator;

import com.stage.gestiondestock_backend.Dto.CommandeFournisseurDto;

import java.util.ArrayList;
import java.util.List;

public class CommandeFournisseurValidator {

    public static List<String> validate(CommandeFournisseurDto commandeFournisseurDto) {
        List<String> errors = new ArrayList<>();

        if (commandeFournisseurDto.getId() == null) {
            errors.add("veuillez renseignez l'identifiant de la commande fournisseur");
        }
        if (commandeFournisseurDto.getCode() == null) {
            errors.add("veuillez renseignez le code de la commande fournisseur");
        }
//        if (commandeFournisseurDto.getDateCommande() == null) {
//            errors.add("veuillez renseignez la date de la commande fournisseur");
//        }
        if (commandeFournisseurDto.getFournisseur() == null) {
            errors.add("veuillez renseignez le fournisseur de la commande fournisseur");
        }
        return errors;
    }
}
