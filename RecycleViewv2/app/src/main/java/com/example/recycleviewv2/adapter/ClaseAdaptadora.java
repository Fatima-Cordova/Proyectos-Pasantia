package com.example.recycleviewv2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.recycleviewv2.R;
import com.example.recycleviewv2.Utilidades;
import com.example.recycleviewv2.model.RedesSociales;

import java.util.ArrayList;

public class ClaseAdaptadora
        extends RecyclerView.Adapter<ClaseAdaptadora.ViewHolderRedes>
        implements View.OnClickListener, View.OnLongClickListener {

    ArrayList<RedesSociales> listaRedes;
    private View.OnClickListener listener;
    private View.OnLongClickListener list;

    public ClaseAdaptadora(ArrayList<RedesSociales> listaRedes) {
        this.listaRedes = listaRedes;
    }

    @Override
    public ViewHolderRedes onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout = 0;
        if (Utilidades.visualizacion == Utilidades.LIST) {
            layout = R.layout.my_list;
        } else {
            layout = R.layout.my_list;
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(layout, null, false);

        view.setOnClickListener(this);

        view.setOnLongClickListener(this);

        return new ViewHolderRedes(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderRedes holder, int position) {
        holder.txtNombre.setText(listaRedes.get(position).getNombre());

        if (Utilidades.visualizacion == Utilidades.LIST) {
            holder.imagen.setImageResource(listaRedes.get(position).getFoto());
        }
    }

    @Override
    public int getItemCount() {
        return listaRedes.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.onClick(view);
        }
    }

    public boolean onLongClick(View.OnLongClickListener list) {
        this.list = list;
        return false;
    }

    @Override
    public boolean onLongClick(View v) {
        if (list != null) {
            list.onLongClick(v);
        }
        return false;
    }


    public class ViewHolderRedes extends RecyclerView.ViewHolder {

        TextView txtNombre;
        ImageView imagen;

        public ViewHolderRedes(View itemView) {
            super(itemView);
            txtNombre= (TextView) itemView.findViewById(R.id.idNombre);
            if (Utilidades.visualizacion==Utilidades.LIST){
                imagen= (ImageView) itemView.findViewById(R.id.idImagen);
            }
        }
    }
}