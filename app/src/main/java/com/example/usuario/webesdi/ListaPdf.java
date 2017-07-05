package com.example.usuario.webesdi;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListaPdf extends BaseActivity {

    String [] titulo;
    String [] subtitulo;
    private String nom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pdf);

        ListView lst = (ListView) findViewById(R.id.listaTutoriales);
        Resources res = getResources();
        Intent Mainact = getIntent();
        nom = Mainact.getStringExtra("nombre");
        if(nom.equals("impresora")){
            titulo = res.getStringArray(R.array.array_lista_utilidad);
            subtitulo = res.getStringArray(R.array.array_lista_utilidad_sub);
        }else if (nom.equals("ps")){
            titulo = res.getStringArray(R.array.array_lista_producto);
            subtitulo = res.getStringArray(R.array.array_lista_producto_sub);
        }else if (nom.equals("gimp")){
            titulo = res.getStringArray(R.array.array_lista_extras);
            subtitulo = res.getStringArray(R.array.array_lista_extras_sub);
        }

        CustomAdapter customAdapter = new CustomAdapter();
        lst.setAdapter(customAdapter);
    }

     class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return titulo.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.custom_adapter_layaout,null);
            TextView txttitulo = (TextView) convertView.findViewById(R.id.titulo);
            TextView txtsubtitulo = (TextView) convertView.findViewById(R.id.subtitulo);
            txttitulo.setText(titulo[position]);
            txtsubtitulo.setText(subtitulo[position]);
            return convertView;
        }
    }
}
