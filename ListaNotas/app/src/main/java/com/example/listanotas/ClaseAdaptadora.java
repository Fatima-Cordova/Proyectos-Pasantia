package com.example.listanotas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listanotas.util.Utilidades;

import java.util.ArrayList;

public class ClaseAdaptadora extends RecyclerView.Adapter<ClaseAdaptadora.ViewHolderNotas> {

    ArrayList<Notas> listaNotas;
    private ViewHolderNotas holder;
    private int position;

    public ClaseAdaptadora(ArrayList<Notas> listaNotas) {

        this.listaNotas = listaNotas;
    }


    @Override
    public ViewHolderNotas onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout = 0;
        if (Utilidades.visualizacion == Utilidades.LIST) {
            layout = R.layout.my_notes;
        } else {
            layout = R.layout.my_notes;
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(layout, null, false);

        return new ViewHolderNotas(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderNotas holder, int position) {
        holder.txtNotas.setText(listaNotas.get(position).getAnotacion());
    }

    @Override
    public int getItemCount() {
        return listaNotas.size();
    }

    public class ViewHolderNotas extends RecyclerView.ViewHolder {

        TextView txtNotas;

        public ViewHolderNotas(View itemView) {
            super(itemView);
            txtNotas = (TextView) itemView.findViewById(R.id.txtNotas);
        }
    }
}