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

    public String getNombreEsp() {
        return nombreEsp;
    }

    public String getNombreCat() {
        return nombreCat;
    }

    public String getPrecio() {
        return precio;
    }

    public String getDescripEng() {
        return descripEng;
    }

    public String getDescripEsp() {
        return descripEsp;
    }

    public String getDescripCat() {
        return descripCat;
    }
}
