package com.example.usuario.webesdi;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by JR on 27/04/2017.
 */

public class MensajesAdaptador
        extends RecyclerView.Adapter<MensajesAdaptador.ViewHolder>
        implements View.OnClickListener {

    private View.OnClickListener listener;
    private ArrayList<MensajesIncidencias> datos;

    public static class ViewHolder
            extends RecyclerView.ViewHolder {

        private TextView lblEquipo;
        private TextView lblFecha;
        private TextView lblTitulo;

        public ViewHolder(View itemView) {
            super(itemView);

            lblEquipo = (TextView)itemView.findViewById(R.id.lblEquipo);
            lblFecha = (TextView)itemView.findViewById(R.id.lblFecha);
            lblTitulo = (TextView)itemView.findViewById(R.id.lblTitulo);
        }

        public void bindMenIn(MensajesIncidencias t) {
            lblEquipo.setText(t.getEquipo());
            lblFecha.setText(t.getFecha());
            lblTitulo.setText(t.getTitulo());
        }
    }

    public MensajesAdaptador(ArrayList<MensajesIncidencias> datos) {
        this.datos = datos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_lista, viewGroup, false);

        itemView.setOnClickListener(this);
        //android:background="?android:attr/selectableItemBackground"

        ViewHolder tvh = new ViewHolder(itemView);

        return tvh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int pos) {
        MensajesIncidencias item = datos.get(pos);

        viewHolder.bindMenIn(item);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener != null)
            listener.onClick(view);
    }
}
