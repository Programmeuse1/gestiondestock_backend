package com.stage.gestiondestock_backend.repository;

import com.stage.gestiondestock_backend.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Roles, Long> {
    Optional<Roles> findRolesByRoleName(String roleName);
}
