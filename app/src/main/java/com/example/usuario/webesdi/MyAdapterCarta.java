package com.example.usuario.webesdi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Becario2 on 12/07/2017.
 */

public class MyAdapterCarta extends ArrayAdapter<String> {

    String [] comida;
    String [] precio;
    String [] contenido;
    Context mContext;

    public MyAdapterCarta(@NonNull Context context, String [] laComida, String [] elPrecio, String [] elContenido) {
        super(context, R.layout.custom_adapter_carta);
        this.comida =laComida;
        this.precio =elPrecio;
        this.contenido =elContenido;
        this.mContext=context;
    }

    @Override
    public int getCount(){
        return comida.length;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        MyAdapterCarta.ViewHolder mViewHolder = new MyAdapterCarta.ViewHolder();
        if(convertView==null) {
            LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.custom_adapter_carta, parent, false);
            mViewHolder.txtcomida = (TextView) convertView.findViewById(R.id.textComida);
            mViewHolder.txtprecio = (TextView) convertView.findViewById(R.id.textPrecio);
            mViewHolder.txtcontenido = (TextView) convertView.findViewById(R.id.textContenido);
            convertView.setTag(mViewHolder);
        }else{
            mViewHolder = (MyAdapterCarta.ViewHolder)convertView.getTag();
        }
        mViewHolder.txtcomida.setText(comida[position]);
        mViewHolder.txtprecio.setText(precio[position]);
        mViewHolder.txtcontenido.setText(contenido[position]);

        return convertView;
    }


    static class ViewHolder{
        TextView txtcomida;
        TextView txtprecio;
        TextView txtcontenido;
    }
}
