package com.example.guardarspinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.guardarspinner.model.RedesSociales;

import java.util.ArrayList;

public class ListaActivity extends AppCompatActivity {

    ArrayList<RedesSociales> listaRedes;
    RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);



    }

    private void llenarLista() {
        listaRedes.add(new RedesSociales("Facebook",R.drawable.facebook));
        listaRedes.add(new RedesSociales("Instagram",R.drawable.instagram));
        listaRedes.add(new RedesSociales("Snapchat",R.drawable.snapchat));
        listaRedes.add(new RedesSociales("Telegram",R.drawable.telegram));
        listaRedes.add(new RedesSociales("Twitter",R.drawable.twitter));
        listaRedes.add(new RedesSociales("YouTube",R.drawable.youtube));
    }
}