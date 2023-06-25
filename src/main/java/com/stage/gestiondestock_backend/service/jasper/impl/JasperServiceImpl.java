package com.stage.gestiondestock_backend.service.jasper.impl;

import com.stage.gestiondestock_backend.bean.ReportingPrinter;
import com.stage.gestiondestock_backend.config.ApplicationProperties;
import com.stage.gestiondestock_backend.model.CommandeClient;
import com.stage.gestiondestock_backend.repository.CommandeClientRepository;
import com.stage.gestiondestock_backend.service.jasper.JasperService;
import com.stage.gestiondestock_backend.utils.MethodUtils;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class JasperServiceImpl implements JasperService {

    private final ApplicationProperties applicationProperties;
    private final DataSource dataSource;

    private Connection con = null;

    private Map<String, Object> parameters = new HashMap<>();

    private static final String FOLDERJASPER = "jasperReports/";
    private static final String POWERED = "Powered by SKYSOFT - Tel : 676 48 02 43";
    private static final String POWEREDBY = "powered";
    private static final String FILECOMMANDECLIENT = "commandeclient.jasper";

    private void addInfo(Map<String, Object> parameters) {
        parameters.clear();
        String chemin = applicationProperties.getResources().getLocationPdfFile();
        parameters.put("Path", chemin + "/");
        /*parameters.put("logo1", Objects.requireNonNull(getClass().getClassLoader().getResource("images/logo_1.jpeg")).toString());
        parameters.put("logo2", Objects.requireNonNull(getClass().getClassLoader().getResource("images/logo_2.jpeg")).toString());*/
    }

    @Override
    public ReportingPrinter printerCommandeClient(String codeUser) throws SQLException, JRException {

        System.out.println("\n\ncodeUser: "+codeUser+"\n");

        ReportingPrinter r = new ReportingPrinter();
        addInfo(parameters);
        con = getConnection();

        parameters.put("codeUser", codeUser);
//        setParameterpowered();
        InputStream path = getFileInputStream(FILECOMMANDECLIENT);
        String fileName = "facture_client_" + MethodUtils.getPrefixDocumentByDate() + ".pdf";

        /*Optional<CommandeClient> commandeClient = commandeClientRepository.findById(idCommande.longValue());
        if (commandeClient.isPresent()){
            *//*parameters.put("codeUser", commandeClient.get().getId());
            setParameterpowered();*//*
        }*/
//        ReportingPrinter rep = getPrinter(r, fileName, path);
        return getPrinter(r, fileName, path);

    }

    private ReportingPrinter getPrinter(ReportingPrinter r, String fileName, InputStream path) throws JRException {
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(path);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, con);
        r.setDirectorie(fileName);
        r.setStatut(true);
        r.setDirectorieLocalLong(String.format("%s%s", applicationProperties.getUpload().getResourcesServerStore(), fileName));
        r.setDirectorieLong(String.format("%s%s", applicationProperties.getUpload().getResourcesServerStoreUrl(), fileName));
        r.setMessage("Impression du fichier PDF");
        JasperExportManager.exportReportToPdfFile(jasperPrint, String.format("%s%s", applicationProperties.getUpload().getResourcesServerStore(), fileName));
        try {
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

    private InputStream getFileInputStream(String nameFile) {
        return getClass().getClassLoader().getResourceAsStream(FOLDERJASPER + nameFile);
    }

    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    private void setParameterpowered() {
        parameters.put(POWEREDBY, POWERED);
    }

}
