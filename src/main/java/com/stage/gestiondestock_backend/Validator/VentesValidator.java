package com.stage.gestiondestock_backend.Validator;

import com.stage.gestiondestock_backend.Dto.VentesDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class VentesValidator {

    public static List<String> validate(VentesDto ventesDto) {
        List<String> errors = new ArrayList<>();

        if (ventesDto.getId() == null) {
            errors.add("veuillez renseignez l'identifiant");
        }
        if (!StringUtils.hasLength(ventesDto.getCode())) {
            errors.add("veuillez renseignez le code de vente");
        }
        if (ventesDto.getDatevente() == null) {
            errors.add("veuillez renseignez la date de vente");
        }
            return errors;
    }
}
