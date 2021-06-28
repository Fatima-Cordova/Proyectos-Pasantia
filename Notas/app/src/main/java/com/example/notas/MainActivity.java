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
    int codigo = 0;
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


        permisos();

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

    private void permisos() {
            int escribir = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            int lectura = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

            if (escribir == PackageManager.PERMISSION_GRANTED && lectura == PackageManager.PERMISSION_GRANTED) {
            }
            if (ActivityCompat.shouldShowRequestPermissionRationale
                    (this, Manifest.permission.WRITE_EXTERNAL_STORAGE) ||
                    (ActivityCompat.shouldShowRequestPermissionRationale
                            (this, Manifest.permission.READ_EXTERNAL_STORAGE))) {
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Se necesitan permisos de lectura y escritura para continuar...");
                    builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            finish();
                        }
                    });

                    builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            ActivityCompat.requestPermissions(MainActivity.this,
                                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, codigo);
                            ActivityCompat.requestPermissions(MainActivity.this,
                                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, codigo);
                        }
                    });
                    builder.show();
                }
            }

            else{
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, codigo);
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, codigo);
            }
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


