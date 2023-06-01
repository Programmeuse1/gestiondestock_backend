package com.stage.gestiondestock_backend.service.auth;

import com.stage.gestiondestock_backend.Dto.UtilisateurDto;
import com.stage.gestiondestock_backend.model.auth.ExtendedUser;
import com.stage.gestiondestock_backend.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

    @Autowired
    private UtilisateurService service;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UtilisateurDto utilisateur = service.findByEmail(email);
//        System.out.println(email );
//        System.out.println(utilisateur.getEmail());
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        utilisateur.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName ())));

        return new ExtendedUser(utilisateur.getEmail(), utilisateur.getMotDePasse(), utilisateur.getEntreprise().getId(),new ArrayList<>());
    }
}
//Ce code est une classe utilitaire pour gérer les JSON Web Tokens (JWT)
// dans une application Spring Boot. Plus précisément,
// il s'agit d'une implémentation de l'interface UserDetailsService de Spring Security qui permet
// de charger les détails d'un utilisateur à partir de son nom d'utilisateur (email dans ce cas).
// La méthode loadUserByUsername charge les détails de l'utilisateur à partir du service UtilisateurService,
// crée une liste d'autorisations à partir des rôles de l'utilisateur,
// puis retourne un objet UserDetails qui représente l'utilisateur avec ses autorisations.
// Ce code est utilisé pour l'authentification et l'autorisation des utilisateurs dans l'application.