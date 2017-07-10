package com.example.usuario.webesdi.Tutorial;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.TextView;
import com.example.usuario.webesdi.BaseActivity;
import com.example.usuario.webesdi.PaginaWeb;
import com.example.usuario.webesdi.R;

import java.util.ArrayList;
import java.util.Arrays;

public class tutoriaV2 extends BaseActivity {

    private ImageView impresora,ps,gimp;
    TextView textView4;
    ArrayAdapter<String> adapter;
    MyAdapter myAdapter;
    String [] titulo;
    String [] subtitulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutoria_v2);

        setTitle(getResources().getText(R.string.titTutorial));
        impresora = (ImageView) findViewById(R.id.imgImpresora);
        ps = (ImageView) findViewById(R.id.imgPS);
        gimp = (ImageView) findViewById(R.id.imgGimp);
        textView4 = (TextView) findViewById(R.id.textView4);

        impresora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tutoriaV2.this,ListaPdf.class);
                //emvio el nom para distinguir entre pdfs
                intent.putExtra("nombre","impresora");
                startActivity(intent);
            }
        });

        ps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tutoriaV2.this,ListaPdf.class);
                //emvio el nom para distinguir entre pdfs
                intent.putExtra("nombre","ps");
                startActivity(intent);
            }
        });

        gimp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tutoriaV2.this,ListaPdf.class);
                //emvio el nom para distinguir entre pdfs
                intent.putExtra("nombre","gimp");
                startActivity(intent);
            }
        });

        ListView lv= (ListView)findViewById(R.id.listaPdfInicial);
        lv.setVisibility(View.INVISIBLE);
        ScrollView sc = (ScrollView) findViewById(R.id.scrollTutorial);
        sc.setVisibility(View.VISIBLE);

        ArrayList<String> arrayTitulo = new ArrayList<>();
        arrayTitulo.addAll(Arrays.asList(getResources().getStringArray(R.array.array_lista_general)));
        adapter = new ArrayAdapter<>(tutoriaV2.this,android.R.layout.simple_list_item_1,arrayTitulo);

        Resources res = getResources();
        titulo = res.getStringArray(R.array.array_lista_general);
        subtitulo = res.getStringArray(R.array.array_lista_general_sub);
        myAdapter = new MyAdapter(tutoriaV2.this,titulo,subtitulo);
        lv.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.menuSearch);
        SearchView searchView = (SearchView)item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(tutoriaV2.this,PaginaWeb.class);
                //emvio el nom para distinguir entre pdfs
                intent.putExtra("nombre","gimp");
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ListView lv= (ListView)findViewById(R.id.listaPdfInicial);
                ScrollView sc = (ScrollView) findViewById(R.id.scrollTutorial);
                if(newText.equals("")){
                    sc.setVisibility(View.VISIBLE);
                    lv.setVisibility(View.INVISIBLE);
                }else {
                    sc.setVisibility(View.INVISIBLE);
                    lv.setVisibility(View.VISIBLE);

                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(tutoriaV2.this, VistaPDF.class);
                            intent.putExtra("nombre",titulo[position]);
                            startActivity(intent);
                        }
                    });
                    adapter.getFilter().filter(newText);
                }


                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
