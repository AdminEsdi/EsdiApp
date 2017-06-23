package com.example.usuario.webesdi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Mensajes extends BaseActivity {


    // String consulta;
    String correo;
    private static String rolMaster = "administrador"; //nombre del rol con control total
    TextView txtEmail;
    static TextView txtChat;
    EditText inChat;
    Spinner spCorreo;
    ImageButton enviar;

    TextView txtEmail2;
    static TextView txtFecha;
    static EditText inEquipo;
    static EditText inTitulo;
    static EditText inDescripcion;
    EditText inChat2;
    private static RecyclerView lstIncidencias;
    private static Button btnNuevo;
    private static Button btnCerrar;
    private static Button btnCancelar;
    private static Button btnScan;
    static ImageButton enviar2;
    static MensajesIncidencias mi;


    Calendar cal = Calendar.getInstance();
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");


    TextView txtEmail3;
    static TextView txtChat3;
    EditText inChat3;
    ImageButton enviar3;

    static Bundle b;

    List<String> datosCorreos;
    //  public static List<String> datosMensajes;

    int request_code = 1;

    private static final String TAGLOG = "firebase-db";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensajes);
        setTitle(getResources().getText(R.string.titMensajes));

        //recibe los datos de usuario a traves del bundle del intent
        Intent Mainact = getIntent();
        b = Mainact.getExtras();

        //  Log.d(TAGLOG, String.valueOf("=========================> "+b.getString("rol")+" <==============="));


        inChat = (EditText) findViewById(R.id.inChat);
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        txtChat = (TextView) findViewById(R.id.txtChat);
        enviar = (ImageButton) findViewById(R.id.imageButton);


        //   ArrayList<MensajesIncidencias> datos;
        inChat2 = (EditText) findViewById(R.id.inTitulo);
        txtEmail2 = (TextView) findViewById(R.id.txtEmail2);
        // txtChat2 = (TextView) findViewById(R.id.txtChat2);
        txtFecha = (TextView) findViewById(R.id.txtFecha);
        txtFecha.setText(dateFormat.format( cal.getTime()));
        inEquipo = (EditText) findViewById(R.id.inEquipo);
        inTitulo = (EditText) findViewById(R.id.inTitulo);
        inDescripcion = (EditText) findViewById(R.id.inDescripcion);
        enviar2 = (ImageButton) findViewById(R.id.imageButton2);
        btnNuevo = (Button) findViewById(R.id.btnNuevo);
        btnCerrar = (Button) findViewById(R.id.btnCerrar);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        btnScan = (Button) findViewById(R.id.btnScan);

        btnNuevo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                nuevo();

            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cancelar();

            }
        });
        btnScan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                escanear();
            }

        });



        //inicialmente los botones, finalizar y nuevo estan ocultos, ya que no tienen uso
        //   btnScan.setVisibility(View.GONE);
        btnCerrar.setVisibility(View.GONE);
        btnNuevo.setVisibility(View.GONE);

        //Inicialización RecyclerView
        lstIncidencias = (RecyclerView) findViewById(R.id.lstIncidencias);
        lstIncidencias.setHasFixedSize(true);


        inChat3 = (EditText) findViewById(R.id.inChat3);
        txtEmail3 = (TextView) findViewById(R.id.txtEmail3);
        txtChat3 = (TextView) findViewById(R.id.txtChat3);
        enviar3 = (ImageButton) findViewById(R.id.imageButton3);
        spCorreo = (Spinner) findViewById(R.id.spCorreo);

        correo = b.getString("email"); //inicialmente el correo es el del usuario logueado


        //bloque para dar valor a las pestañas


        TabHost tabs = (TabHost) findViewById(android.R.id.tabhost);
        tabs.setup();

        TabHost.TabSpec spec = tabs.newTabSpec("Sugerencia");
        spec.setContent(R.id.tab1);
        spec.setIndicator(getResources().getString(R.string.sugerencias));
        tabs.addTab(spec);

        spec = tabs.newTabSpec("Incidencia");
        spec.setContent(R.id.tab2);
        spec.setIndicator(getResources().getString(R.string.incidencias));
        tabs.addTab(spec);

        spec = tabs.newTabSpec("Consulta");
        spec.setContent(R.id.tab3);
        spec.setIndicator(getResources().getString(R.string.consultas));
        tabs.addTab(spec);

        tabs.setCurrentTab(0);
        //se carga inicialmente el contenido de la primera pestaña
        cargaTexto("Sugerencia");

        tabs.setOnTabChangedListener(new OnTabChangeListener() {
            public void onTabChanged(final String tabId) {
//cada vez que se selecciona una pestaña se llama al proceso para que muestre su contenido
                cargaTexto(tabId);
            }
        });
        //fin de pestañas


    }





    //metodo que recoge la pestaña seleccionada y muestra el arrayTexto segun toque
    public void cargaTexto(final String tabId) {
        DBmensajes conn = new DBmensajes(tabId);
        //switch con el resultado de la posicion seleccionadan,
        switch (tabId) {
            case "Sugerencia":

                //llama a muestratexto y dependiendo del rol muestra los mensajes
                //si es master oculta el boton enviar y muestra todos los mensajes
                if (b.getString("rol").equals(rolMaster)) {
                    enviar.setVisibility(View.GONE);
                    //  muestraMensajes("todos", tabId);
                    conn.listaMensajes("todos", tabId);

                } else {
                    //si no es master permite enviar y muestra solo sus mensajes
                    // muestraMensajes(b.getString("email").toString(), tabId);
                    conn.listaMensajes(b.getString("email").toString(), tabId);
                }
                break;
            case "Incidencia":

                conn.listaIncidencias(this);

                break;
            case "Consulta":
                //si es master llama a creaspinnercorreo para seleccionar la conversacion

                if ((b.getString("rol")).equals(rolMaster)) {
                    creaSpinnerCorreo(tabId);
                    spCorreo.setVisibility(View.VISIBLE);
                    //si no es master oculta el spiner de seleccion de conversacion, permite enviar
                    //mensajes de consulta y muestra los mensajes propios
                } else {
                    spCorreo.setVisibility(View.GONE);
                    enviar3.setVisibility(View.VISIBLE);
                    // muestraMensajes(b.getString("email").toString(), tabId);
                    conn.listaMensajes(b.getString("email").toString(), tabId);
                }

                break;
            default:
                txtEmail.setText("inválido ");
                break;
        }


        enviar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                entraTexto(tabId);
            }
        });
        enviar2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                entraTexto(tabId);
            }
        });
        enviar3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                entraTexto(tabId);

            }
        });

        btnCerrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finalizar(tabId);

            }
        });



    }


    public static void muestraIncidencias(final ArrayList<MensajesIncidencias> datos, Context ctx) {


      //  Log.d(TAGLOG, "----------------------- pasando4---------------" + datos.size());

        //instancia un objeto de la clase adaptador pasandole como parametro el array recibido con
        //los datos recibidos de la BBDD de firebase
        final MensajesAdaptador adaptador = new MensajesAdaptador(datos);


        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnScan.setVisibility(View.GONE);
                btnCancelar.setVisibility(View.GONE);
                enviar2.setVisibility(View.GONE);
                btnNuevo.setVisibility(View.VISIBLE);
                //si es un administrador se muestra el boton para finalizar incidencias
                if (b.getString("rol").equals(rolMaster)) {
                    btnCerrar.setVisibility(View.VISIBLE);
                }

                //cada vez que se hace click en un objeto del recyclerview, se instancia un objeto de la
                //clase mensajesincidencias con los valores del array datos, en la misma posicion
                //que la linea del recyclerview clickada y mediante los getters se cargan los edittext
                //y se les quita la edicion.
                mi = datos.get(lstIncidencias.getChildAdapterPosition(v));
                inEquipo.setText(mi.getEquipo());
                inEquipo.setEnabled(false);
                //inEquipo.setTextColor(black);
                inTitulo.setText(mi.getTitulo());
                inTitulo.setEnabled(false);
                // inTitulo.setTextColor(999);
                inDescripcion.setText(mi.getDescripcion());
                inDescripcion.setEnabled(false);
                // inDescripcion.setTextColor(000);
                txtFecha.setText(mi.getFecha());
                Log.i("DemoRecView", "Pulsado el elemento " + lstIncidencias.getChildAdapterPosition(v));
            }
        });

        lstIncidencias.setAdapter(adaptador);

        lstIncidencias.setLayoutManager(
                new LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false));
        lstIncidencias.addItemDecoration(
                new DividerItemDecoration(ctx, DividerItemDecoration.VERTICAL));
        lstIncidencias.setItemAnimator(new DefaultItemAnimator());

    }

    //

    public void creaSpinnerCorreo(final String miTab) {
        //definición del spinner de conversacion
        //crea una lista dinamica y se carga con los elementos recibidos de la BD firebase


        DBmensajes conn = new DBmensajes(miTab);
        datosCorreos = conn.listaCorreos();

        //creacion del adaptador del spinner de seleccion de conversaciones de consulta
        ArrayAdapter<String> adaptador2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datosCorreos);
        adaptador2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCorreo.setAdapter(adaptador2);
        // Log.d(TAGLOG, "----------------------- pasando6---------------" + datosCorreos.size());
        spCorreo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //metodo del spinner cuando se selecciona algo
            public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {
                //       Log.d(TAGLOG, "----------------------- pasando7---------------" + datosCorreos.size());
                correo = parent.getItemAtPosition(position).toString();
                txtEmail3.setText(miTab + " de " + correo);
                //     Log.d(TAGLOG, "----------------------- pasando8---------------" + datosCorreos.size());
                switch (spCorreo.getSelectedItemPosition()) {
                    case 0:
                        //si se selecciona todos, oculta el boton enviar
                        enviar3.setVisibility(View.GONE);
                        //   Toast.makeText(getApplicationContext(), "todos seleccionados "+spCorreo.getSelectedItemPosition(), Toast.LENGTH_LONG).show();
                        break;
                    default:
                        //por defecto muestra el boton enviar
                        enviar3.setVisibility(View.VISIBLE);
                        //   Toast.makeText(getApplicationContext(), "otros seleccionados "+spCorreo.getSelectedItemPosition(), Toast.LENGTH_LONG).show();
                        break;
                }
                Log.d(TAGLOG, "----------------------- pasando9---------------" + datosCorreos.size());
                //llama a muestra mensaje con el correo seleccionado a mostrar o con "todos" si no se ha seleccionado ninguno
                //muestraMensajes(correo, miTab);
                DBmensajes conn = new DBmensajes(miTab);
                conn.listaMensajes(correo, miTab);

            }

            //metodo del spinner cuando no se selecciona nada
            public void onNothingSelected(AdapterView<?> parent) {
                //  Toast.makeText(getApplicationContext(), "nada en el spinner", Toast.LENGTH_LONG).show();
            }

        });
    }


    public static void muestraMensajes(final ArrayList<String> datosMensajes, final String miTab) {

        String texto = "";


        switch (miTab) {
            case "Sugerencia":
                for (String elemento : datosMensajes) {
                    texto = (texto + elemento);
                    //            Log.d(TAGLOG, "----------------------- pasando4---------------" + datos2.get(0));
                }
                txtChat.setText(texto);
                txtChat.setMovementMethod(new ScrollingMovementMethod());
                break;
            case "Incidencia":
                //no hace naa aqui, las incidencias se muestran en otro metodo
                break;
            case "Consulta":
                for (String elemento : datosMensajes) {
                    texto = (texto + elemento);
                    //        Log.d(TAGLOG, "----------------------- pasando5---------------" + datos2.get(0));
                }
                txtChat3.setText(texto);
                txtChat3.setMovementMethod(new ScrollingMovementMethod());
                break;
            default:
                break;
        }
    }


    //metodo que se ejecuta al clickar enviar
    public void entraTexto(String miTab) {
        //instancia la base de datos de firebase
        DatabaseReference dbEnviar = FirebaseDatabase.getInstance().getReference().child(miTab);
        //crea una lista con datos del usuario y el mensaje introducido
        Map<String, String> envio = new HashMap<>();


        //selecciona una de las 3 cajas de entrada (una por pestaña) segun la pestaña y pone el valor
        //en el array
        switch (miTab) {
            case "Sugerencia":
                envio.put("Correo", correo);
                envio.put("Nombre", b.getString("nombre"));
                envio.put("Mensaje", inChat.getText().toString());
                //vacia la caja de entrada de arrayTexto
                inChat.setText("");
                break;
            case "Incidencia":
                envio.put("Correo", correo);
                envio.put("Descripcion", inDescripcion.getText().toString());
                envio.put("Equipo", inEquipo.getText().toString());
                envio.put("Fecha", txtFecha.getText().toString());
                envio.put("Titulo", inTitulo.getText().toString());
                break;
            case "Consulta":
                envio.put("Correo", correo);
                envio.put("Nombre", b.getString("nombre"));
                envio.put("Mensaje", inChat3.getText().toString());
                //vacia la caja de entrada de arrayTexto
                inChat3.setText("");
                break;
            default:
                break;
        }

        //sube el arrayTexto entrado a firebase, al nodo del usuario
        //  dbMensajes.child(txtEmail.getText().toString()).push().setValue(inChat.getText().toString());
        dbEnviar.push().setValue(envio);

    }

    public void nuevo() {
        inEquipo.setText(getResources().getString(R.string.equipo));
        inEquipo.setEnabled(true);
        inDescripcion.setText(getResources().getString(R.string.descripcion));
        inDescripcion.setEnabled(true);
        inTitulo.setText(getResources().getString(R.string.titulo));
        inTitulo.setEnabled(true);
        txtFecha.setText(dateFormat.format( cal.getTime()));

        btnCerrar.setVisibility(View.GONE);
        btnNuevo.setVisibility(View.GONE);
        btnScan.setVisibility(View.VISIBLE);
        btnCancelar.setVisibility(View.VISIBLE);
        enviar2.setVisibility(View.VISIBLE);
    }

    public void finalizar(String mytab) {
        //todo eliminar la incidencia seleccionada
        //instancia la clase que crea una conexion con la base de datos y llama al metodo de borrar
        //nodos pasandole como parametro el nodo almacenado mediante la clase mensajesincidencias
        //segun la incidencia seleccionada en el metodo muestraincidencias
        DBmensajes conn = new DBmensajes(mytab);
        conn.borrar(mi.getNodo());
    }

    public void cancelar() {
        inEquipo.setText(getResources().getString(R.string.equipo));
        inDescripcion.setText(getResources().getString(R.string.descripcion));
        inTitulo.setText(getResources().getString(R.string.titulo));
        txtFecha.setText(dateFormat.format( cal.getTime()));
    }

    public void escanear(){
        Intent intent = new Intent(this, QRscanner.class);
        startActivityForResult(intent, request_code);
    }

    //recibe el resultado de la llamada al lector de QR
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        // TODO Auto-generated method stub
        if ((requestCode == request_code) && (resultCode == RESULT_OK)) {

           // Toast.makeText(this, intent.getStringExtra("resultado"), Toast.LENGTH_LONG).show();
            inEquipo.setText(intent.getStringExtra("resultado"));
        }
    }

}
