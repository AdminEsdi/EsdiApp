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

        holder.name.setText(listaEmpresas.get(position).getName());
        holder.descripcio.setText(listaEmpresas.get(position).getDescripcio());
        Glide.with(context).load(listaEmpresas.get(position).getPic()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return listaEmpresas.size();
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder{

        public TextView name;
        public TextView descripcio;
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tv_nombre);
            imageView = (ImageView) itemView.findViewById(R.id.iv_logo_empresa);
            descripcio = (TextView) itemView.findViewById(R.id.tv_descr);
        }
    }
}

