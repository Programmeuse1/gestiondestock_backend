package com.stage.gestiondestock_backend.Validator;

import com.stage.gestiondestock_backend.dto.LigneCommandeFournisseurDto;

import java.util.ArrayList;
import java.util.List;

public class LigneCommandeFournisseurValidator {

    public static List<String> validate(LigneCommandeFournisseurDto ligneCommandeFournisseurDto) {
        List<String> errors = new ArrayList<>();

        if (ligneCommandeFournisseurDto.getId() == null) {
            errors.add("veuillez renseignez l'identifiant de la ligne commande fournisseur");
        }
        if (ligneCommandeFournisseurDto.getArticle() == null) {
            errors.add("veuillez renseignez l'article de la ligne commande fournisseur");
        }
        if (ligneCommandeFournisseurDto.getCommandeFournisseur() == null) {
            errors.add("veuillez renseignez la commande client de la ligne commande fournisseur");
        }
        if (ligneCommandeFournisseurDto.getQuantite() == null) {
            errors.add("veuillez renseignez la quantite de la commande fournisseur");
        }
        return errors;
    }
}
