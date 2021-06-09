package com.example.widgets2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnFemenino).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarMaterialDialog();
            }
        });

        findViewById(R.id.btnMasculino).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarSegundoMaterialDialog();
            }
        });
    }



    private void mostrarMaterialDialog(){
        AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
        alerta.setTitle("");
        alerta.setMessage("¿Estas seguro de elegir esta opcion?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Su genero es Femenino",Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Elija nuevamente una de las 2 opciones", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }).show();
    }

    private void mostrarSegundoMaterialDialog(){
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setTitle("");
        alert.setMessage("¿Estas seguro de elegir esta opcion?")
            .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(),"Su genero es Masculino",Toast.LENGTH_LONG).show();
                }
            })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Elija nuevamente una de las 2 opciones", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }).show();
    }

}