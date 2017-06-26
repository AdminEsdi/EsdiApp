package com.example.usuario.webesdi;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class VistaPDF extends BaseActivity {

    static PDFView pdfView;
    private String nom;
    private String url="http://www.esdi.es/content/pdf/fent-memoria-2010_2011ok.pdf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_pdf);
        setTitle("PDF");
        //Recogemos la variable
        Intent Mainact = getIntent();
        nom = Mainact.getStringExtra("nombre");
        //Distinguimos la variable dependiendo de que cardview vengamos
        if(nom.equals("impresora")){
            url="http://www.esdi.es/content/pdf/musica-y-nuevos-espacios-de-comunicacio--769-n--digital.pdf";
        }else if (nom.equals("ps")){
            url="http://www.um.es/aulasenior/saavedrafajardo/apuntes/doc/Manual_photoshop.pdf";
        }else if (nom.equals("gimp")){
            url="http://dis.um.es/~jfernand/0506/smig/gimp.pdf";
        }
        //Funcion para cargar un pdf de internet y mostrarlo
        new urlPdf().execute(url);
        pdfView = (PDFView) findViewById(R.id.pdfView);

        //Funcion para cargar desde un asset, para que funcione, tendriamos que crear una carpeta de assets y guardar dentro el pdf
        //pdfView.fromAsset("musica.pdf").load();
    }

    public void Descargar(View v) {
        Intent Mainact = getIntent();
        nom = Mainact.getStringExtra("nombre");
        if (nom.equals("impresora")) {
            new urlPdf.descargarFitxer().execute("http://www.esdi.es/content/pdf/musica-y-nuevos-espacios-de-comunicacio--769-n--digital.pdf", "musica-y-nuevos-espacios-de-comunicacio--769-n--digital.pdf");
        } else if (nom.equals("ps")) {
            new urlPdf.descargarFitxer().execute("http://www.um.es/aulasenior/saavedrafajardo/apuntes/doc/Manual_photoshop.pdf", "Manual_photoshop.pdf");
        } else if (nom.equals("gimp")) {
            new urlPdf.descargarFitxer().execute("http://dis.um.es/~jfernand/0506/smig/gimp.pdf", "gimp.pdf");
        }
    }

    static class urlPdf extends AsyncTask<String,Void,InputStream>{

        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream is = null;
            try{
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                if(urlConnection.getResponseCode()==200){
                    is=new BufferedInputStream(urlConnection.getInputStream());
                }
            }
            catch (IOException e){
                return null;
            }
            return is;
        }

        private static class descargarFitxer extends AsyncTask<String, Void, Void>{

            @Override
            protected Void doInBackground(String... strings) {
                String fileUrl = strings[0];
                String fileName = strings[1];
                File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                File pdfFile = new File(folder, fileName);

                try{
                    pdfFile.createNewFile();
                }catch (IOException e){
                    e.printStackTrace();
                }
                Descarregarpdf.downloadFile(fileUrl, pdfFile);
                return null;
            }
        }
        @Override
        protected void onPostExecute(InputStream is){
            pdfView.fromStream(is).load();
        }


    }
}