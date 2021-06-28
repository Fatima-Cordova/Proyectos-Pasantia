package com.example.notas.model;

public class Notas {
    private String descripcion, titulo;
    private long id = 0;


    public Notas(){

    }

    public Notas(String descripcion, String titulo, long id) {
        this.descripcion = descripcion;
        this.titulo = titulo;
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
