package com.stage.gestiondestock_backend.service.jasper;

import com.stage.gestiondestock_backend.bean.ReportingPrinter;
import net.sf.jasperreports.engine.JRException;

import java.sql.SQLException;

public interface JasperService {

    ReportingPrinter printerCommandeClient(String codeUser) throws SQLException, JRException;
}
