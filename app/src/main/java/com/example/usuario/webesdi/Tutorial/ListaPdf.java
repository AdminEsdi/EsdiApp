package com.example.usuario.webesdi.Tutorial;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.usuario.webesdi.BaseActivity;
import com.example.usuario.webesdi.R;

public class ListaPdf extends BaseActivity {

    String [] titulo;
    String [] subtitulo;
    private String nom;
    ListView lst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pdf);

        Resources res = getResources();
        ListView lst = (ListView) findViewById(R.id.listaTutoriales);
        Intent Mainact = getIntent();
        nom = Mainact.getStringExtra("nombre");
        switch (nom) {
            case "impresora":
                titulo = res.getStringArray(R.array.array_lista_utilidad);
                subtitulo = res.getStringArray(R.array.array_lista_utilidad_sub);
                break;
            case "ps":
                titulo = res.getStringArray(R.array.array_lista_producto);
                subtitulo = res.getStringArray(R.array.array_lista_producto_sub);
                break;
            case "gimp":
                titulo = res.getStringArray(R.array.array_lista_extras);
                subtitulo = res.getStringArray(R.array.array_lista_extras_sub);
                break;
        }
        MyAdapter myAdapter = new MyAdapter(ListaPdf.this,titulo,subtitulo);
        lst.setAdapter(myAdapter);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListaPdf.this, VistaPDF.class);
                intent.putExtra("nombre",titulo[position]);
                startActivity(intent);
            }
        });
    }

    /*class MyAdapter extends ArrayAdapter<String>{

        String [] titulo;
        String [] subtitulo;
        Context mContext;

        public MyAdapter(@NonNull Context context, String [] elTitulo, String [] elSubtitulo) {
            super(context, R.layout.custom_adapter_layaout);
            this.titulo=elTitulo;
            this.subtitulo=elSubtitulo;
            this.mContext=context;
        }

        @Override
        public int getCount(){
            return titulo.length;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHolder mViewHolder = new ViewHolder();
            if(convertView==null) {
                LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = mInflater.inflate(R.layout.custom_adapter_layaout, parent, false);
                mViewHolder.txttitulo = (TextView) convertView.findViewById(R.id.titulo);
                mViewHolder.txtsubtitulo = (TextView) convertView.findViewById(R.id.subtitulo);
                convertView.setTag(mViewHolder);
            }else{
                mViewHolder = (ViewHolder)convertView.getTag();
            }
                mViewHolder.txttitulo.setText(titulo[position]);
                mViewHolder.txtsubtitulo.setText(subtitulo[position]);


            return convertView;
        }
    }

    static class ViewHolder{
        TextView txttitulo;
        TextView txtsubtitulo;
    }*/
}
