package com.stage.gestiondestock_backend.controller.Api;

import com.stage.gestiondestock_backend.Dto.auth.AuthenticationRequest;
import com.stage.gestiondestock_backend.Dto.auth.AuthenticationResponse;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import static com.stage.gestiondestock_backend.utils.Constants.AUTHENTICATION_ENDPOINT;


@Api("authApi")
public interface AuthenticationControllerApi {
    @PostMapping(value=AUTHENTICATION_ENDPOINT+"/authenticate", consumes= MediaType.APPLICATION_JSON_VALUE ,produces= MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) ;

}
