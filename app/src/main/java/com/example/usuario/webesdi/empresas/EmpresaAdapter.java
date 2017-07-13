package com.example.usuario.webesdi.empresas;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.usuario.webesdi.R;

import java.util.List;
import java.util.Locale;

/**
 * Created by PracticasEsdi on 28/04/2017.
 */

public class EmpresaAdapter extends RecyclerView.Adapter<EmpresaAdapter.ViewHolder> {
    private List<Empresa> listaEmpresas;
    private Context context;

    public EmpresaAdapter(Context context, List<Empresa> empresa) {
        this.context = context;
        this.listaEmpresas = empresa;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.empresa_item_list,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String idioma = Locale.getDefault().toString(); // es_ES
        if (idioma.equals("ca")) {
            holder.descripcio.setText(listaEmpresas.get(position).getDescripcioCatala());
        } else if (idioma.equals("es")) {
            holder.descripcio.setText(listaEmpresas.get(position).getDescripcioCastella());
        } else if (idioma.equals("en")) {
            holder.descripcio.setText(listaEmpresas.get(position).getDescripcioAngles());
        } Glide.with(context).load(listaEmpresas.get(position).getPic()).into(holder.imageView);
    }
    @Override
    public int getItemCount() {
        return listaEmpresas.size();
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder{

        public TextView descripcio;
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv_logo_empresa);
            descripcio = (TextView) itemView.findViewById(R.id.tv_descr);
        }
    }
}

