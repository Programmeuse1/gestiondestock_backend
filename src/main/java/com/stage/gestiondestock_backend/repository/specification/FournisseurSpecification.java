package com.stage.gestiondestock_backend.repository.specification;

import com.stage.gestiondestock_backend.model.Fournisseur;
import com.stage.gestiondestock_backend.service.criteria.FournisseurCriteria;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

public class FournisseurSpecification {

    private static Specification<Fournisseur> withNom(String nom){
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("nom"),"%".concat(nom).concat("%"));
    }

    private static Specification<Fournisseur> withActif(Boolean actif){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("actif"),actif);
    }

    private static Specification<Fournisseur> withCode(String code){
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("code"),"%".concat(code).concat("%"));
    }

    public static Specification<Fournisseur> getFournisseur(FournisseurCriteria fournisseurCriteria){

        Specification<Fournisseur> fournisseurSpecification = (root, query, criteriaBuilder) -> null;

        if (fournisseurCriteria.getNom() != null){
            fournisseurSpecification = Objects.requireNonNull(Specification.where(fournisseurSpecification)).and(withNom(fournisseurCriteria.getNom()));
        }

        if (fournisseurCriteria.getCode() != null){
            fournisseurSpecification = Objects.requireNonNull(Specification.where(fournisseurSpecification)).and(withCode(fournisseurCriteria.getCode()));
        }

        if (Boolean.TRUE.equals(fournisseurCriteria.getActif()) || Boolean.FALSE.equals(fournisseurCriteria.getActif())){
            fournisseurSpecification = Objects.requireNonNull(Specification.where(fournisseurSpecification)).and(withActif(fournisseurCriteria.getActif()));
        }

        return fournisseurSpecification;
    }
}
