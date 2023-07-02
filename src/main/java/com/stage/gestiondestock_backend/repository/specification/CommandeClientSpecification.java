package com.stage.gestiondestock_backend.repository.specification;

import com.stage.gestiondestock_backend.model.Client;
import com.stage.gestiondestock_backend.model.CommandeClient;
import com.stage.gestiondestock_backend.service.criteria.CommandeClientCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import java.util.Objects;

public class CommandeClientSpecification {

    private static Specification<CommandeClient> withActif(Boolean actif){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("actif"),actif);
    }

    private static Specification<CommandeClient> withCode(String code){
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("code"),"%".concat(code).concat("%"));
    }

    private static Specification<CommandeClient> withNomClient(String nomClient) {
        return (root, query, criteriaBuilder) -> {
            Join<CommandeClient, Client> clientJoin = root.join("client");
            return criteriaBuilder.like(clientJoin.get("nom"), "%".concat(nomClient).concat("%"));
        };
    }

    private static Specification<CommandeClient> withPhoneClient(String phoneClient) {
        return (root, query, criteriaBuilder) -> {
            Join<CommandeClient, Client> clientJoin = root.join("client");
            return criteriaBuilder.like(clientJoin.get("numTel"), "%".concat(phoneClient).concat("%"));
        };
    }

    public static Specification<CommandeClient> getCommandeClient(CommandeClientCriteria commandeClientCriteria){

        Specification<CommandeClient> commandeClientSpecification = (root, query, criteriaBuilder) -> null;

        if (commandeClientCriteria.getCode() != null){
            commandeClientSpecification = Objects.requireNonNull(Specification.where(commandeClientSpecification)).and(withCode(commandeClientCriteria.getCode()));
        }

        if (Boolean.TRUE.equals(commandeClientCriteria.getActif()) || Boolean.FALSE.equals(commandeClientCriteria.getActif())){
            commandeClientSpecification = Objects.requireNonNull(Specification.where(commandeClientSpecification)).and(withActif(commandeClientCriteria.getActif()));
        }

        if (commandeClientCriteria.getNomClient() != null){
            commandeClientSpecification = Objects.requireNonNull(Specification.where(commandeClientSpecification)).and(withNomClient(commandeClientCriteria.getNomClient()));
        }

        if (commandeClientCriteria.getPhoneClient() != null){
            commandeClientSpecification = Objects.requireNonNull(Specification.where(commandeClientSpecification)).and(withPhoneClient(commandeClientCriteria.getPhoneClient()));
        }

        return commandeClientSpecification;
    }
}
