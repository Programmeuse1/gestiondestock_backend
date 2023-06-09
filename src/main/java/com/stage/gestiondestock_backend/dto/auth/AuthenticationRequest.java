package com.stage.gestiondestock_backend.dto.auth;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthenticationRequest {

    private String login;
    private String password;
}
