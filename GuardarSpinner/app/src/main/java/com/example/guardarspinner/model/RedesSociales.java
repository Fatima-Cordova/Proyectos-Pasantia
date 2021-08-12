package com.example.guardarspinner.model;

import java.io.Serializable;

public class RedesSociales implements Serializable {
    private String nombreSocial = "";
    private int idSocial;

    public RedesSociales(String nombreSocial, int idSocial) {
        this.nombreSocial = nombreSocial;
        this.idSocial = idSocial;
    }

    public String getNombreSocial() {
        return nombreSocial;
    }

    public void setNombreSocial(String nombreSocial) {
        this.nombreSocial = nombreSocial;
    }

    public int getIdSocial() {
        return idSocial;
    }

    public void setIdSocial(int idSocial) {
        this.idSocial = idSocial;
    }
}
