package com.example.spinner.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.spinner.R;
import com.example.spinner.model.IconoRedes;

import java.util.ArrayList;

public class IconoAdaptador extends ArrayAdapter <IconoRedes> {

    public IconoAdaptador(Context context, ArrayList<IconoRedes> iconoList){
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
                    R.layout.spinner_iconos, parent, false
            );
        }
        ImageView iconoImagen = convertView.findViewById(R.id.icono_imagen);
        TextView txtIcono = convertView.findViewById(R.id.txtIcono);

        IconoRedes iconoRedes = getItem(position);

        if (iconoRedes != null) {
            iconoImagen.setImageResource(iconoRedes.getIcono());
            txtIcono.setText(iconoRedes.getNombre());
        }
        return convertView;
    }
}
