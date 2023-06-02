package com.stage.gestiondestock_backend.Validator;

import com.stage.gestiondestock_backend.Dto.ArticleDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ArticleValidator {

    public static List<String> validate(ArticleDto articleDto) {
        List<String> errors = new ArrayList<>();

        if(articleDto==null){
            errors.add("veuillez renseignez le code l'article");
            errors.add("veuillez renseignez la designation de l'article");
            errors.add("veuillez renseignez le prix unitaire ht de l'article");
            errors.add("veuillez renseignez le taux tva de l'article");
            return errors;
        }

        /*if (!StringUtils.hasLength(articleDto.getCode())) {
            errors.add("veuillez renseignez le code l'article");
        }*/
        if (!StringUtils.hasLength(articleDto.getDesignation())) {
            errors.add("veuillez renseignez la designation de l'article");
        }
        /*if (articleDto.getAdresse()== null) {
            errors.add("veuillez renseignez l'adresse du client du client");
        }*/

        /*if (articleDto.getPrixUnitaireTtc()== null) {
            errors.add("veuillez renseignez le prix unitaire ht de l'article");
        }*/
        if (articleDto.getTauxTva()== null) {
            errors.add("veuillez renseignez le taux tva de l'article");
        }
//        if (articleDto.getIdEntreprise()== null) {
//            errors.add("veuillez renseignez l'id de l'entreprise");
//        }
        return errors;
    }
}
