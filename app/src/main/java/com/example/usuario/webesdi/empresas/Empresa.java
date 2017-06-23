package com.example.usuario.webesdi.empresas;

/**
 * Created by PracticasEsdi on 28/04/2017.
 */

public class Empresa {


    private String name;
    private String descripcio;
    private int pic;
    private int rank;
    private String explicacionCompleta;

    public Empresa(String name, String descripcio, int rank, int pic){

        this.name = name;
        this.descripcio = descripcio;
        this.pic = pic;
        this.rank = rank;

    }

    public Empresa(String name, String descripcio, int rank, int pic, String fullDescr){

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
