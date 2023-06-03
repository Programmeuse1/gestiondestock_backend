package com.stage.gestiondestock_backend.Validator;

import com.stage.gestiondestock_backend.dto.LigneCommandeClientDto;

import java.util.ArrayList;
import java.util.List;

public class LigneCommandeClientValidator {

    public static List<String> validate(LigneCommandeClientDto ligneCommandeClientDto) {
        List<String> errors = new ArrayList<>();

        if (ligneCommandeClientDto.getId() == null) {
            errors.add("veuillez renseignez l'identifiant de la ligne commande client");
        }
        if (ligneCommandeClientDto.getArticle() == null) {
            errors.add("veuillez renseignez l'article de la ligne commande client");
        }
        if (ligneCommandeClientDto.getCommandeClient() == null) {
            errors.add("veuillez renseignez la commande client de la ligne commande client");
        }
        if (ligneCommandeClientDto.getQuantite() == null) {
            errors.add("veuillez renseignez la quantite de la commande client");
        }
        return errors;
    }
}
