package com.stage.gestiondestock_backend.Validator;

import com.stage.gestiondestock_backend.Dto.UtilisateurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurValidator {


    public static List<String> validate(UtilisateurDto utilisateurDto) {
        List<String> errors = new ArrayList<>();

        if(utilisateurDto==null){
            errors.add("veuillez renseignez le nom de l'utilisateur");
            errors.add("veuillez renseignez le prenom de l'utilisateur");
            errors.add("veuillez renseignez le numero de telephone de l'utilisateur");
            return errors;
        }

        if (!StringUtils.hasLength(utilisateurDto.getNom())) {
            errors.add("veuillez renseignez le nom de l'utilisateur");
        }
        /*if (cutilisateurDto.getAdresse()== null) {
            errors.add("veuillez renseignez l'adresse du client du client");
        }*/

        if (!StringUtils.hasLength(utilisateurDto.getPrenom())) {
            errors.add("veuillez renseignez le prenom de l'utilisateur");
        }
        if (!StringUtils.hasLength(utilisateurDto.getEmail())) {
            errors.add("veuillez renseignez l'email de l'utilisateur");
        }
        if (!StringUtils.hasLength(utilisateurDto.getMotDePasse())) {
            errors.add("veuillez renseignez le mot de passe de l'utilisateur");
        }
        if (!StringUtils.hasLength(utilisateurDto.getNumTel())) {
            errors.add("veuillez renseignez le numero de telephone de l'utilisateur");
        }
        return errors;
    }
}
