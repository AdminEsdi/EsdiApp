package com.example.usuario.webesdi.Coffe;

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


public class AdaptadorMenu extends RecyclerView.Adapter<AdaptadorMenu.ViewHolder> {
    private List<Menu> listaMenu;
    private Context context;

    public AdaptadorMenu(Context context, List<Menu> menu) {
        this.context = context;
        this.listaMenu = menu;
    }

    @Override
    public AdaptadorMenu.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item_list,parent,false);
        return new AdaptadorMenu.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdaptadorMenu.ViewHolder holder, int position) {

        String idioma = Locale.getDefault().toString(); // es_ES
        if (idioma.equals("ca")) {
            holder.dia.setText(listaMenu.get(position).getDiaCatala());
            holder.primerPlat.setText(listaMenu.get(position).getPrimerPlatMenuCatala());
            holder.segonPlat.setText(listaMenu.get(position).getSegonPlatMenuCatala());
            holder.postre.setText(listaMenu.get(position).getPostreMenuCatala());
        } else if (idioma.equals("es")) {
            holder.dia.setText(listaMenu.get(position).getDiaCastella());
            holder.primerPlat.setText(listaMenu.get(position).getPrimerPlatMenuCastella());
            holder.segonPlat.setText(listaMenu.get(position).getSegonPlatMenuCastella());
            holder.postre.setText(listaMenu.get(position).getPostreMenuCastella());
        } else if (idioma.equals("en")) {
            holder.dia.setText(listaMenu.get(position).getDiaAngles());
            holder.primerPlat.setText(listaMenu.get(position).getPrimerPlatMenuAngles());
            holder.segonPlat.setText(listaMenu.get(position).getSegonPlatMenuAngles());
            holder.postre.setText(listaMenu.get(position).getPostreMenuAngles());
        }
    }
    @Override
    public int getItemCount() {
        return listaMenu.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{

        public TextView dia;
        public TextView primerPlat;
        public TextView segonPlat;
        public TextView postre;

        public ViewHolder(View itemView) {
            super(itemView);
            dia = (TextView) itemView.findViewById(R.id.tv_dia);
            primerPlat = (TextView) itemView.findViewById(R.id.tv_primerPlat);
            segonPlat = (TextView) itemView.findViewById(R.id.tv_segonPlat);
            postre = (TextView) itemView.findViewById(R.id.tv_postres);
        }
    }
}



