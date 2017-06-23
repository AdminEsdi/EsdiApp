package com.example.usuario.webesdi.tutoriales;

/**
 * Created by Becario2 on 05/05/2017.
 */

public class Tutorial {

    private String name;
    private String descripcio;
    private int pic;
    private int rank;
    private String explicacionCompleta;

    public Tutorial(String name, String descripcio, int rank, int pic){

        this.name = name;
        this.descripcio = descripcio;
        this.pic = pic;
        this.rank = rank;

    }

    public Tutorial(String name, String descripcio, int rank, int pic, String fullDescr){

        this.name = name;
        this.descripcio = descripcio;
        this.pic = pic;
        this.rank = rank;
        this.explicacionCompleta = fullDescr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }
    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
    public String getExplicacionCompleta() {
        return explicacionCompleta;
    }

    public void setExplicacionCompleta(String explicacionCompleta) {
        this.explicacionCompleta = explicacionCompleta;
    }
}
