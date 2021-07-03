package com.example.notasdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.notasdemo.model.Nota;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText edtEscribirNota;
    Button btnAgregar, btnBorrar;
    RecyclerView recycler;
    List<Nota> dataList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    RoomBD database;
    ClaseAdaptadora adaptadora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtEscribirNota = (EditText) findViewById(R.id.edtEscribirNota);
        btnAgregar = (Button) findViewById(R.id.btnAgregar);
        btnBorrar = (Button) findViewById(R.id.btnEliminar);
        recycler = (RecyclerView) findViewById(R.id.recycler);

        database = RoomBD.getInstance(this);
        dataList = database.notaDao().getAll();

        linearLayoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(linearLayoutManager);
        adaptadora = new ClaseAdaptadora(MainActivity.this,dataList);
        recycler.setAdapter(adaptadora);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sText = edtEscribirNota.getText().toString().trim();
                if (!sText.equals("")){
                    Nota nota = new Nota();
                    nota.setText(sText);
                    database.notaDao().insert(nota);
                    edtEscribirNota.setText("");
                    dataList.clear();
                    dataList.addAll(database.notaDao().getAll());
                    adaptadora.notifyDataSetChanged();
                }
            }
        });

        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.notaDao().reset(dataList);
                dataList.clear();
                dataList.addAll(database.notaDao().getAll());
                adaptadora.notifyDataSetChanged();
            }
        });
    }
}