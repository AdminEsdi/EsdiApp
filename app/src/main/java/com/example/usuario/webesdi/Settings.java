package com.example.usuario.webesdi;


import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Silvan on 09/02/2017.
 */

public class Settings extends BaseActivity  implements CompoundButton.OnCheckedChangeListener, Spinner.OnItemSelectedListener {
    private String callingActivity = "";
    private SharedPreferences sp;
    private Switch themeSwitch;
    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sp = PreferenceManager.getDefaultSharedPreferences(this);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);

        if (callingActivity.isEmpty()){
            Intent i = getIntent();
            callingActivity =  i.getStringExtra("callingActivity");
        }


        int posi = sp.getInt("idiomaAplicacion", 1);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setSelection(posi);
        spinner.setOnItemSelectedListener(this);
    }

    // Para controlar el cambio de posicion del Switch
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        SharedPreferences.Editor editor = sp.edit();
        if(isChecked) {
            editor.putBoolean("temaAplicacion", true);
        } else {
            editor.putBoolean("temaAplicacion", false);
        }
        editor.commit();
        finish();
        startActivity(getIntent());
    }

    // Para controlar la seleccion de una opcion del Spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        int guardado = sp.getInt("idiomaAplicacion", 1);

        // parent.getItemAtPosition(position).toString(); por si se necesita mas adelante
        String lang ;
        switch (position){
            case 1:
                lang = "ca-ES";
                break;
            case 2:
                lang = "es-ES";
                break;
            default:
                lang = "en";
                break;
        }

        if (guardado != position){
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt("idiomaAplicacion", position);
            editor.commit();
            this.setLocale(lang);
            finish();
            startActivity(getIntent());
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {


    }
    public void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
    }


    @Override
    public void onBackPressed() {
        Bundle extras = getIntent().getExtras();
        Class<?> c = null;
        if (callingActivity != null) {
            try {
                c = Class.forName(callingActivity);
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Intent intent = new Intent(Settings.this, c);
            intent.putExtras(extras);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            super.onBackPressed();
        }
    }
}
