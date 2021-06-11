package com.example.recycleviewv2.model;

public class RedesSociales {

    private String nombre;
    private int imagen;


    public RedesSociales(String nombre, int imagen) {
        this.nombre = nombre;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFoto() {
        return imagen;
    }

    public void setFoto(int foto) {
        this.imagen = foto;
    }
}