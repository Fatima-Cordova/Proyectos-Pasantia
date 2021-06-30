package com.example.notas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.notas.adapter.ClaseAdaptadora;
import com.example.notas.model.Notas;
import com.example.notas.util.FileManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ClaseAdaptadora adaptadora;
    RecyclerView recycler;
    EditText edtBuscador;
    Button btnAgregar;
    ArrayList<Notas> listaNotas;
    FileManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm = new FileManager();
        listaNotas = fm.getListFile(MainActivity.this.getFilesDir().toString());

        btnAgregar = (Button)findViewById(R.id.btnAgregar);
        edtBuscador = (EditText)findViewById(R.id.edtBuscador);
        recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
      //  listaNotas = new ArrayList ();

        recycler.setHasFixedSize (true);
        adaptadora = new ClaseAdaptadora(listaNotas);
        recycler.setAdapter(adaptadora);

        edtBuscador.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                adaptadora.getFilter().filter(s);
            }
        });
        adaptadora.notifyDataSetChanged();
    }

    public void agregar (View view) {
        Intent intent = new Intent(MainActivity.this, EscribirActivity.class);

        startActivity(intent);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        this.listaNotas.clear();
        popularLista();
    }

    private void popularLista(){
        listaNotas = fm.getListFile(this.getFilesDir().toString());
        adaptadora.notifyDataSetChanged();
    }
}


