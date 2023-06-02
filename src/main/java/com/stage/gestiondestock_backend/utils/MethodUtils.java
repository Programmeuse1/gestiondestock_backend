package com.stage.gestiondestock_backend.utils;

import com.google.common.base.Strings;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.lang.reflect.Field;

public class MethodUtils {

    public static Pageable findAllByCriteria(boolean classement, String typeClassement, Integer nombreDeResultat, Object criteria) {
        Sort sort;
        if(Strings.isNullOrEmpty(typeClassement)) {
            typeClassement = "id";
        }
        if(Boolean.FALSE.equals(classement)) {
            sort = Sort.by(typeClassement).descending();
        } else {
            sort = Sort.by(typeClassement).ascending();
        }

        try {
            Field field = criteria.getClass().getDeclaredField("typeClassement");
            field.setAccessible(true);
            field.set(criteria, typeClassement);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return PageRequest.of(0, nombreDeResultat == null || nombreDeResultat <0
                ? Integer.MAX_VALUE : nombreDeResultat, sort);

    }

    public static String format(Integer value, int numberCharMax) {
        String v = value.toString();
        StringBuilder fixe = new StringBuilder();
        if (v.length() <= numberCharMax) {
            int nbreZero = numberCharMax - v.length();
            fixe.append("0".repeat(nbreZero));
            fixe.append(v);
        } else {
            fixe = new StringBuilder(v);
        }
        return fixe.toString();
    }
}
