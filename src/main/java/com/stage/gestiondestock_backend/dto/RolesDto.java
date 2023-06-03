package com.stage.gestiondestock_backend.dto;

import com.stage.gestiondestock_backend.model.Roles;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RolesDto {

    private Long id;

    private String roleName;

    private LocalDateTime dateEnregistrement;

    private UtilisateurDto utilisateur;

    public static RolesDto fromEntity(Roles roles) {
        if (roles == null) {
            return null;
        }

        return RolesDto.builder()
                .id(roles.getId())
                .dateEnregistrement(roles.getDateEnregistrement())
                .roleName(roles.getRoleName())
                .build();
    }

    public static Roles toEntity(RolesDto dto) {
        if (dto == null) {
            return null;
        }
        Roles roles = new Roles();
        roles.setId(dto.getId());
        roles.setDateEnregistrement(dto.getDateEnregistrement());
        roles.setRoleName(dto.getRoleName());
        return roles;
    }
}
