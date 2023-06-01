package com.stage.gestiondestock_backend.controller.Api;

import com.stage.gestiondestock_backend.Dto.VentesDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static com.stage.gestiondestock_backend.utils.Constants.VENTES_ENDPOINT;

@Api("ventesApi")
public interface VentesApi {

    @PostMapping(VENTES_ENDPOINT + "/create")
    VentesDto save(@RequestBody VentesDto dto);

    @GetMapping(VENTES_ENDPOINT +"/{idVente}")
    VentesDto findById(@PathVariable("idVente") Long id);

    @GetMapping(VENTES_ENDPOINT + "/{codeVente}")
    VentesDto findByCode(@PathVariable("codeVente") String code);

    @GetMapping(VENTES_ENDPOINT +"/all")
    List<VentesDto> findAll();

    @DeleteMapping(VENTES_ENDPOINT + "/delete/{idVente}")
    void delete(@PathVariable("idVente") Long id);
}
