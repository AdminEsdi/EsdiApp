package com.example.usuario.webesdi;

/**
 * Created by JR on 27/04/2017.
 */

public class MensajesIncidencias {
    String Equipo;
    String Fecha;
    String titulo;
    String descripcion;
    String correo;
    String nodo;


    public MensajesIncidencias(String equipo, String fecha, String titulo, String descripcion, String correo, String nodo) {
        Equipo = equipo;
        Fecha = fecha;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.correo = correo;
        this.nodo = nodo;
    }

    public String getEquipo() {
        return Equipo;
    }

    public void setEquipo(String equipo) {
        Equipo = equipo;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNodo() {
        return nodo;
    }

    public void setNodo(String nodo) {
        this.nodo = nodo;
    }
}
