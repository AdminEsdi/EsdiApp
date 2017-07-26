package com.example.usuario.webesdi.Tutorial;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

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

        setTitle(getResources().getText(R.string.titTutorial));
        Resources res = getResources();
        ListView lst = (ListView) findViewById(R.id.listaTutoriales);
        Intent Mainact = getIntent();
        nom = Mainact.getStringExtra("nombre");
        //depende que boton hayamos pulsará cargará una lista de arrays o otra.
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
        //arrancamos el adaptador para poder visualizar las arrays y si clicamos que nos lleve al pdf
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
}
