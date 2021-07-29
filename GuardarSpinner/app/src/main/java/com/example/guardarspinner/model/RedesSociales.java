package com.example.guardarspinner.model;

public class RedesSociales {
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
