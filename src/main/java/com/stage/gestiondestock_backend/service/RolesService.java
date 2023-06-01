package com.stage.gestiondestock_backend.service;

import com.stage.gestiondestock_backend.Dto.RolesDto;
import java.util.List;

public interface RolesService {

    RolesDto save(RolesDto dto);

    RolesDto findById(Long id);

    RolesDto findByRoleName(String roleName);

    List<RolesDto> findAll();

    void delete(Long id);
}
