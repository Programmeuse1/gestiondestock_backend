package com.stage.gestiondestock_backend.Validator;

import com.stage.gestiondestock_backend.dto.ClientDto;
import org.springframework.util.StringUtils;
import java.util.ArrayList;
import java.util.List;

public class ClientValidator {

    public static List<String> validate(ClientDto clientDto) {
        List<String> errors = new ArrayList<>();

        if(clientDto==null){
            errors.add("veuillez renseignez le nom du client");
            errors.add("veuillez renseignez le prenom du client");
            errors.add("veuillez renseignez le numero de telephone du client");
            return errors;
        }

        if (!StringUtils.hasLength(clientDto.getNom())) {
            errors.add("veuillez renseignez le nom du client");
        }
        if (clientDto.getAdresse()== null) {
            errors.add("veuillez renseignez l'adresse du client du client");
        }

        if (!StringUtils.hasLength(clientDto.getPrenom())) {
            errors.add("veuillez renseignez le prenom du client");
            return errors;
        }
        if (!StringUtils.hasLength(clientDto.getNumTel())) {
            errors.add("veuillez renseignez le numero de telephone du client");
        }
        return errors;
    }
}
