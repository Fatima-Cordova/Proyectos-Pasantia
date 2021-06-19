package com.example.buscador;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ClaseAdaptadora adaptador;
    RecyclerView recycler;
    EditText edtBuscador;
    Button btnBuscar;

    ArrayList <Redes> filtrarNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recycler = (RecyclerView) findViewById(R.id.recycler);
        edtBuscador = (EditText) findViewById(R.id.edtBuscador);
        btnBuscar = (Button) findViewById(R.id.btnBuscar);


        filtrarNombre = new ArrayList<>();
        filtrarNombre = llenarLista();

        adaptador = new ClaseAdaptadora(filtrarNombre);
        recycler.setAdapter(adaptador);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        edtBuscador.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                filtrarNombre.clear();
                filtrarNombre = llenarLista();
                if(s.toString().isEmpty()){
                    recycler.setAdapter(new ClaseAdaptadora(filtrarNombre));
                }
                else{
                    search(s.toString());
                }
               adaptador.notifyDataSetChanged();
            }
        });
    }

    private  ArrayList<Redes> llenarLista() {
        ArrayList<Redes> redS = new ArrayList<>();
        redS.add(new Redes("Discord",R.drawable.discord));
        redS.add(new Redes("Facebook",R.drawable.facebook));
        redS.add(new Redes("Instagram",R.drawable.instagram));
        redS.add(new Redes("Pinterest",R.drawable.pinterest));
        redS.add(new Redes("Snapchat",R.drawable.snapchat));
        redS.add(new Redes("Telegram",R.drawable.telegram));
        redS.add(new Redes("Twitter",R.drawable.twitter));
        redS.add(new Redes("Whatsapp",R.drawable.whatsapp));
        redS.add(new Redes("YouTube",R.drawable.youtube));
        return redS;
    }

    private void buscar(String red){
        recycler.setAdapter(new ClaseAdaptadora(filtrarNombre));
        adaptador.notifyDataSetChanged();
    }

    private void search(String busqueda){
        filtrarNombre.stream().filter(redes -> redes.getNombre()=="Discord");
        recycler.setAdapter(new ClaseAdaptadora(filtrarNombre));
        adaptador.notifyDataSetChanged();
    }
}