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
import com.example.guardarspinner.RoomDB;
import com.example.guardarspinner.model.RedesSociales;
import com.example.guardarspinner.model.Usuario;

import java.util.ArrayList;

public class Adaptador extends ArrayAdapter<RedesSociales> {

    public Adaptador(Context context, ArrayList<RedesSociales> iconoList){
        super(context,0, iconoList);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent){
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.spinner_iconos, parent, false
            );
        }
        ImageView iconoImagen = convertView.findViewById(R.id.icono_imagen);
        TextView txtIcono = convertView.findViewById(R.id.txtIcono);

        RedesSociales redesSociales = getItem(position);

        if (redesSociales != null) {
            iconoImagen.setImageResource(redesSociales.getIdSocial());
            txtIcono.setText(redesSociales.getNombreSocial());
        }
        return convertView;
    }
}