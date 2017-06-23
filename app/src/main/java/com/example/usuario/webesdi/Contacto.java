package com.example.usuario.webesdi;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Contacto extends BaseActivity implements OnMapReadyCallback {

    private GoogleMap mapa;
    private Button btnCorreo;

    Bundle b;
    Button numero;
    Button btnChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        setTitle(getResources().getText(R.string.titContacto));
        //recibe los datos de usuario a traves del bundle del intent
        Intent Mainact = getIntent();
        b = Mainact.getExtras();

        String nombre = b.getString("nombre");
        final String email = b.getString("email");
        numero = (Button) findViewById(R.id.botontlf);
        btnCorreo = (Button) findViewById(R.id.botonchat);
        btnChat = (Button) findViewById(R.id.botonmensajeria);


        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

        numero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri num = Uri.parse("tel:937274819");
                Intent i = new Intent(Intent.ACTION_CALL, num);
                if (ActivityCompat.checkSelfPermission(Contacto.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(i);
            }
        });
        btnCorreo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Reemplazamos el email por algun otro real
                String[] to = { "informatica@esdi.edu.es" };
                String[] cc = {  ""};
                enviar(to, cc, "Asunto",
                        " ");

            }
        });

        btnChat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                lanzarMensajes();

            }
        });
    }

    private void enviar(String[] to, String[] cc,
                        String asunto, String mensaje) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        //String[] to = direccionesEmail;
        //String[] cc = copias;
        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
        emailIntent.putExtra(Intent.EXTRA_CC, cc);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, asunto);
        emailIntent.putExtra(Intent.EXTRA_TEXT, mensaje);
        emailIntent.setType("message/rfc822");
        startActivity(Intent.createChooser(emailIntent, "Email "));
    }
    @Override
    public void onMapReady(GoogleMap map) {
        mapa = map;
        moverCentro();
        insertarMarcador();
    }

    private void moverCentro()
    {
        // actualiza la camara para que se mueva a la posicion de Esdi, con un zoom de 15
        CameraUpdate camUpd1 =
                CameraUpdateFactory
                        .newLatLngZoom(new LatLng(41.5438081, 2.1168294000000287), 15);

        mapa.moveCamera(camUpd1);
    }
    private void insertarMarcador() {
        //pone un marcador sobre el centro, permite hacerle click, y abrir la ruta para llegar.
        mapa.addMarker(new MarkerOptions()
                .position(new LatLng(41.5438081, 2.1168294000000287))
                .title("ESDI: Sabadell"));
    }

    public void lanzarMensajes(){
        Intent intent = new Intent(Contacto.this,Mensajes.class);
        intent.putExtras(b);
        startActivity(intent);
    }

}
