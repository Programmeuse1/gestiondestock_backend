package com.stage.gestiondestock_backend.controller;

import com.stage.gestiondestock_backend.Dto.auth.AuthenticationRequest;
import com.stage.gestiondestock_backend.Dto.auth.AuthenticationResponse;
import com.stage.gestiondestock_backend.model.auth.ExtendedUser;
import com.stage.gestiondestock_backend.service.auth.ApplicationUserDetailsService;
import com.stage.gestiondestock_backend.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.stage.gestiondestock_backend.utils.Constants.AUTHENTICATION_ENDPOINT;

@RestController
@RequestMapping(AUTHENTICATION_ENDPOINT)
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ApplicationUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
//        System.out.println(" authenticate: \n" + request.toString() +"\n");
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getLogin(),
//                        request.getPassword()
//                )
//        );
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getLogin());

        final String jwt = jwtUtil.generateToken((ExtendedUser) userDetails);

        return ResponseEntity.ok(AuthenticationResponse.builder().accessToken(jwt).build());
    }
}