package com.example.buscador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ClaseAdaptadora extends RecyclerView.Adapter<ClaseAdaptadora.RedesSociales> {

    String redesSociales[];
    int imagenes[];
    Context contxt;

    public ClaseAdaptadora(String redes[], int img[], Context context) {
        redesSociales = redes;
        imagenes = img;
        contxt = context;
    }

    public ClaseAdaptadora(Context applicationContext, List<Redes> redesSociales) {
    }


    @Override
    public RedesSociales onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(contxt);
        View view = inflater.inflate(R.layout.mi_lista,parent, false);
        return new RedesSociales(view);
    }

    @Override
    public void onBindViewHolder(ClaseAdaptadora.RedesSociales holder, int position) {
        holder.txtRedes.setText(redesSociales[position]);
        holder.imageView.setImageResource(imagenes[position]);
    }

    @Override
    public int getItemCount() {
        return imagenes.length;
    }

    public class RedesSociales extends RecyclerView.ViewHolder {

        TextView txtRedes;
        ImageView imageView;

        public RedesSociales(View itemView) {
            super(itemView);
            txtRedes = itemView.findViewById(R.id.txtRedes);
            imageView = itemView.findViewById(R.id.myImageView);
        }
    }
}
