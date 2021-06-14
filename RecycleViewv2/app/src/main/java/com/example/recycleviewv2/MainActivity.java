package com.example.recycleviewv2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.recycleviewv2.adapter.ClaseAdaptadora;
import com.example.recycleviewv2.model.RedesSociales;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<RedesSociales> listaRedes;
    RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler();

    }

    private void llenarLista() {
        listaRedes.add(new RedesSociales("Discord",R.drawable.discord));
        listaRedes.add(new RedesSociales("Facebook",R.drawable.facebook));
        listaRedes.add(new RedesSociales("Instagram",R.drawable.instagram));
        listaRedes.add(new RedesSociales("Pinterest",R.drawable.pinterest));
        listaRedes.add(new RedesSociales("Snapchat",R.drawable.snapchat));
        listaRedes.add(new RedesSociales("Telegram",R.drawable.telegram));
        listaRedes.add(new RedesSociales("Twitter",R.drawable.twitter));
        listaRedes.add(new RedesSociales("Whatsapp",R.drawable.whatsapp));
        listaRedes.add(new RedesSociales("YouTube",R.drawable.youtube));
    }

    public void onClick(View view) {

        switch (view.getId()){
            case R.id.recycler: Utilidades.visualizacion=Utilidades.LIST;
                break;
        }
        recycler();
    }

    private void recycler() {
        listaRedes = new ArrayList<>();
        recycler = (RecyclerView) findViewById(R.id.recycler);

        if (Utilidades.visualizacion == Utilidades.LIST) {
            recycler.setLayoutManager(new LinearLayoutManager(this));
        }

        llenarLista();

        ClaseAdaptadora adapter = new ClaseAdaptadora(listaRedes);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),
                        "Usted Seleccionó: " + listaRedes.get(recycler.getChildAdapterPosition(view)).getNombre(), Toast.LENGTH_SHORT).show();
            }
        });
        recycler.setAdapter(adapter);

        adapter.onLongClick(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Redes Sociales");
                alert.setMessage(listaRedes.get(recycler.getChildAdapterPosition(v)).getNombre())
                        .setPositiveButton("Si", new DialogInterface.OnClickListener () {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }).show();
                return false;
            }
        });
    }
}