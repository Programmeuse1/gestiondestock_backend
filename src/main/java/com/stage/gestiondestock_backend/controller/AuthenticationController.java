package com.stage.gestiondestock_backend.controller;

import com.stage.gestiondestock_backend.Dto.auth.AuthenticationRequest;
import com.stage.gestiondestock_backend.Dto.auth.AuthenticationResponse;
import com.stage.gestiondestock_backend.controller.api.AuthenticationControllerApi;
import com.stage.gestiondestock_backend.model.auth.ExtendedUser;
import com.stage.gestiondestock_backend.service.auth.ApplicationUserDetailsService;
import com.stage.gestiondestock_backend.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController implements AuthenticationControllerApi {

    @Autowired
    private ApplicationUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){

        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getLogin());

        final String jwt = jwtUtil.generateToken((ExtendedUser) userDetails);

        return ResponseEntity.ok(AuthenticationResponse.builder().accessToken(jwt).build());
    }
}
