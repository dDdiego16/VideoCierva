package com.example.videocierva;

public class Pelicula {

    String titulo;
    int año;
    String categoria;
    String foto;
    String disponible;

    public Pelicula(String titulo, int año, String categoria, String foto, String disponible) {
        this.titulo = titulo;
        this.año = año;
        this.categoria = categoria;
        this.foto = foto;
        this.disponible = disponible;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getAño() {
        return año;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getFoto() {
        return foto;
    }
    public String getDisponible() {
        return disponible;
    }

}
