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
                mostrarMensajes(dosBotones);
            }
        });

        findViewById(R.id.btnMasculino).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarMensajes(unaAccion);
            }
        });
    }


    final int dosBotones = 1;
    final int unaAccion = 2;

    private void mostrarMensajes(int tipoMsj){
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        switch (tipoMsj){
            case dosBotones:
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
                        });
                break;
            case unaAccion:
                alert.setTitle("Una sola accion");
                alert.setMessage("Presione si, por favor")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "Presiono el boton",Toast.LENGTH_LONG).show();
                            }
                        });
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + tipoMsj);
        }
        alert.show();
    }

}