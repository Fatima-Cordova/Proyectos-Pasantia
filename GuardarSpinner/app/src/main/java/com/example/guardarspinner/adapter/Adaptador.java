package com.example.guardarspinner.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.guardarspinner.R;
import com.example.guardarspinner.model.Usuario;

import java.util.ArrayList;

public class Adaptador extends ArrayAdapter<Usuario> {

    public Adaptador(Context context, ArrayList<Usuario> iconoList){
        super(context,0, iconoList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent){
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.spinner_iconos, parent, false);
        }

        ImageView iconoImagen = convertView.findViewById(R.id.icono_imagen);
        TextView txtIcono = convertView.findViewById(R.id.txtIcono);

        Usuario usuario = getItem(position);

        if (usuario != null) {
            iconoImagen.setImageResource(usuario.getId());
            txtIcono.setText(usuario.getNameNetworkSocial());
        }
        return convertView;
    }
}