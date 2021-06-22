package com.example.buscador;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ClaseAdaptadora adaptador;
    RecyclerView recycler;
    EditText edtBuscador;

    ArrayList <Redes> filtrarNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtBuscador = (EditText) findViewById(R.id.edtBuscador);
        llenarLista();
        recycler();



        edtBuscador.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
           adaptador.getFilter().filter(s);
        }
    });
}
    private  void llenarLista() {
        filtrarNombre = new ArrayList<>();
        filtrarNombre.add(new Redes("Discord",R.drawable.discord));
        filtrarNombre.add(new Redes("Facebook",R.drawable.facebook));
        filtrarNombre.add(new Redes("Instagram",R.drawable.instagram));
        filtrarNombre.add(new Redes("Pinterest",R.drawable.pinterest));
        filtrarNombre.add(new Redes("Snapchat",R.drawable.snapchat));
        filtrarNombre.add(new Redes("Telegram",R.drawable.telegram));
        filtrarNombre.add(new Redes("Twitter",R.drawable.twitter));
        filtrarNombre.add(new Redes("Whatsapp",R.drawable.whatsapp));
        filtrarNombre.add(new Redes("YouTube",R.drawable.youtube));
    }

    private void recycler(){
        recycler = (RecyclerView) findViewById(R.id.recycler);

        recycler.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        adaptador = new ClaseAdaptadora(filtrarNombre);

        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adaptador);

    }
}