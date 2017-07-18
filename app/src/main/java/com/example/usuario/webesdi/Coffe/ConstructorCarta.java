package com.example.usuario.webesdi.Coffe;

/**
 * Created by Becario2 on 17/07/2017.
 */

public class ConstructorCarta {

    private int id;
    private String nombreEng;
    private String nombreEsp;
    private String nombreCat;
    private String precio;
    private String descripEng;
    private String descripEsp;
    private String descripCat;

    public ConstructorCarta(int id, String nombreEng, String nombreEsp, String nombreCat, String precio, String descripEng, String descripEsp, String descripCat) {
        this.id = id;
        this.nombreEng = nombreEng;
        this.nombreEsp = nombreEsp;
        this.nombreCat = nombreCat;
        this.precio = precio;
        this.descripEng = descripEng;
        this.descripEsp = descripEsp;
        this.descripCat = descripCat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreEng() {
        return nombreEng;
    }

    public void setNombreEng(String nombreEng) {
        this.nombreEng = nombreEng;
    }

    public String getNombreEsp() {
        return nombreEsp;
    }

    public void setNombreEsp(String nombreEsp) {
        this.nombreEsp = nombreEsp;
    }

    public String getNombreCat() {
        return nombreCat;
    }

    public void setNombreCat(String nombreCat) {
        this.nombreCat = nombreCat;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getDescripEng() {
        return descripEng;
    }

    public void setDescripEng(String descripEng) {
        this.descripEng = descripEng;
    }

    public String getDescripEsp() {
        return descripEsp;
    }

    public void setDescripEsp(String descripEsp) {
        this.descripEsp = descripEsp;
    }

    public String getDescripCat() {
        return descripCat;
    }

    public void setDescripCat(String descripCat) {
        this.descripCat = descripCat;
    }
}
