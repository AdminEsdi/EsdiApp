package com.example.usuario.webesdi.Coffe;

import com.example.usuario.webesdi.Cafeteria;

/**
 * Created by User on 10/07/2017.
 */

public class Menjar {

    private int idMenjar;
    private String nomMenjarCatala;
    private String nomMenjarCastella;
    private String nomMenjarAngles;
    private Float preuMenjar;
    private String picMenjar;

    public Menjar(int idMenjar, String nomMenjarCatala, String nomMenjarCastella, String nommenjarAngles, Float preuMenjar, String picMenjar) {
        this.idMenjar = idMenjar;
        this.nomMenjarCatala = nomMenjarCatala;
        this.nomMenjarCastella = nomMenjarCastella;
        this.nomMenjarAngles = nommenjarAngles;
        this.preuMenjar = preuMenjar;
        this.picMenjar = picMenjar;
    }

    public int getIdMenjar() {
        return idMenjar;
    }

    public void setIdMenjar(int idMenjar) {
        this.idMenjar = idMenjar;
    }

    public String getNomMenjarCatala() {
        return nomMenjarCatala;
    }

    public void setNomMenjarCatala(String nomMenjarCatala) {
        this.nomMenjarCatala = nomMenjarCatala;
    }

    public String getNomMenjarCastella() {
        return nomMenjarCastella;
    }

    public void setNomMenjarCastella(String nomMenjarCastella) {
        this.nomMenjarCastella = nomMenjarCastella;
    }

    public String getNomMenjarAngles() {
        return nomMenjarAngles;
    }

    public void setNomMenjaAngles(String nomMenjarAngles) {
        this.nomMenjarAngles = nomMenjarAngles;
    }

    public Float getPreuMenjar() {
        return preuMenjar;
    }

    public void setPreuMenjar(Float preuMenjar) {
        this.preuMenjar = preuMenjar;
    }

    public String getPicMenjar() {
        return picMenjar;
    }

    public void setPicMenjar(String picMenjar) {
        this.picMenjar = picMenjar;
    }


}
