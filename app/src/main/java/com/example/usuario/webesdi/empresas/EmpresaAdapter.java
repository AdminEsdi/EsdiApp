package com.example.usuario.webesdi.empresas;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.usuario.webesdi.R;

import java.util.List;

/**
 * Created by PracticasEsdi on 28/04/2017.
 */

public class EmpresaAdapter extends RecyclerView.Adapter<EmpresaAdapter.ViewHolder> {
    private List<Empresa> listaEmpresas;

    public EmpresaAdapter(List<Empresa> listaEmpresas){
        this.listaEmpresas = listaEmpresas;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txtName,txtDescr, txtRank;
        public ImageView ivLogo;
        public ViewHolder(View v) {
            super(v);
            txtRank = (TextView)v.findViewById(R.id.tv_rank);
            txtName = (TextView) v.findViewById(R.id.tv_nombre);
            txtDescr = (TextView) v.findViewById(R.id.tv_descr);
            ivLogo = (ImageView) v.findViewById(R.id.iv_logo_empresa);
        }
    }
    //Create new views (invoked by the layout manager)
    public EmpresaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //Creating a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.empresa_item_list,parent,false);

        //set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }
    public void onBindViewHolder(EmpresaAdapter.ViewHolder holder, int position) {

        // - get element from arraylist at this position
        // - replace the contents of the view with that element

        Empresa empresa = listaEmpresas.get(position);
        holder.txtRank.setText(String.valueOf(empresa.getRank()));
        holder.txtName.setText(empresa.getName());
        holder.txtDescr.setText(empresa.getDescripcio());
        holder.ivLogo.setImageResource(empresa.getPic());
    }

    public int getItemCount() {
        return listaEmpresas.size();
    }

}

