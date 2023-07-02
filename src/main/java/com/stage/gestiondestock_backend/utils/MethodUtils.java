package com.stage.gestiondestock_backend.utils;

import com.google.common.base.Strings;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Calendar;

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

    public static String getPrefixDocumentByDate() {
        LocalDate today = LocalDate.now();
        Calendar c = Calendar.getInstance();
        String p = (today.getYear() + "").substring(2);
        p += format(today.getMonthValue(), 2);
        p += format(today.getDayOfMonth(), 2);
        p += "_";
        p += format(c.get(Calendar.HOUR_OF_DAY), 2);
        p += format(c.get(Calendar.MINUTE), 2);
        p += format(c.get(Calendar.SECOND), 2);
        p += "_";
        p += RandomStringUtils.randomNumeric(6);
        return p;
    }
}
