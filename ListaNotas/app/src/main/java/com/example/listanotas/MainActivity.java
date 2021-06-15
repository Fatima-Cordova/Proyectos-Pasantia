package com.example.listanotas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ClaseAdaptadora adaptadora;
    ArrayList<Notas> listaNotas;
    RecyclerView recycler;
    EditText edtNotas;
    Button btnAgregar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAgregar = (Button)findViewById(R.id.btnAgregar);
        edtNotas = (EditText)findViewById(R.id.edtNota);
        recycler = (RecyclerView)findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        listaNotas = new ArrayList ();

        recycler.setHasFixedSize (true);
        adaptadora = new ClaseAdaptadora(listaNotas);
        recycler.setAdapter(adaptadora);

    }

    public void agregar (View view) {
        String nota = edtNotas.getText().toString();
        listaNotas.add(new Notas(nota));
        edtNotas.getText().clear();
        adaptadora.notifyDataSetChanged();
    }
}