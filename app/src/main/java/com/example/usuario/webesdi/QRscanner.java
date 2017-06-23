package com.example.usuario.webesdi;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


//implementing onclicklistener

    public class QRscanner extends BaseActivity{


    //qr code scanner object
    private IntentIntegrator qrScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //esta activity no muestra nada por si misma, por lo que no necesita layout
      //  setContentView(R.layout.activity_qrscanner);

        //intializing scan object
        qrScan = new IntentIntegrator(this);

        //initiating the qr code scan
        qrScan.initiateScan();
    }

    //Getting the scan results
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //if qrcode has nothing in it
            if (result.getContents() == null) {
                //devuelve nada
                setResult(RESULT_CANCELED);
                finish();
               // Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                //if qr contains data
                //crea un intent y le inserta el string recibido del activity de la camara
                Intent intent = new Intent();
                intent.putExtra("resultado",result.getContents());
                //devuelve resultado ok y el intent con el string
                setResult(RESULT_OK, intent);
                //finaliza esta activity
                finish();
               // Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }

}