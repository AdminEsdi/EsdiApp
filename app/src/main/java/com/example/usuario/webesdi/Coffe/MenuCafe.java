package com.example.usuario.webesdi.Coffe;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.usuario.webesdi.R;
import com.github.barteksc.pdfviewer.PDFView;

public class MenuCafe extends Fragment{

    PDFView pdfView;
    private String nom;
    private String url="http://www.esdi.es/content/pdf/fent-memoria-2010_2011ok.pdf";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_carta, container, false);
        /*new urlPdf().execute(url);
        pdfView = (PDFView) getActivity().findViewById(R.id.pdfView);*/

        return rootView;
    }

/*
    class urlPdf extends AsyncTask<String,Void,InputStream> {

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
    }*/
}
