package com.example.usuario.webesdi.Coffe;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.usuario.webesdi.R;

import java.util.List;
import java.util.Locale;

/**
 * Created by Becario2 on 12/07/2017.
 */

public class MyAdapterCarta extends RecyclerView.Adapter<MyAdapterCarta.ViewHolder> {

    private List<ConstructorCarta> listaCarta;
    Context mContext;

    public MyAdapterCarta(@NonNull Context context, List<ConstructorCarta> carta) {
        //super(context, R.layout.custom_adapter_carta);
        this.listaCarta=carta;
        this.mContext=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_adapter_carta,parent,false);
        return new ViewHolder(itemView);
    }

    //Con este metodo conseguimos instanciar en el holder lo recogido anteriormente en la BBDD y instanciado en la clase ConstructorCarta
    @Override
    public void onBindViewHolder(ViewHolder mViewHolder, int position) {
        String idioma = Locale.getDefault().toString(); // es_ES
        if (idioma.equals("ca")) {
            mViewHolder.txtcomida.setText(listaCarta.get(position).getNombreCat());
            mViewHolder.txtprecio.setText(listaCarta.get(position).getPrecio());
            mViewHolder.txtcontenido.setText(listaCarta.get(position).getDescripCat());
        } else if (idioma.equals("es")) {
            mViewHolder.txtcomida.setText(listaCarta.get(position).getNombreEsp());
            mViewHolder.txtprecio.setText(listaCarta.get(position).getPrecio());
            mViewHolder.txtcontenido.setText(listaCarta.get(position).getDescripEsp());
        } else if (idioma.equals("en")) {
            mViewHolder.txtcomida.setText(listaCarta.get(position).getNombreEng());
            mViewHolder.txtprecio.setText(listaCarta.get(position).getPrecio());
            mViewHolder.txtcontenido.setText(listaCarta.get(position).getDescripEng());
        }
    }

    @Override
    public int getItemCount(){
        return listaCarta.size();
    }

    //Con el viewHolder conseguimos detectar donde se encuentran los TextViews.
    public class ViewHolder extends  RecyclerView.ViewHolder{
        TextView txtcomida;
        TextView txtprecio;
        TextView txtcontenido;
        public ViewHolder(View itemView) {
            super(itemView);
            txtcomida = (TextView) itemView.findViewById(R.id.textComida);
            txtprecio = (TextView) itemView.findViewById(R.id.textPrecio);
            txtcontenido = (TextView) itemView.findViewById(R.id.textContenido);
        }
    }
}
