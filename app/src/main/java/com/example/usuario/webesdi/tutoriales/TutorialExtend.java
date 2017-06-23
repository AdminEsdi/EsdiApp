package com.example.usuario.webesdi.tutoriales;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.usuario.webesdi.BaseActivity;
import com.example.usuario.webesdi.R;
import com.example.usuario.webesdi.VistaPDF;

public class TutorialExtend extends BaseActivity {

    private String nom;
    private String descr;
    private int numLogo;
    private String fullDescr;

    private ImageView imgLogo;
    private TextView txtNom;
    private TextView txtDescr;
    private Button btnPdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_extend);
        Intent i = getIntent();
        //cargo los datos correctos.
        nom = i.getStringExtra("nom");
        descr = i.getStringExtra("descr");
        numLogo = i.getIntExtra("logo", 0);
        fullDescr = i.getStringExtra("descrComp");

        imgLogo = (ImageView) findViewById(R.id.logoEmpresaExt);
        txtNom = (TextView) findViewById(R.id.nombreEmpresaExt);
        txtDescr = (TextView) findViewById(R.id.descrEmpresaExt);
        btnPdf = (Button) findViewById(R.id.visualizarPdf);

        imgLogo.setImageResource(numLogo);
        txtNom.setText(nom);
        txtDescr.setText(descr + "\n...\n" + fullDescr );

        btnPdf.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent intent = new Intent(TutorialExtend.this,VistaPDF.class);
                //emvio el nom para distinguir entre pdfs
                intent.putExtra("nombre",nom);
                startActivity(intent);

            }
        });
    }
}
