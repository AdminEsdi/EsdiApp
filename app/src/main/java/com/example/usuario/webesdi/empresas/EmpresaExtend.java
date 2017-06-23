package com.example.usuario.webesdi.empresas;

import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.usuario.webesdi.BaseActivity;
import com.example.usuario.webesdi.R;

public class EmpresaExtend extends BaseActivity {
    private String nom;
    private String descr;
    private int numLogo;
    private String fullDescr;

    private ImageView imgLogo;
    private TextView txtNom;
    private TextView txtDescr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresa_extend);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int height = size.y;

        imgLogo = (ImageView) findViewById(R.id.logoEmpresaExt);
        imgLogo.setMaxHeight((height / 2));

        Intent i = getIntent();
        nom = i.getStringExtra("nom");
        descr = i.getStringExtra("descr");
        numLogo = i.getIntExtra("logo", 0);
        fullDescr = i.getStringExtra("descrComp");

        imgLogo = (ImageView) findViewById(R.id.logoEmpresaExt);
        txtNom = (TextView) findViewById(R.id.nombreEmpresaExt);
        txtDescr = (TextView) findViewById(R.id.descrEmpresaExt);

        imgLogo.setImageResource(numLogo);
        txtNom.setText(nom);
        txtDescr.setText(descr + "\n...\n" + fullDescr );

    }
}
