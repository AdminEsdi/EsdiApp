package com.example.usuario.webesdi.empresas;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.usuario.webesdi.BaseActivity;
import com.example.usuario.webesdi.MenuPrincipal;
import com.example.usuario.webesdi.R;
import com.example.usuario.webesdi.Settings;

import java.util.ArrayList;
import java.util.List;

public class Empresas extends BaseActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<Empresa> listaEmpresa;
    private EmpresaAdapter adaptadorEmpresas;

    private String[] nombres = {"Canon", "HP", "Adam"};

    private String[] descripcio;

    int[] pics = {
            R.drawable.logo_canon,
            R.drawable.logo_hp,
            R.drawable.logo_adam
           };

    private String[] fullDescr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresas);

        setTitle(getResources().getText(R.string.titEmpresas));
        descripcio =  getResources().getStringArray(R.array.descripcionEmpresa);
        fullDescr =  getResources().getStringArray(R.array.descripcionCompletaEmpresa);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_empresas);

        //Use this setting to improve performance if you know that changes in
        //the content do not change the layout size of the RecyclerView
        if (mRecyclerView != null) {
            mRecyclerView.setHasFixedSize(true);
        }

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //intializing an arraylist called songlist
        listaEmpresa = new ArrayList<>();

        //adding data from arrays to songlist
        for (int i = 0; i < nombres.length; i++) {
            Empresa empresa = new Empresa(nombres[i], descripcio[i], i + 1, pics[i],  fullDescr[i]);
            listaEmpresa.add(empresa);
        }
        //initializing adapter
        adaptadorEmpresas = new EmpresaAdapter(listaEmpresa);

        //specifying an adapter to access data, create views and replace the content
        mRecyclerView.setAdapter(adaptadorEmpresas);
        adaptadorEmpresas.notifyDataSetChanged();

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Empresa e = listaEmpresa.get(position);
                expandir(e);
            }
        }));
    }

    public void expandir(Empresa e)
    {
        String nom = e.getName();
        String descr = e.getDescripcio();
        int logo = e.getPic();
        String descrCom = e.getExplicacionCompleta();

        Intent intent = new Intent(Empresas.this,EmpresaExtend.class);
        intent.putExtra("nom", nom );
        intent.putExtra("descr", descr );
        intent.putExtra("logo", logo );
        intent.putExtra("descrComp",descrCom );
        startActivity(intent);
    }
}
