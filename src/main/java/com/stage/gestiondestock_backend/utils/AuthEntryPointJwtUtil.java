package com.stage.gestiondestock_backend.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class AuthEntryPointJwtUtil implements AuthenticationEntryPoint {
    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwtUtil.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        logger.error("Unauthorized error: {}", authException.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized");
    }
}

//Ce code est une implémentation de l'interface AuthenticationEntryPoint de Spring Security.
// Il est utilisé pour gérer les erreurs d'authentification et rediriger les utilisateurs
// non authentifiés vers une page d'erreur personnalisée. La méthode commence est appelée
// lorsqu'une exception d'authentification se produit, elle enregistre l'erreur dans les logs
// et renvoie une réponse HTTP avec le code d'erreur 401 (non autorisé) et un message d'erreur personnalisé.
// Ce code est utilisé pour sécuriser les endpoints de l'application qui nécessitent une authentification préalable.
