package com.example.notas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.notas.model.Notas;
import com.example.notas.util.FileManager;
import com.google.gson.Gson;

public class EscribirActivity extends AppCompatActivity {

    EditText edttitulo, edtDescrp;
    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escribir);

        edttitulo = (EditText) findViewById(R.id.edtTitu);
        edtDescrp = (EditText) findViewById(R.id.edtDescrp);
        btnGuardar = (Button) findViewById(R.id.btnGuar);
    }

    public void guardar(View view){
        Notas notas = new Notas();
        notas.setTitulo(edttitulo.getText().toString());
        notas.setDescripcion(edtDescrp.getText().toString());
        notas.setId(System.currentTimeMillis());

        Gson gson = new Gson();
        String escribir = gson.toJson(notas);
        Log.d("Escribiendo",escribir);

        FileManager fileManager = new FileManager();
        fileManager.write(this.getFilesDir().toString(), String.valueOf(notas.getId()), escribir);
        this.finish();
    }
}