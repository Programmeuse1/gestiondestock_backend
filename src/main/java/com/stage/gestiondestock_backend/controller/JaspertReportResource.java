package com.stage.gestiondestock_backend.controller;

import com.stage.gestiondestock_backend.bean.ReportingPrinter;
import com.stage.gestiondestock_backend.controller.api.JaspertReportApi;
import com.stage.gestiondestock_backend.service.jasper.JasperService;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequiredArgsConstructor
public class JaspertReportResource implements JaspertReportApi {

    private final JasperService jasperService;

    @Override
    public ReportingPrinter printFactureClient(String codeUser) throws SQLException, JRException {
        return jasperService.printerCommandeClient(codeUser);
    }


}
