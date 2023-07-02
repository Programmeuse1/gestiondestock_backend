package com.stage.gestiondestock_backend.controller.api;


import com.stage.gestiondestock_backend.bean.ReportingPrinter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.SQLException;

import static com.stage.gestiondestock_backend.utils.Constants.APP_ROOT;

@Api("jaspertReportApi")
public interface JaspertReportApi {

    @ApiOperation(value = "Return printer appro information from its ID and Language",
            notes = "Retrieves a printer appro based on the given ID and Language", response = ReportingPrinter.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Appro printer information returned successfully")
    })
    @GetMapping(value = APP_ROOT +"/jaspertReport/printCommandeClient/{codeCommande}", produces = MediaType.APPLICATION_JSON_VALUE)
    ReportingPrinter printFactureClient(@PathVariable("codeCommande") String codeUser) throws SQLException, JRException;

}
