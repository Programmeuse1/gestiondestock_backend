package com.stage.gestiondestock_backend.service;

import com.stage.gestiondestock_backend.dto.VentesDto;
import java.util.List;

public interface VentesService {

    VentesDto save(VentesDto dto);

    VentesDto findById(Long id);

    VentesDto findByCode(String code);

    List<VentesDto> findAll();

    void delete(Long id);
}
