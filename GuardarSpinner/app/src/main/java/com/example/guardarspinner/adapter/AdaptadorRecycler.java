package com.example.guardarspinner.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.guardarspinner.R;
import com.example.guardarspinner.model.Usuario;

import java.util.ArrayList;


public class AdaptadorRecycler extends RecyclerView.Adapter<AdaptadorRecycler.ViewHolderUsuario>
        implements View.OnClickListener {

    ArrayList<Usuario> listaUsuarios;
    private Activity context;
    private View.OnClickListener listener;

    public AdaptadorRecycler(Activity context, ArrayList<Usuario> usuarios) {
        this.context = context;
        this.listaUsuarios = usuarios;
    }

    @Override
    public AdaptadorRecycler.ViewHolderUsuario onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mi_lista, null, false);

        return new ViewHolderUsuario(view);
    }

    @Override
    public void onBindViewHolder(AdaptadorRecycler.ViewHolderUsuario holder, int position) {
        // int id = 0;
        holder.txtNombreUsuario.setText(listaUsuarios.get(position).getUser());
    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.onClick(view);
        }
    }

    public class ViewHolderUsuario extends RecyclerView.ViewHolder{

        private TextView txtNombreUsuario;
        private ImageView imgRed;

        public ViewHolderUsuario(View itemView) {
            super(itemView);
            txtNombreUsuario = (TextView) itemView.findViewById(R.id.txtNombreUsuario);
            imgRed = (ImageView) itemView.findViewById(R.id.myImageView);
        }
    }
}















/* public interface RecyclerItemClick {
        void itemClick(Usuario usuario);
    }*/
// this.itemClick = itemClick; RecyclerItemClick itemClick// private RecyclerItemClick itemClick;