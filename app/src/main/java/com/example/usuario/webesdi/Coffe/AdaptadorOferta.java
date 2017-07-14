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



    public class AdaptadorOferta extends RecyclerView.Adapter<AdaptadorOferta.ViewHolder> {
        private List<Menjar> listaMenjar;
        private Context context;

        public AdaptadorOferta(Context context, List<Menjar> menjar) {
            this.context = context;
            this.listaMenjar = menjar;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.oferta_item_list,parent,false);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            String idioma = Locale.getDefault().toString(); // es_ES
            Glide.with(context).load(listaMenjar.get(position).getPicMenjar()).into(holder.imageView);
            if (idioma.equals("ca")) {
                holder.nom.setText(listaMenjar.get(position).getNomMenjarCatala());
            } else if (idioma.equals("es")) {
                holder.nom.setText(listaMenjar.get(position).getNomMenjarCastella());
            } else if (idioma.equals("en")) {
                holder.nom.setText(listaMenjar.get(position).getNomMenjarAngles());
            }
            holder.preu.setText(""+listaMenjar.get(position).getPreuMenjar());
        }
        @Override
        public int getItemCount() {
            return listaMenjar.size();
        }

        public  class ViewHolder extends  RecyclerView.ViewHolder{

            public ImageView imageView;
            public TextView nom;;
            public TextView preu;

            public ViewHolder(View itemView) {
                super(itemView);
                nom = (TextView) itemView.findViewById(R.id.tv_nommenjar);
                imageView = (ImageView) itemView.findViewById(R.id.iv_imatge_menjar);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                preu = (TextView) itemView.findViewById(R.id.tv_preu);
            }
        }
    }



