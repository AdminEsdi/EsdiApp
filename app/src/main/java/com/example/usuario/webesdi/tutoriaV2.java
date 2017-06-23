package com.example.usuario.webesdi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class tutoriaV2 extends BaseActivity {

    private ImageView impresora,ps,gimp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutoria_v2);

        setTitle(getResources().getText(R.string.titTutorial));
        impresora = (ImageView) findViewById(R.id.imgImpresora);
        ps = (ImageView) findViewById(R.id.imgPS);
        gimp = (ImageView) findViewById(R.id.imgGimp);

        impresora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tutoriaV2.this,VistaPDF.class);
                //emvio el nom para distinguir entre pdfs
                intent.putExtra("nombre","impresora");
                startActivity(intent);
            }
        });

        ps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tutoriaV2.this,VistaPDF.class);
                //emvio el nom para distinguir entre pdfs
                intent.putExtra("nombre","ps");
                startActivity(intent);
            }
        });

        gimp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tutoriaV2.this,VistaPDF.class);
                //emvio el nom para distinguir entre pdfs
                intent.putExtra("nombre","gimp");
                startActivity(intent);
            }
        });
    }
}
