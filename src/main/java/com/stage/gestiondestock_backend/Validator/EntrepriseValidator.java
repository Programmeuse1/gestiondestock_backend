package com.stage.gestiondestock_backend.Validator;

import com.stage.gestiondestock_backend.Dto.EntrepriseDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EntrepriseValidator {

    public static List<String> validate(EntrepriseDto entrepriseDto) {
        List<String> errors = new ArrayList<>();

        if(entrepriseDto==null){
            errors.add("veuillez renseignez le nom de l'entreprise");
            errors.add("veuillez renseignez l'email de l'entreprisee");
           errors.add("veuillez renseignez la description de l'entreprise");
           errors.add("veuillez renseignez le code fiscale de l'entreprise");
           errors.add("veuillez renseignez le numero de telephone de l'entreprise");
            return errors;
        }

        if (!StringUtils.hasLength(entrepriseDto.getNom())) {
            errors.add("veuillez renseignez le nom de l'entreprise");
        }
        if (!StringUtils.hasLength(entrepriseDto.getEmail())) {
            errors.add("veuillez renseignez l'email de l'entreprise");
        }
        if (!StringUtils.hasLength(entrepriseDto.getCodeFiscal())) {
            errors.add("veuillez renseignez le code fiscale de l'entreprise");
        }

        if (!StringUtils.hasLength(entrepriseDto.getDescription())) {
            errors.add("veuillez renseignez la description de l'entreprise");
            return errors;
        }
        if (!StringUtils.hasLength(entrepriseDto.getNumTel())) {
            errors.add("veuillez renseignez le numero de telephone de l'entreprise");
        }
        return errors;
    }
}
