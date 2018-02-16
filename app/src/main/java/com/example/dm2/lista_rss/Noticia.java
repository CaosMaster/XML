package com.example.dm2.lista_rss;

/**
 * Created by dm2 on 20/12/2017.
 */

public class Noticia {

    private String titulo;
    private String link;
    private String descripcion;
    private String guild;
    private String fecha;

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setGuild(String guild) {
        this.guild = guild;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTitulo() {

        return titulo;
    }

    public String getLink() {
        return link;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getGuild() {
        return guild;
    }

    public String getFecha() {
        return fecha;
    }
}
