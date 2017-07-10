package com.example.usuario.webesdi.empresas;

/**
 * Created by PracticasEsdi on 28/04/2017.
 */

public class Empresa {

    private int id;
    private String name;
    private String pic;
    private String descripcioCatala;
    private String descripcioCastella;
    private String descripcioAngles;



    public Empresa(Integer id, String name, String pic, String descripcioCatala, String descripcioCastella, String descripcioAngles){
        this.id = id;
        this.name = name;
        this.pic = pic;
        this.descripcioCatala = descripcioCatala;
        this.descripcioCastella = descripcioCastella;
        this.descripcioAngles = descripcioAngles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescripcioCatala() {
        return descripcioCatala;
    }

    public void setDescripcioCatala(String descripcioCatala) {
        this.descripcioCatala = descripcioCatala;
    }
    public String getDescripcioCastella() {
        return descripcioCastella;
    }

    public void setDescripcioCastella(String descripcioCastella) {
        this.descripcioCastella = descripcioCastella;
    }
    public String getDescripcioAngles() {
        return descripcioAngles;
    }

    public void setDescripcioAngles(String descripcioAngles) {
        this.descripcioAngles = descripcioAngles;
    }


    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
