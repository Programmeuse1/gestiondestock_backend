package com.stage.gestiondestock_backend.repository.specification;

import com.stage.gestiondestock_backend.model.Utilisateur;
import com.stage.gestiondestock_backend.service.criteria.UtilisateurCriteria;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

public class UtilisateurSpecification {

    private static Specification<Utilisateur> withNom(String nom){
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("nom"),"%".concat(nom).concat("%"));
    }

    private static Specification<Utilisateur> withActif(Boolean actif){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("actif"),actif);
    }

    private static Specification<Utilisateur> withCode(String code){
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("code"),"%".concat(code).concat("%"));
    }

    public static Specification<Utilisateur> getUtilisateur(UtilisateurCriteria utilisateurCriteria){

        Specification<Utilisateur> utilisateurSpecification = (root, query, criteriaBuilder) -> null;

        if (utilisateurCriteria.getNom() != null){
            utilisateurSpecification = Objects.requireNonNull(Specification.where(utilisateurSpecification)).and(withNom(utilisateurCriteria.getNom()));
        }

        if (utilisateurCriteria.getCode() != null){
            utilisateurSpecification = Objects.requireNonNull(Specification.where(utilisateurSpecification)).and(withCode(utilisateurCriteria.getCode()));
        }

        if (Boolean.TRUE.equals(utilisateurCriteria.getActif()) || Boolean.FALSE.equals(utilisateurCriteria.getActif())){
            utilisateurSpecification = Objects.requireNonNull(Specification.where(utilisateurSpecification)).and(withActif(utilisateurCriteria.getActif()));
        }

        return utilisateurSpecification;
    }
}
