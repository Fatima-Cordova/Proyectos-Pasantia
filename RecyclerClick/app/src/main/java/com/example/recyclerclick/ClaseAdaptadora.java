package com.example.recyclerclick;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ClaseAdaptadora
        extends RecyclerView.Adapter<ClaseAdaptadora.ViewHolderNotas>
        implements View.OnClickListener, View.OnLongClickListener {

    ArrayList<Listas> listaNotas;
    private View.OnClickListener listener;
    private View.OnLongClickListener list;

    public ClaseAdaptadora(ArrayList<Listas> listaNotas) {

        this.listaNotas = listaNotas;
    }

    @Override
    public ViewHolderNotas onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_list,parent,false);

        view.setOnClickListener(this);

        view.setOnLongClickListener(this);

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

    public void removeItem(int position){
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


    public class ViewHolderNotas extends RecyclerView.ViewHolder {

        TextView txtNotas;

        public ViewHolderNotas(View itemView) {
            super(itemView);
            txtNotas = (TextView) itemView.findViewById(R.id.txtNotas);
        }
    }
}