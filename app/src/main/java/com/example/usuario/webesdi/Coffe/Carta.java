package com.example.usuario.webesdi.Coffe;



import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.usuario.webesdi.MyAdapterCarta;
import com.example.usuario.webesdi.R;

public class Carta extends Fragment{

    String [] comida;
    String [] precio;
    String [] contenido;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ListView lst = (ListView) getView().findViewById(R.id.listaCarta);
        Resources res = getResources();
        comida=res.getStringArray(R.array.listaCarta);
        precio=res.getStringArray(R.array.listaCartaPrecio);
        contenido=res.getStringArray(R.array.listaCartaContenido);
        MyAdapterCarta myAdapterCarta = new MyAdapterCarta(getActivity(),comida,precio,contenido);
        lst.setAdapter(myAdapterCarta);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_carta, container, false);
        return rootView;
    }


}
