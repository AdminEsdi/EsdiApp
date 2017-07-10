package com.example.usuario.webesdi.Tutorial;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.usuario.webesdi.R;

/**
 * Created by Becario2 on 06/07/2017.
 */

public class MyAdapter extends ArrayAdapter<String> {

    String [] titulo;
    String [] subtitulo;
    Context mContext;

    public MyAdapter(@NonNull Context context, String [] elTitulo, String [] elSubtitulo) {
        super(context, R.layout.custom_adapter_layaout);
        this.titulo=elTitulo;
        this.subtitulo=elSubtitulo;
        this.mContext=context;
    }

    @Override
    public int getCount(){
        return titulo.length;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder mViewHolder = new ViewHolder();
        if(convertView==null) {
            LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.custom_adapter_layaout, parent, false);
            mViewHolder.txttitulo = (TextView) convertView.findViewById(R.id.titulo);
            mViewHolder.txtsubtitulo = (TextView) convertView.findViewById(R.id.subtitulo);
            convertView.setTag(mViewHolder);
        }else{
            mViewHolder = (ViewHolder)convertView.getTag();
        }
        mViewHolder.txttitulo.setText(titulo[position]);
        mViewHolder.txtsubtitulo.setText(subtitulo[position]);


        return convertView;
    }


    static class ViewHolder{
        TextView txttitulo;
        TextView txtsubtitulo;
    }
}
