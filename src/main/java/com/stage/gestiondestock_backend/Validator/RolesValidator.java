package com.stage.gestiondestock_backend.Validator;

import com.stage.gestiondestock_backend.dto.RolesDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class RolesValidator {

    public static List<String> validate(RolesDto rolesDto) {
        List<String> errors = new ArrayList<>();

        if (rolesDto.getId() == null) {
            errors.add("veuillez renseignez l'identifiant");
        }
        if (!StringUtils.hasLength(rolesDto.getRoleName())) {
            errors.add("veuillez renseignez le roleName de la vente");
        }
        return errors;
    }
}
