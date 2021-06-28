package com.example.notas.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.notas.R;
import com.example.notas.model.Notas;

import java.util.ArrayList;
import java.util.List;

public class ClaseAdaptadora
        extends RecyclerView.Adapter<ClaseAdaptadora.ViewHolderNotas>
        implements Filterable,View.OnClickListener, View.OnLongClickListener {

    ArrayList<Notas> listaNotas;
    ArrayList<Notas> filtrarNombre;
    private View.OnClickListener listener;
    private View.OnLongClickListener list;

    public ClaseAdaptadora(ArrayList<Notas> listaNotas) {
        this.listaNotas = listaNotas;
        this.filtrarNombre = new ArrayList<>(listaNotas);
    }


    @Override
    public ViewHolderNotas onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_notes, parent, false);
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        return new ViewHolderNotas(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderNotas holder, int position) {
        Notas currentItem = listaNotas.get(position);
        holder.txtTitulo.setText(currentItem.getTitulo());
    }

    @Override
    public int getItemCount() {
        return listaNotas.size();
    }

    public void removeItem(int position) {
        listaNotas.remove(position);
        notifyItemRemoved(position);
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

    @Override
    public Filter getFilter() {
        return filtrar;
    }

    private Filter filtrar = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Notas> filtrarLista = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filtrarLista.addAll(filtrarNombre);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Notas item : filtrarNombre) {
                    if (item.getTitulo().toLowerCase().contains(filterPattern)) {
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
            listaNotas.clear();
            listaNotas.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

        public class ViewHolderNotas extends RecyclerView.ViewHolder {
            TextView txtTitulo;

            public ViewHolderNotas(View itemView) {
                super(itemView);
                txtTitulo = (TextView) itemView.findViewById(R.id.txtTitulo);
            }
        }
}

