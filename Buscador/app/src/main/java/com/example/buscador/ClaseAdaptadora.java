package com.example.buscador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ClaseAdaptadora
        extends RecyclerView.Adapter<ClaseAdaptadora.ViewHolderRedes>
        implements Filterable, View.OnClickListener, View.OnLongClickListener {

    ArrayList<Redes> listaRedes;
    ArrayList <Redes> filtrarNombre;
    private View.OnClickListener listener;
    private View.OnLongClickListener list;

    public ClaseAdaptadora(ArrayList<Redes> listaRedes) {
        this.listaRedes = listaRedes;
        this.filtrarNombre = new ArrayList<>(listaRedes);
    }

    @Override
    public ViewHolderRedes onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mi_lista, null, false);

        view.setOnClickListener(this);

        view.setOnLongClickListener(this);

        return new ViewHolderRedes(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderRedes holder, int position) {
        Redes currentItem = listaRedes.get(position);
        holder.txtNombre.setText(currentItem.getNombre());
        holder.imagen.setImageResource(currentItem.getImagen());
    }


    @Override
    public int getItemCount() {
        return listaRedes.size();
    }

        public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

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

    @Override
    public Filter getFilter() {
        return filtrar;
    }

    private Filter filtrar = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Redes> filtrarLista = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filtrarLista.addAll(filtrarNombre);
            }
            else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Redes item : filtrarNombre) {
                    if (item.getNombre().toLowerCase().contains(filterPattern)) {
                        filtrarLista.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filtrarLista;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            listaRedes.clear();
            listaRedes.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };


    public class ViewHolderRedes extends RecyclerView.ViewHolder {

        TextView txtNombre;
        ImageView imagen;


        public ViewHolderRedes(View itemView) {
            super(itemView);
            txtNombre= (TextView) itemView.findViewById(R.id.txtRedes);
            imagen= (ImageView) itemView.findViewById(R.id.myImageView);
        }
    }
}