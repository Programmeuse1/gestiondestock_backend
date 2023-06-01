package com.stage.gestiondestock_backend.Validator;

import com.stage.gestiondestock_backend.Dto.MvtStockDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class MvtStockValidator {

    public static List<String> validate(MvtStockDto mvtStockDto) {
        List<String> errors = new ArrayList<>();

        if (mvtStockDto.getId() == null) {
            errors.add("veuillez renseignez l'identifiant du mouvement de stock");
        }
        if (mvtStockDto.getDateMvt() == null) {
            errors.add("veuillez renseignez la date du mouvement de stock");
        }
        if (mvtStockDto.getQuantite() == null) {
            errors.add("veuillez renseignez la quantite du mouvement de stock");
        }
        if (mvtStockDto.getArticle() == null) {
            errors.add("veuillez renseignez l'article du mouvement de stock");
        }
        if (mvtStockDto.getTypeMvtStk() == null) {
            errors.add("veuillez renseignez le type de mouvement de stock");
        }
        return errors;
    }
}
