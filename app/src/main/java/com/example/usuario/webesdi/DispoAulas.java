package com.example.usuario.webesdi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DispoAulas extends BaseActivity {


    private DatePicker dp ;
    private TimePicker tp;
    private String URLserver;
    ImageView ivMapa,h1,h2,h3,h4,h5;
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    ImageView btnSeleccionarD;
    Button btnSeleccionarH;
    Bundle b;

    Button btnAceptar;

    boolean dpseleccionado = false,tpseleccionado = false,paso1= false,paso2 = false;
    // con el calendar puedo mirar datos del tiempo actual.
    //Todos los tiempo que nos interesan

    int diasemana,horas,minutos;
        Calendar cal = Calendar.getInstance();
    String dia = "Finde";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dispo_aulas);

        setTitle(getResources().getText(R.string.titDispoA));
        //recibe los datos de usuario a traves del bundle del intent
        Intent Mainact = getIntent();
        b = Mainact.getExtras();
        URLserver = b.getString("URL");

        tp = (TimePicker) findViewById(R.id.timePicker);
        dp = (DatePicker) findViewById(R.id.datePicker);
        h1 = (ImageView)findViewById(R.id.h1);
        h2 = (ImageView)findViewById(R.id.h2);
        h3 = (ImageView)findViewById(R.id.h3);
        h4 = (ImageView)findViewById(R.id.h4);
        h5 = (ImageView)findViewById(R.id.h5);
        btnSeleccionarH = (Button) findViewById(R.id.btnSeleccionarH);
        btnSeleccionarD = (ImageView) findViewById(R.id.btnSeleccionarD);
        btnAceptar = (Button) findViewById(R.id.btnAceptar);

        //informacion del momento actual usando la libreria Calendar
        diasemana = cal.get(Calendar.DAY_OF_WEEK);
        horas = cal.get(Calendar.HOUR_OF_DAY);
        minutos = cal.get(Calendar.MINUTE);

        btnSeleccionarH.setVisibility(View.GONE);

        btnSeleccionarH.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                lanzarSeleccionH();

            }
        });

        btnSeleccionarD.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                lanzarSeleccionD();

            }
        });

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzarAceptar();
            }
        });

        if (diasemana==2){
            dia = "Lunes";

        } else if (diasemana==3) {
            dia = "Martes";
        }else if (diasemana==4) {
            dia = "Miercoles";
        }else if (diasemana==5) {
            dia = "Jueves";
        }else if (diasemana==6) {
            dia = "Viernes";
        }


        ivMapa = (ImageView)findViewById(R.id.ivMapa);

        //este metodo comprovará la hora y cambiará el Recurso del ImageView por el que nos interese
        // comprobarhora mira que no sea por la noche, o mañana (fuera de horarios)
        if (comprobarhora()){
            actualizar();
        }


    }



    // hace una comprovación rápida, para asegurar que las aulas estan abiertas.
    private boolean comprobarhora() {
        if (horas==8 ){
            if (minutos>30){
                return true;
            }
        }else if (horas > 8 && horas<20) {
            return true;
        }
            return false;


    }
    //este metodo se encarga de comparar el momento actual con el horario, y mostrar la imagen que corresponde
    private void cambiarh1(){
        h1.setImageResource(R.drawable.edifici_esdi_h1verde);

    }
    private void cambiarh2(){
        h2.setImageResource(R.drawable.edifici_esdi_h2verde);

    }
    private void cambiarh3(){
        h3.setImageResource(R.drawable.edifici_esdi_h3verde);

    }
    private void cambiarh4(){
        h4.setImageResource(R.drawable.edifici_esdi_h4verde);

    }
    private void cambiarh5(){
        h5.setImageResource(R.drawable.edifici_esdi_h5verde);

    }
    //los 2 metodos de seleccion ocultaran el mapa y hacen aparecer un picker.
    private void lanzarSeleccionH(){
        dp.setVisibility(View.GONE);
        tp.setVisibility(View.VISIBLE);
        btnSeleccionarD.setVisibility(View.INVISIBLE);
        btnSeleccionarH.setVisibility(View.INVISIBLE);
        btnAceptar.setVisibility(View.VISIBLE);

        cal.set(dp.getYear(),dp.getMonth(),dp.getDayOfMonth());
        diasemana = cal.get(Calendar.DAY_OF_WEEK);

        if (diasemana==2){
            dia = "Lunes";
        } else if (diasemana==3) {
            dia = "Martes";
        }else if (diasemana==4) {
            dia = "Miercoles";
        }else if (diasemana==5) {
            dia = "Jueves";
        }else if (diasemana==6) {
            dia = "Viernes";
        }



    }
    private void lanzarSeleccionD(){

            ivMapa.setVisibility(View.INVISIBLE);
            h1.setVisibility(View.INVISIBLE);
            h2.setVisibility(View.INVISIBLE);
            h3.setVisibility(View.INVISIBLE);
            h4.setVisibility(View.INVISIBLE);
            h5.setVisibility(View.INVISIBLE);
            dp.setVisibility(View.VISIBLE);
            btnSeleccionarH.setVisibility(View.VISIBLE);
            btnSeleccionarD.setVisibility(View.GONE);

    }

    private void lanzarAceptar(){
        ivMapa.setVisibility(View.VISIBLE);
        h1.setVisibility(View.VISIBLE);
        h2.setVisibility(View.VISIBLE);
        h3.setVisibility(View.VISIBLE);
        h4.setVisibility(View.VISIBLE);
        h5.setVisibility(View.VISIBLE);
        tp.setVisibility(View.INVISIBLE);
        btnSeleccionarD.setVisibility(View.VISIBLE);
        btnAceptar.setVisibility(View.INVISIBLE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            horas = tp.getHour();
            minutos = tp.getMinute();
        }
        actualizar();
    }

    public void actualizar(){
            cambiarh1();
            if(dia!="Finde"){
                // new AsyncSelect().execute(String.valueOf(cal),dia);
                new AsyncSelect().execute(String.valueOf(horas),dia,"h2" );
                new AsyncSelect().execute(String.valueOf(horas),dia,"h3" );
                new AsyncSelect().execute(String.valueOf(horas),dia,"h4" );
                new AsyncSelect().execute(String.valueOf(horas),dia,"h5" );
            }

    }


    private class AsyncSelect extends AsyncTask<String, String, String> {
        ProgressDialog pdLoading = new ProgressDialog(DispoAulas.this);
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();

        }

        @Override
        protected String doInBackground(String... params) {
            //funcion que devuelve un string al metodo postexecute
            try {

                url = new URL("http://67.222.58.123/ddt/sql/select.inc.php");

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return "exception 1";
            }
            try {
                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("POST");

                // setDoInput and setDoOutput method depict handling of both send and receive
                conn.setDoInput(true);
                conn.setDoOutput(true);

                // Append parameters to URL
                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("username", params[0])
                        .appendQueryParameter("dia", params[1])
                        .appendQueryParameter("aula", params[2]);
                //   .appendQueryParameter("password", params[1]);
                String query = builder.build().getEncodedQuery();

                // Open connection for sending data
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                conn.connect();

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return "exception 2";
            }

            try {

                int response_code = conn.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // recibe el string desde el php y lo pasa al postexecute
                    return (result.toString());

                } else {

                    return ("exception 3");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return "exception 3";
            } finally {
                conn.disconnect();
            }


        }

        @Override
        protected void onPostExecute(String result) {
            //se ejecuta tras recibir el string desde doInbackground

            //this method will be running on UI thread

            pdLoading.dismiss();


            switch (result) {
                //si no se puede comprobar el usuario en la base de datos, el rol pasa a ser usuario
                case "exception 0":
                    Toast.makeText(DispoAulas.this, "imposible establecer conexión con la " +
                            "base de datos SQL desde el servidor", Toast.LENGTH_LONG).show();
                    break;
                case "exception 1":
                    Toast.makeText(DispoAulas.this, "imposible establecer conexión con el " +
                            "servidor", Toast.LENGTH_LONG).show();
                    break;
                case "exception 2":
                    Toast.makeText(DispoAulas.this, "fallo al enviar la consulta al" +
                            "servidor", Toast.LENGTH_LONG).show();
                    break;
                case "exception 3":
                    Toast.makeText(DispoAulas.this, "no se recibe respuesta desde el " +
                            "servidor", Toast.LENGTH_LONG).show();
                    break;
                case "h2":
                    cambiarh2();
                    break;
                case "h3":
                    cambiarh3();
                    break;
                case "h4":
                    cambiarh4();
                    break;
                case "h5":
                    cambiarh5();
                    break;
                /*
                case "usuario":
                    Log.d(TAGLOG, "======= usuario ==========   " + result);
                    //txtAcceso.setText("Acceso: " + result);
                    rol = result;
                    break;
                    */
                //si no devuelve una excepcion, es que ha foncionado, entonces el rol pasa a ser
                //el que devuelve el php
                default:
                    //  Log.d(TAGLOG, "======= usuario ==========   " + result);
                    // txtAcceso.setText("Acceso: " + result);
                    break;

            }

        }

    }



}

