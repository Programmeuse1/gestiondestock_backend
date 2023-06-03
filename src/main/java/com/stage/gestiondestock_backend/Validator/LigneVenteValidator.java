package com.stage.gestiondestock_backend.Validator;

import com.stage.gestiondestock_backend.dto.LigneVenteDto;

import java.util.ArrayList;
import java.util.List;

public class LigneVenteValidator {

    public static List<String> validate(LigneVenteDto ligneVenteDto) {
        List<String> errors = new ArrayList<>();

        if (ligneVenteDto.getId() == null) {
            errors.add("veuillez renseignez l'identifiant de la ligne commande vente");
        }
        if (ligneVenteDto.getArticle() == null) {
            errors.add("veuillez renseignez l'article de la ligne commande vente");
        }
        if (ligneVenteDto.getVente() == null) {
            errors.add("veuillez renseignez la commande client de la ligne commande vente");
        }
        if (ligneVenteDto.getQuantite() == null) {
            errors.add("veuillez renseignez la quantite de la commande vente");
        }
        return errors;
    }
}
