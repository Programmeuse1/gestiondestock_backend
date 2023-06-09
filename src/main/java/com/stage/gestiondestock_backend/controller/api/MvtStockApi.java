package com.stage.gestiondestock_backend.controller.api;

import com.stage.gestiondestock_backend.dto.MvtStockDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static com.stage.gestiondestock_backend.utils.Constants.MVTSTOCK_ENDPOINT;

@Api("mvtstockApi")
public interface MvtStockApi {

    @PostMapping(MVTSTOCK_ENDPOINT +"/create")
    MvtStockDto save(@RequestBody MvtStockDto dto);

    @GetMapping(MVTSTOCK_ENDPOINT + "/{idMvtStock}")
    MvtStockDto findById(@PathVariable("idMvtStock") Long id);

    @GetMapping(MVTSTOCK_ENDPOINT + "/all")
    List <MvtStockDto> findAll();

    @DeleteMapping(MVTSTOCK_ENDPOINT + "/delete/{idMvtStock}")
    void delete(@PathVariable("idMvtStock") Long id);

}
