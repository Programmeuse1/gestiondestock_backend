package com.stage.gestiondestock_backend.repository;

import com.stage.gestiondestock_backend.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UtilisateurRepository  extends JpaRepository<Utilisateur, Long>, JpaSpecificationExecutor<Utilisateur> {
    Optional<Utilisateur> findUtilisateurByNom(String nom);

    @Query("select u from Utilisateur u where u.email = :email")
    Optional<Utilisateur> findUtilisateurByEmail(String email);

    Optional<Utilisateur> findByEmail(String email);
}
