package com.stage.gestiondestock_backend.Validator;

import com.stage.gestiondestock_backend.Dto.CommandeClientDto;
import java.util.ArrayList;
import java.util.List;

public class CommandeClientValidator {

    public static List<String> validate(CommandeClientDto commandeClientDto) {
        List<String> errors = new ArrayList<>();

        if (commandeClientDto.getId() == null) {
            errors.add("veuillez renseignez l'identifiant de la commande client");
        }
        if (commandeClientDto.getCode() == null) {
            errors.add("veuillez renseignez le code de la commande client");
        }
//        if (commandeClientDto.getDateCommande() == null) {
//            errors.add("veuillez renseignez la date de la commande client");
//        }
//        if (commandeClientDto.getClient() == null) {
//            errors.add("veuillez renseignez le client");
//        }
        return errors;
    }
}
