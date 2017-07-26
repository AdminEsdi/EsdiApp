package com.example.usuario.webesdi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.usuario.webesdi.Tutorial.tutoriaV2;
import com.example.usuario.webesdi.empresas.Empresas;


public class MenuPrincipal extends BaseActivity {
    ImageView btnpaginaweb;
    ImageView btnContacto;
    ImageView btnMensajes;
    ImageView btndispoAulas;
    ImageView btnIncidencias;
    ImageView btnEmpresas;
    ImageView btnTutoriales;
    ImageView btnServicios;
    ImageView btnCafeteria;
    TextView textAluas,textMensajes,txtServeis,textView4;
    String email;
    String nombre;
    String rol;
    Bundle b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        setTitle(getResources().getText(R.string.titMenuP));                                                                        //De esta manera cambiamos el titulo del marco superior.

        Intent Mainact = getIntent();

        b = Mainact.getExtras();

        //extraemos datos del bundle
        email = b.getString("email");
        nombre = b.getString("nombre");
        rol = b.getString("rol");

        //buscamos las ImageViews que usaremos como boton
        btnpaginaweb = (ImageView) findViewById(R.id.btnpaginaweb);
        btnContacto = (ImageView) findViewById(R.id.btnContacto);
        btnMensajes = (ImageView) findViewById(R.id.btnMensajes);
        btndispoAulas = (ImageView) findViewById(R.id.btndispoAulas);
        btnIncidencias = (ImageView) findViewById(R.id.btnIncidencias);
        btnEmpresas = (ImageView) findViewById(R.id.btnEmpresas);
        btnTutoriales = (ImageView) findViewById(R.id.btnTutoriales);
        btnServicios = (ImageView) findViewById(R.id.btnServicios);
        btnCafeteria = (ImageView) findViewById(R.id.btnCafe);
        textAluas = (TextView) findViewById(R.id.textAluas);
        textMensajes = (TextView) findViewById(R.id.textMensajes);
        txtServeis = (TextView) findViewById(R.id.txtServeis);
        textView4 = (TextView) findViewById(R.id.textIncidencies);
        //btnVacio = (Button) findViewById(R.id.btnVacio);textAluas,textMensajes,txtServeis,textView4;
        //btnCancelar = (ImageView) findViewById(R.id.algo);

        //el boton hace un setOnClickListenter y le damos el metodo concreto para que encuentre donde ha de ir
        btnpaginaweb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                lanzarPaginaWeb();

                }
        });
        btnContacto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                lanzarContacto();

            }
        });
        btnMensajes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                lanzarMensajes3();

            }
        });
        btndispoAulas.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                lanzarDispo();

            }
        });
        btnIncidencias.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                lanzarIncidencias();

            }
        });
        btnTutoriales.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                lanzarTutoriales();

            }
        });
        btnEmpresas.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                lanzarEmpresas();
            }
        });
        btnServicios.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                lanzarServicios();
            }
        });

        btnCafeteria.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                lanzarCafeteria();
            }
        });

        /*btnCancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                lanzarEmpresas();
            }
        });*/

        //solo un administrador puede entrar a incidencias, y los invitados no pueden entrar a dispoaulas ni servicios y a mensajes
        if (rol.equalsIgnoreCase("Administrador")){
            btnIncidencias.setVisibility(View.VISIBLE);
            textView4.setVisibility(View.VISIBLE);
        }else if(rol.equalsIgnoreCase("Invitado")){
            btndispoAulas.setVisibility(View.INVISIBLE);
            btnServicios.setVisibility(View.INVISIBLE);
            btnMensajes.setVisibility(View.INVISIBLE);

            textAluas.setVisibility(View.INVISIBLE);
            textMensajes.setVisibility(View.INVISIBLE);
            txtServeis.setVisibility(View.INVISIBLE);
        }

    }

    //metodos para entrar a una nueva activity
    private void lanzarPaginaWeb(){
        Intent intent = new Intent(MenuPrincipal.this,PaginaWeb.class);
        intent.putExtras(b);
        startActivity(intent);
    }
    private void lanzarContacto(){
        Intent intent = new Intent(MenuPrincipal.this,Contacto.class);
        intent.putExtras(b);
        startActivity(intent);
    }

    private void lanzarMensajes3(){
       // Intent intent = new Intent(MenuPrincipal.this,Mensajes.class);
        Intent intent = new Intent(MenuPrincipal.this,Mensajes.class);
        intent.putExtras(b);
        startActivity(intent);
    }
    private void lanzarDispo(){
        Intent intent = new Intent(MenuPrincipal.this,DispoAulas.class);
        intent.putExtras(b);
        startActivity(intent);
    }

    private void lanzarIncidencias(){
        Intent intent = new Intent(MenuPrincipal.this,GLPI.class);
        intent.putExtras(b);
        startActivity(intent);
    }

    private void lanzarEmpresas(){
        Intent intent = new Intent(MenuPrincipal.this,Empresas.class);
        intent.putExtras(b);
        startActivity(intent);
    }

    private void lanzarTutoriales(){
        Intent intent = new Intent(MenuPrincipal.this,tutoriaV2.class);
        intent.putExtras(b);
        startActivity(intent);
    }

    private void lanzarServicios(){
        Intent intent = new Intent(MenuPrincipal.this,EstadoServicios.class);
        intent.putExtras(b);
        startActivity(intent);
    }

    private void lanzarCafeteria(){
        Intent intent = new Intent(MenuPrincipal.this,Cafeteria.class);
        intent.putExtras(b);
        startActivity(intent);
    }

}
