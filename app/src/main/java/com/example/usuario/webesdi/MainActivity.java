package com.example.usuario.webesdi;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class MainActivity extends BaseActivity
        implements GoogleApiClient.OnConnectionFailedListener {

    private String[] admins   = {"ocerezo@esdi.edu.es", "egutierrez@esdi.edu.es"};
    private SignInButton btnSignIn;
    private Button btnSignOut,btnInvitado;
    String Email,Nombre,rol = "Invitado",URLserver ="http://67.222.58.123/";

    private GoogleApiClient apiClient;
    private static final int RC_SIGN_IN = 1001;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSignIn = (SignInButton)findViewById(R.id.sign_in_button);
        btnSignOut = (Button)findViewById(R.id.sign_out_button);
        btnInvitado = (Button)findViewById(R.id.btnInvitado);

/*

        //Google API Client
*/
        GoogleSignInOptions gso =
                new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestEmail()
                        .build();

        apiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        //Personalización del botón de login

        btnSignIn.setSize(SignInButton.SIZE_STANDARD);
        btnSignIn.setColorScheme(SignInButton.COLOR_LIGHT);
        btnSignIn.setScopes(gso.getScopeArray());

        //Eventos de los botones



        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(apiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSignOut.setVisibility(View.GONE);
                Auth.GoogleSignInApi.signOut(apiClient).setResultCallback(
                        new ResultCallback<Status>() {
                            @Override
                            public void onResult(Status status) {
                                updateUI(false);
                            }
                        });
            }
        });
        btnInvitado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Email="invitado@gmail.com";
                Nombre="Invitado";
                iniciarActivity();

            }
        });

        updateUI(false);
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Toast.makeText(this, "Error de conexion!", Toast.LENGTH_SHORT).show();
        Log.e("GoogleSignIn", "OnConnectionFailed: " + connectionResult);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result =
                    Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            //Usuario logueado --> Mostramos sus datos
            GoogleSignInAccount acct = result.getSignInAccount();
            Email = acct.getEmail();
            Nombre = acct.getDisplayName();

            updateUI(true);
        } else {
            //Usuario no logueado --> Lo mostramos como "Desconectado"
            Toast.makeText(this, "Error en el resultado", Toast.LENGTH_SHORT).show();
            updateUI(false);
        }
    }

    private void updateUI(boolean signedIn) {
        if (signedIn) {

                iniciarActivity();
        } else {

            btnSignIn.setVisibility(View.VISIBLE);
        }
    }


    void iniciarActivity() {
        Bundle b = new Bundle();
        String[] extension = Email.split("@");
        compruebaRol(extension);
        b.putString("email", Email);
        b.putString("nombre", Nombre);
        b.putString("rol", rol);
        b.putString("URL", URLserver);

        Intent intent = new Intent(MainActivity.this, MenuPrincipal.class);

        intent.putExtras(b);

        startActivity(intent);
    }
    void compruebaRol(String[] extension){
        if (extension[1].equalsIgnoreCase("esdi.edu.es")){
            rol = "Alumno";
        }
        for(String actual : admins ){
            if (actual.equalsIgnoreCase(Email)){
                rol = "Administrador";
            }
        }
    }
    //silent
    @Override
    protected void onStart() {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(apiClient);
        if (opr.isDone()) {
            btnSignOut.setVisibility(View.VISIBLE);

        } else {

            btnSignOut.setVisibility(View.INVISIBLE);
        }
    }



}

