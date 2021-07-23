package com.example.guardarspinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guardarspinner.adapter.Adaptador;
import com.example.guardarspinner.model.Usuario;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText edtNombre;
    private Button btnGuardar, btnMostrar;
    private TextView txtNotas;
    private Spinner spIconos;
    private RoomDB dataBase;
    private ArrayList<Usuario> usuario;
    private Adaptador adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNombre = (EditText) findViewById(R.id.edtNombre);
        btnMostrar = (Button) findViewById(R.id.btnMostrar);
        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        spIconos = (Spinner) findViewById(R.id.spIconos);

        dataBase = RoomDB.getInstance(this);
        adaptador = new Adaptador(this, usuario);
        spIconos.setAdapter(adaptador);
    }


}