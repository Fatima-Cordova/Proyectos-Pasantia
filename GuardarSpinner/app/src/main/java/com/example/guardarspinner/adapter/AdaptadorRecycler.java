package com.example.guardarspinner.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.guardarspinner.R;

public class AdaptadorRecycler extends RecyclerView.Adapter<AdaptadorRecycler.RedesSociales> {
    String redes[];
    int imagenes[];
    Context contxt;

    public AdaptadorRecycler(Context context, String redesSociales[], int img[]) {
        contxt = context;
        redes = redesSociales;
        imagenes = img;

    }

    @NonNull
    @Override
    public RedesSociales onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(contxt);
        View view = inflater.inflate(R.layout.mi_lista, parent, false);
        return new RedesSociales(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RedesSociales holder, int position) {
        holder.txtNombreUsuario.setText(redes[position]);
        holder.imageView.setImageResource(imagenes[position]);
    }

    @Override
    public int getItemCount() {
        return imagenes.length;
    }

    public class RedesSociales extends RecyclerView.ViewHolder {

        TextView txtNombreUsuario;
        ImageView imageView;

        public RedesSociales(@NonNull View itemView) {
            super(itemView);
            txtNombreUsuario = itemView.findViewById(R.id.txtNombreUsuario);
            imageView = itemView.findViewById(R.id.myImageView);
        }
    }
}