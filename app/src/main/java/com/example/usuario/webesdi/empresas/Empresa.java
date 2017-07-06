package com.example.usuario.webesdi.empresas;

/**
 * Created by PracticasEsdi on 28/04/2017.
 */

public class Empresa {

    private int id;
    private String name;
    private String pic;
    private String descripcio;



    public Empresa(Integer id,String name, String pic, String descripcio){
        this.id = id;
        this.name = name;
        this.pic = pic;
        this.descripcio = descripcio;
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
