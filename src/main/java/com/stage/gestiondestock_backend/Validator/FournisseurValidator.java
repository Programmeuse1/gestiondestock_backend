package com.stage.gestiondestock_backend.Validator;

import com.stage.gestiondestock_backend.dto.FournisseurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class FournisseurValidator {

    public static List<String> validate(FournisseurDto fournisseurDto) {
        List<String> errors = new ArrayList<>();

        if (fournisseurDto == null) {
            errors.add("veuillez renseignez le nom du fournisseur");
            errors.add("veuillez renseignez le prenom du fournisseur");
            errors.add("veuillez renseignez le mail du fournisseur");
            errors.add("veuillez renseignez le numero de telephone du fournisseur");
            return errors;
        }

        if (!StringUtils.hasLength(fournisseurDto.getNom())) {
            errors.add("veuillez renseignez le nom du fournisseur");
        }
        /*if (fournisseurDto.getAdresse()== null) {
            errors.add("veuillez renseignez l'adresse du fournisseur");
        }*/

        if (!StringUtils.hasLength(fournisseurDto.getPrenom())) {
            errors.add("veuillez renseignez le prenom du fournisseur");
            return errors;
        }
        if (!StringUtils.hasLength(fournisseurDto.getMail())) {
            errors.add("veuillez renseignez le mail du fournisseur");
            return errors;
        }
        if (!StringUtils.hasLength(fournisseurDto.getNumTel())) {
            errors.add("veuillez renseignez le numero de telephone du fournisseur");
        }
        return errors;
    }

}
