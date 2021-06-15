package com.example.listanotas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.listanotas.util.Utilidades;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> listaNotas;
    RecyclerView recycler;
    EditText edtNotas;
    TextView txtNotas;
    Button btnAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAgregar = (Button)findViewById(R.id.btnAgregar);
        edtNotas = (EditText)findViewById(R.id.edtNota);
        txtNotas = (TextView) findViewById(R.id.txtNotas);
        recycler = (RecyclerView)findViewById(R.id.recycler);
        listaNotas = new ArrayList ();
        recycler.setAdapter();

    }

    public void ingresar (View view) {
        String txtAgregarNota = edtNotas.getText().toString();
        listaNotas.add(txtAgregarNota);
        recycler.setText("");
        String output = "";
        for (String txt:listaNotas){
            output += txt;
        }
        txtNotas.setText(output);
    }
}