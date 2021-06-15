package com.example.listanotas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ClaseAdaptadora extends RecyclerView.Adapter<ClaseAdaptadora.ViewHolderNotas> {

    ArrayList<Notas> listaNotas;


    public ClaseAdaptadora(ArrayList<Notas> listaNotas) {

        this.listaNotas = listaNotas;
    }


    @Override
    public ViewHolderNotas onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_notes,parent,false);
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