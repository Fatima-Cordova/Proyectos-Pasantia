package com.example.notas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notas.adapter.ClaseAdaptadora;
import com.example.notas.model.Notas;
import com.example.notas.util.FileManager;

import java.io.File;
import java.util.ArrayList;

public class LeerActivity extends AppCompatActivity {

    EditText edtTitulo, edtDescripcion;
    Button btnGuardar;
    private int nota = 0;
    public static final String EXTRA_ID = "";
    public static final String EXTRA_TITLE = "";
    public static final String EXTRA_DESCRIPTION = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leer);

        edtTitulo = (EditText) findViewById(R.id.edtTitulo);
        edtDescripcion = (EditText) findViewById(R.id.edtDescripcion);
        btnGuardar = (Button) findViewById(R.id.btnGuardar);

        FileManager fm = new FileManager();
        fm.readFile(LeerActivity.this.getFilesDir().toString(), "leer.txt");



    }

    public void guardarNota(View view) {
        String titulo = edtTitulo.getText().toString();
        String descripcion = edtDescripcion.getText().toString();
        if (titulo.trim().isEmpty() || descripcion.trim().isEmpty()) {
            Toast.makeText(this, "Por favor agregue un título y una descrpción", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, titulo);
        data.putExtra(EXTRA_DESCRIPTION, descripcion);
        if (nota > 0) {
            data.putExtra(EXTRA_ID, nota);
        }
        setResult(RESULT_OK, data);
        finish();
    }
}






















   /* Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
                setTitle("Edit Note");
                edtTitulo.setText(intent.getStringExtra(EXTRA_TITLE));
                edtDescripcion.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
                } else {
                setTitle("Add Note");
                }*/