package com.example.usuario.webesdi.tutoriales;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.usuario.webesdi.BaseActivity;
import com.example.usuario.webesdi.R;
import com.example.usuario.webesdi.empresas.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class Tutoriales extends BaseActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<Tutorial> listaTutoriales;
    private TutorialAdapter adaptadorTutorial;

    private String[] names = {"Impresora","Escuela"};

    private String[] descripcio;

    private String[] fullDescr;

    int[] pics = {
            R.drawable.impresora,
            R.drawable.uab};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutoriales);

        descripcio =  getResources().getStringArray(R.array.descripcionTutoriales);
        fullDescr =  getResources().getStringArray(R.array.descripcionCompletaTutoriales);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_tutoriales);

        //Use this setting to improve performance if you know that changes in
        //the content do not change the layout size of the RecyclerView
        if (mRecyclerView != null) {
            mRecyclerView.setHasFixedSize(true);
        }

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //intializing an arraylist called songlist
        listaTutoriales = new ArrayList<>();

        //adding data from arrays to songlist
        for (int i = 0; i < names.length; i++) {
            Tutorial tutorial = new Tutorial(names[i], descripcio[i], i + 1, pics[i],fullDescr[i]);
            listaTutoriales.add(tutorial);
        }
        //initializing adapter
        adaptadorTutorial = new TutorialAdapter(listaTutoriales);

        //specifying an adapter to access data, create views and replace the content
        mRecyclerView.setAdapter(adaptadorTutorial);
        adaptadorTutorial.notifyDataSetChanged();

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Tutorial e = listaTutoriales.get(position);
                expandir(e);
            }
        }));
    }

    public void expandir(Tutorial e)
    {
        String nom = e.getName();
        String descr = e.getDescripcio();
        int logo = e.getPic();
        String descrCom = e.getExplicacionCompleta();
        //Envio el contenido de el tutorial que he seleccionado.
        Intent intent = new Intent(Tutoriales.this,TutorialExtend.class);
        intent.putExtra("nom", nom );
        intent.putExtra("descr", descr );
        intent.putExtra("logo", logo );
        intent.putExtra("descrComp",descrCom );
        startActivity(intent);
    }
}
