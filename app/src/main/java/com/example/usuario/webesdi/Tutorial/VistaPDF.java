package com.example.usuario.webesdi.Tutorial;

import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.example.usuario.webesdi.BaseActivity;
import com.example.usuario.webesdi.R;
import com.github.barteksc.pdfviewer.PDFView;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class VistaPDF extends BaseActivity {

    PDFView pdfView;
    private String nom;
    private String url;
    private DownloadManager dm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_pdf);
        dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        setTitle("PDF");
        //Recogemos la variable
        Intent Mainact = getIntent();
        nom = Mainact.getStringExtra("nombre");
        //Distinguimos la variable dependiendo de que cardview vengamos
        switch (nom) {
            case "Item1":
                url = "http://www.esdi.es/content/pdf/musica-y-nuevos-espacios-de-comunicacio--769-n--digital.pdf";
                break;
            case "Item2":
                url = "http://www.um.es/aulasenior/saavedrafajardo/apuntes/doc/Manual_photoshop.pdf";
                break;
            case "Item3":
                url = "http://dis.um.es/~jfernand/0506/smig/gimp.pdf";
                break;
        }
        //Funcion para cargar un pdf de internet y mostrarlo
        new urlPdf().execute(url);
        pdfView = (PDFView) findViewById(R.id.pdfView);

        findViewById(R.id.descargar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(url)) {
                    throw new IllegalArgumentException("url cannot be empty or null");
                }

                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

                if (isExternalStorageWritable()) {
                    String uriString = v.getContext().getExternalFilesDir(null) + "";
                    File file = new File(uriString, Uri.parse(url).getLastPathSegment());
                    Uri destinationUri = Uri.fromFile(file);
                    request.setDestinationUri(destinationUri);
                    dm.enqueue(request);
                }
            }
        });

        //Funcion para cargar desde un asset, para que funcione, tendriamos que crear una carpeta de assets y guardar dentro el pdf
        //pdfView.fromAsset("musica.pdf").load();


    }
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    class urlPdf extends AsyncTask<String,Void,InputStream>{

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

        @Override
        protected void onPostExecute(InputStream is){
            pdfView.fromStream(is).load();
        }
    }
}
