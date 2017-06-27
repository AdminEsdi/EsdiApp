package com.example.usuario.webesdi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.Locale;

/**
 * Created by JR on 28/03/2017.
 */

public class BaseActivity extends AppCompatActivity {

    private int mThemeId = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        final AppCompatDelegate delegate = getDelegate();
        delegate.installViewFactory();
        delegate.onCreate(savedInstanceState);
        if (delegate.applyDayNight() && mThemeId != 0) {
            // If DayNight has been applied, we need to re-apply the theme for
            // the changes to take effect. On API 23+, we should bypass
            // setTheme(), which will no-op if the theme ID is identical to the
            // current theme ID.
            if (Build.VERSION.SDK_INT >= 23) {
                onApplyThemeResource(getTheme(), mThemeId, false);
            } else {
                setTheme(mThemeId);
            }
        }
        this.checkTheme();
        super.onCreate(savedInstanceState);
        this.ponIdioma();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.languages:
                lanzarSettings();
                return true;
            case R.id.desconection:
                lanzaDesconexion();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void lanzaDesconexion(){
        Bundle extras = getIntent().getExtras();
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtras(extras);
        startActivity(intent);
    }

    private void lanzarSettings(){
        Bundle extras = getIntent().getExtras();
        String nombreActivity = this.getClass().getCanonicalName();
        Intent intent = new Intent(this,Settings.class);
        intent.putExtra("callingActivity", nombreActivity );
        intent.putExtras(extras);
        startActivity(intent);
    }
    @VisibleForTesting
    public ProgressDialog mProgressDialog;

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        hideProgressDialog();
    }

    private void checkTheme() {
        int version = android.os.Build.VERSION.SDK_INT;
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean appTheme = SP.getBoolean("temaAplicacion", true);
        if (appTheme) {
            this.setTheme(R.style.AppTheme);
        } else {
            this.setTheme(R.style.AppThemeDark);
        }
    }

    private void ponIdioma(){
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        int valor = SP.getInt("idiomaAplicacion", 1);
        String idioma = "en";
        switch (valor){
            case 0:
                idioma = "en";
                break;
            case 1:
                idioma = "ca";
                break;
            case 2:
                idioma = "es";
                break;
        }
        setLocale(idioma);
    }
    private void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
    }

    protected int obtenFondo(){
        //Se consigue el color actual del fondo del activity. Por si no fuera un color, se pone a 0 (blanco) por defecto
        int color = 000;
        TypedValue a = new TypedValue();
        getTheme().resolveAttribute(android.R.attr.windowBackground, a, true);
        if (a.type >= TypedValue.TYPE_FIRST_COLOR_INT && a.type <= TypedValue.TYPE_LAST_COLOR_INT) {
            // si el windowBackground es un color, ya que puede ser otra cosa, por ejemplo un drawable
            color = a.data;
        }
        return color;
    }

    public void setSupportActionBar(@Nullable Toolbar toolbar){}

}