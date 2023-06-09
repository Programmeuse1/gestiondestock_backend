package com.stage.gestiondestock_backend.controller;

import com.stage.gestiondestock_backend.dto.RolesDto;
import com.stage.gestiondestock_backend.controller.api.RolesApi;
import com.stage.gestiondestock_backend.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RolesController implements RolesApi {

    private RolesService rolesService;
    @Autowired
    public RolesController(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    @Override
    public RolesDto save(RolesDto dto) {
        return rolesService.save(dto);
    }

    @Override
    public RolesDto findById(Long id) {
        return rolesService.findById(id);
    }

    @Override
    public RolesDto findByRoleName(String roleName) {
        return rolesService.findByRoleName(roleName);
    }

    @Override
    public List<RolesDto> findAll() {
        return rolesService.findAll();
    }

    @Override
    public void delete(Long id) {
        rolesService.delete(id);
    }
}
