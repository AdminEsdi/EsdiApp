package com.example.usuario.webesdi;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.util.Calendar;
import java.util.List;


public class EstadoServicios extends AppCompatActivity {
    ImageView btnimpresoras;
    ImageView btnwifi;
    ImageView btngoogledocs;
    ImageView btngooglegmail;
    ImageView btngooglecalendar;
    ImageView btncantina;
    ImageView btnestatimpresoras;
    ImageView btnestatwifi;
    ImageView btnestatgoogledocs;
    ImageView btnestatgooglegmail;
    ImageView btnestatgooglecalendar;
    ImageView btnestatcantina;
    int diasemana,horas;
    Calendar cal = Calendar.getInstance();
    Toast toast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estado_servicios);

        setTitle(getResources().getText(R.string.titServicios));
        btnimpresoras  = (ImageView) findViewById(R.id.btnimpresoras);
        btnwifi = (ImageView) findViewById(R.id.btnwifi);
        btngoogledocs = (ImageView) findViewById(R.id.btnGoogleDocs);
        btncantina = (ImageView) findViewById(R.id.btncantina);
        btngooglegmail = (ImageView) findViewById(R.id.btnGoogleGmail);
        btngooglecalendar = (ImageView) findViewById(R.id.btnGoogleCalendar);
        btnestatimpresoras  = (ImageView) findViewById(R.id.btnestatPrinters);
        btnestatwifi = (ImageView) findViewById(R.id.btnestatWifi);
        btnestatgoogledocs = (ImageView) findViewById(R.id.btnestatGoogleDocs);
        btnestatcantina = (ImageView) findViewById(R.id.btnestatCafe);
        btnestatgooglegmail = (ImageView) findViewById(R.id.btnestatGmail);
        btnestatgooglecalendar = (ImageView) findViewById(R.id.btnestatGoogleCalendar);

        new disponibilitatwifi().execute();
        new disponibilitatdocs().execute();
        new disponibilitatgmail().execute();
        new disponibilitatimpresora().execute();
        new disponibilitatcantina().execute();
        new disponibilitatcalendar().execute();




    }



    private class disponibilitatwifi extends AsyncTask<Void, Void, Boolean>{
        Boolean a;
        @Override

        protected Boolean doInBackground(Void... params) {
            WifiManager wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            final List<ScanResult> results = wifi.getScanResults();
            if (results != null) {
                for (int i = 0; i < results.size(); i++) {
                    String ssid = results.get(i).SSID;
                    if (ssid.startsWith("ESDiWIFI") && wifi.isWifiEnabled() ) {
                        a = true; }
                    else if (!wifi.isWifiEnabled()) {
                        a = false;
                    }
                }
            }
            return a;
        }


        @Override
        protected void onPostExecute(Boolean color) {
            if ( color == true) {
                btnwifi.setImageResource(R.drawable.wifi_verd);
                btnestatwifi.setImageResource(R.drawable.actiu);
                btnwifi.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v)  {
                        displayToast("La Wifi esta disponible on estas situat");
                    }
                });

            } else {
                btnwifi.setImageResource(R.drawable.wifi_vermell);
                btnestatwifi.setImageResource(R.drawable.innactiu);
                btnwifi.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v)  {
                        displayToast("La Wifi no esta disponible on estas situat");
                    }
                });
            }

        }
    }
    private class disponibilitatdocs extends AsyncTask<Void, Void, Boolean> {
        Boolean a;

        @Override

        protected Boolean doInBackground(Void... params) {
            if(isReachable("docs.esdi.edu.es",80,1000)== true){
                a = true;
            }
            else{
                a = false;
            }
            return a;
        }





        @Override
        protected void onPostExecute(Boolean color) {
            if (color == true) {
                btngoogledocs.setImageResource(R.drawable.docs_verd);
                btnestatgoogledocs.setImageResource(R.drawable.actiu);
                btngoogledocs.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v)  {
                        displayToast("Google docs funciona correctament");
                    }
                });
            } else {
                btngoogledocs.setImageResource(R.drawable.docs_vermell);
                btnestatgoogledocs.setImageResource(R.drawable.innactiu);
                btngoogledocs.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v)  {
                        displayToast("Google docs no esta operatiu");
                    }
                });
            }

        }
    }
      private class disponibilitatimpresora extends AsyncTask<Void, Void, Boolean>{
      Boolean a;
          String funciona1 = "", funciona2="",funciona3="";
      @Override

      protected Boolean doInBackground(Void... params) {

          URL url= null;
          try {
              url = new URL("http://67.222.58.123/ddt/sql/ping (3).php");
          } catch (MalformedURLException e) {
              e.printStackTrace();
          }

          InputStream in = null;
          try {
              in = url.openStream();
          } catch (IOException e) {
              e.printStackTrace();
          }
          BufferedReader reader = new BufferedReader(new InputStreamReader(in));
          StringBuilder result = new StringBuilder();
          String line;
          try {
              while((line = reader.readLine()) != null) {
                  result.append(line);
              }
          } catch (IOException e) {
              e.printStackTrace();
          }
          System.out.println(result.toString());

          String[] ips = result.toString().split("/");
          String ip1 = ips[0].trim();
          String ip2 = ips[1].trim();
          String ip3 = ips[2].trim();




          if (ip1 == "True" && ip2== "True" && ip3=="True") {
                  a = true;
              } else {
                  a = false;
              }

          if (ip1 == "True") {
              funciona1 = "funciona";
          } else {
              funciona1 = "no funciona";
          }

          if (ip2 == "True") {
              funciona2 = "funciona";
          } else {
              funciona2 = "no funciona";
          }

          if (ip3 == "True") {
              funciona3 = "funciona";
          } else {
              funciona3 = "no funciona";
          }

          return false;

      }



          @Override
      protected void onPostExecute(Boolean color) {

          if ( color == true) {
              btnimpresoras.setImageResource(R.drawable.impresora_verda);
              btnestatimpresoras.setImageResource(R.drawable.actiu);
              btnimpresoras.setOnClickListener(new View.OnClickListener() {
                  public void onClick(View v)  {
                       displayToast("Les impresores funcionen correctament");
                  }
              });
          } else {
              btnimpresoras.setImageResource(R.drawable.impresora_vermell);
              btnestatimpresoras.setImageResource(R.drawable.innactiu);
              btnimpresoras.setOnClickListener(new View.OnClickListener() {
                  public void onClick(View v)  {
                   displayToast("La impresora de la primera clase:"+ funciona1 +"\n"+"La impresora de la segona clase:"+ funciona2 +"\n"+"La impresora de la tercera clase:"+ funciona3);
                  }
              });
          }

      }
      }

    private class disponibilitatcantina extends AsyncTask<Void, Void, Boolean>{
        Boolean a;
        @Override

        protected Boolean doInBackground(Void... params) {
            diasemana = cal.get(Calendar.DAY_OF_WEEK);
            horas = cal.get(Calendar.HOUR_OF_DAY);
            if ((diasemana==2 || diasemana==3 || diasemana==4 || diasemana==5 || diasemana==6) && (horas>=8 || horas<19)){
                a = true;

            }else{
                a = false;
            }
            return a;
        }

        @Override
        protected void onPostExecute(Boolean color) {
            if ( color == true) {
                btncantina.setImageResource(R.drawable.cantina_verd);
                btnestatcantina.setImageResource(R.drawable.actiu);
                btncantina.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v)  {
                        displayToast("La cafeteria esta oberta");
                    }
                });
            } else {
                btncantina.setImageResource(R.drawable.cantina_vermell);
                btnestatcantina.setImageResource(R.drawable.innactiu);
                btncantina.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v)  {
                        displayToast("La cafeteria no esta oberta");
                    }
                });
            }

        }
    }
    private class disponibilitatgmail extends AsyncTask<Void, Void, Boolean>{
        Boolean a;
        @Override

        protected Boolean doInBackground(Void... params) {


            if (isReachable("mail.esdi.edu.es", 80, 1000) == true) {
                a = true;
            } else {
                a = false;
            }
            return a;
        }

        @Override
        protected void onPostExecute(Boolean color) {
            if ( color == true) {
                btngooglegmail.setImageResource(R.drawable.gmail_verd);
                btnestatgooglegmail.setImageResource(R.drawable.actiu);
                btngooglegmail.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v)  {
                        displayToast("El gmail funciona correctament");
                    }
                });
            } else {
                btngooglegmail.setImageResource(R.drawable.gmail_vermell);
                btnestatgooglegmail.setImageResource(R.drawable.innactiu);
                btngooglegmail.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v)  {
                        displayToast("El gmail no esta operatiu");
                    }
                });
            }

        }
    }
    private class disponibilitatcalendar extends AsyncTask<Void, Void, Boolean>{
        Boolean a;
        @Override

        protected Boolean doInBackground(Void... params) {
            if (isReachable("calendar.esdi.edu.es", 80, 1000) == true) {
                a = true;
            } else {
                a = false;
            }
            return a;
        }

        @Override
        protected void onPostExecute(Boolean color) {
            if ( color == true) {
                btngooglecalendar.setImageResource(R.drawable.calendari_verd);
                btnestatgooglecalendar.setImageResource(R.drawable.actiu);
                btngooglecalendar.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v)  {
                        displayToast("El Google Calendar funciona correctament");
                    }
                });
            } else {
                btngooglecalendar.setImageResource(R.drawable.calendari_vermell);
                btnestatgooglecalendar.setImageResource(R.drawable.innactiu);
                btngooglecalendar.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v)  {
                        displayToast("El Google Calendar no esta operatiu");
                    }
                });
            }

        }
    }

    private static boolean isReachable(String addr, int openPort, int timeOutMillis) {

        try {
            try (Socket soc = new Socket()) {
                soc.connect(new InetSocketAddress(addr, openPort), timeOutMillis);
            }
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    public void displayToast(String message) {
        if(toast != null)
            toast.cancel();
        toast = Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT);
        toast.show();
    }
    protected void onPause() {
        if(toast != null)
            toast.cancel();
        super.onPause();
    }

}




