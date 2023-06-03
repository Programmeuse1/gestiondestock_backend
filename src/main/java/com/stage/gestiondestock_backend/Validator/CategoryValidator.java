package com.stage.gestiondestock_backend.Validator;

import com.stage.gestiondestock_backend.dto.CategoryDto;

import java.util.ArrayList;
import java.util.List;

public class CategoryValidator {

    public static List<String> validate(CategoryDto categoryDto) {
        List<String> errors = new ArrayList<>();

        if (categoryDto.getCode() == null) {
            errors.add("veuillez renseignez le code de la category");
        }
        if (categoryDto.getDesignation() == null) {
            errors.add("veuillez renseignez la designation de la category");
        }
//        if (categoryDto.getIdEntreprise() == null) {
//            errors.add("veuillez renseignez l'id de l'entreprise");
//        }
        return errors;
    }
}
