package com.stage.gestiondestock_backend.repository.specification;

import com.stage.gestiondestock_backend.model.Client;
import com.stage.gestiondestock_backend.service.criteria.ClientCriteria;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

public class ClientSpecification {

    private static Specification<Client> withNom(String nom){
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("nom"),"%".concat(nom).concat("%"));
    }

    private static Specification<Client> withActif(Boolean actif){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("actif"),actif);
    }

    private static Specification<Client> withCode(String code){
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("code"),"%".concat(code).concat("%"));
    }

    public static Specification<Client> getClient(ClientCriteria clientCriteria){

        Specification<Client> clientSpecification = (root, query, criteriaBuilder) -> null;

        if (clientCriteria.getNom() != null){
            clientSpecification = Objects.requireNonNull(Specification.where(clientSpecification)).and(withNom(clientCriteria.getNom()));
        }

        if (clientCriteria.getCode() != null){
            clientSpecification = Objects.requireNonNull(Specification.where(clientSpecification)).and(withCode(clientCriteria.getCode()));
        }

        if (Boolean.TRUE.equals(clientCriteria.getActif()) || Boolean.FALSE.equals(clientCriteria.getActif())){
            clientSpecification = Objects.requireNonNull(Specification.where(clientSpecification)).and(withActif(clientCriteria.getActif()));
        }

        return clientSpecification;
    }
}
