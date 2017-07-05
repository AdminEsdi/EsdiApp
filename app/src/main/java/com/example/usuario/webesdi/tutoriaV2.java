package com.example.usuario.webesdi;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class tutoriaV2 extends BaseActivity {

    private ImageView impresora,ps,gimp;
    TextView textView4;
    ArrayAdapter<String> adapter;
    String [] arrayCountry;

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

        Resources res = getResources();
        arrayCountry = res.getStringArray(R.array.array_lista_general);
        adapter = new ArrayAdapter<String>(tutoriaV2.this,android.R.layout.simple_list_item_1, arrayCountry);
        lv.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.menuSearch);
        android.widget.SearchView searchView = (android.widget.SearchView)item.getActionView();
        searchView.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
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
                }
                adapter.getFilter().filter(newText);

                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
