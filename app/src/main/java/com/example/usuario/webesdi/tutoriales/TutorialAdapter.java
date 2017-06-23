package com.example.usuario.webesdi.tutoriales;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.usuario.webesdi.R;

import java.util.List;

/**
 * Created by Becario2 on 05/05/2017.
 */

public class TutorialAdapter extends RecyclerView.Adapter<TutorialAdapter.ViewHolder>{
    private List<Tutorial> listaTutorial;

    public TutorialAdapter(List<Tutorial> listaTutorial){
        this.listaTutorial = listaTutorial;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txtName,txtDescr, txtRank;
        public ImageView ivLogo;
        public ViewHolder(View v) {
            super(v);
            txtRank = (TextView) v.findViewById(R.id.tv_rank);
            txtName = (TextView) v.findViewById(R.id.tv_nombre);
            txtDescr = (TextView) v.findViewById(R.id.tv_descr);
            ivLogo = (ImageView) v.findViewById(R.id.iv_logo_tutorial);
        }
    }

    @Override
    public TutorialAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Creating a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_tutoriales,parent,false);

        //set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Tutorial tutorial = listaTutorial.get(position);
        holder.txtRank.setText(String.valueOf(tutorial.getRank()));
        holder.txtName.setText(tutorial.getName());
        holder.txtDescr.setText(tutorial.getDescripcio());
        holder.ivLogo.setImageResource(tutorial.getPic());
    }

    public int getItemCount() {
        return listaTutorial.size();
    }

}
