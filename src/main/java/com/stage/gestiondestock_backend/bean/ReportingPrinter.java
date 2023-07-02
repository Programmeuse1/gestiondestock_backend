package com.stage.gestiondestock_backend.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReportingPrinter {

    private  String directorie;

    private  String directorieLong;
    private  String directorieLocalLong;

    private  String message;
    private  boolean statut;

    public ReportingPrinter() {
    }

    /*public ReportingPrinter(String message) {
        this.message = message;
        this.statut = true;
    }

    public ReportingPrinter(String message, boolean statut) {
        this.message = message;
        this.statut = statut;
    }*/
}
