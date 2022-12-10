package com.example.videocierva;

public class Serie {


    String titulo;
    int año;
    int temporada;
    String categoria;
    String foto;
    String disponible;

    public Serie(String titulo, int año, int temporada, String categoria, String foto, String disponible) {
        this.titulo = titulo;
        this.año = año;
        this.temporada = temporada;
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

    public int getTemporada() {
        return temporada;
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
