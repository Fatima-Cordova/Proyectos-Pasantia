package com.example.guardarspinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guardarspinner.model.Usuario;
import com.example.guardarspinner.util.Constante;
import com.example.guardarspinner.util.Cypher;

import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private TextView txtNombre, txtRed;
    private ImageView imgRed;
    private RoomDB dataBase;
    private int idUser = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        txtNombre = (TextView) findViewById(R.id.txtNombre);
        txtRed = (TextView) findViewById(R.id.txtRed);
        imgRed = (ImageView) findViewById(R.id.imageView);


        dataBase = RoomDB.getInstance(this);
        extraerDatosDB();
    }

    private void extraerDatosDB() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                Usuario usuario = dataBase.usuariosDao().getUser(idUser);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        datosAMostrar(usuario);
                    }
                });
            }
        }).start();
    }

    private void datosAMostrar(Usuario usuario) {

        if(usuario != null) {
            try {
                imgRed.setImageDrawable(getDrawable(usuario.getIdSocialIcon()));
                txtRed.setText(usuario.getNameNetworkSocial());
                txtNombre.setText(Cypher.decrypt(usuario.getUser()));
            } catch(Exception exception) {
                mostrarMensaje(Constante.ERROR, "No se han podido leer los datos de la Base de Datos");
            }
        } else {
            mostrarMensaje(Constante.ERROR, "No se han podido leer los datos de la Base de Datos");
        }
    }

    public void regresar(View view) {
        finish();
    }

    private void mostrarMensaje(int type, String mensaje) {
        switch (type) {
            case Constante.CAMPOS_VACIOS:
                Toast.makeText(this, "Los campos no deben estar vacíos", Toast.LENGTH_SHORT).show();
                break;
            case Constante.DATOS_GUARDADOS:
                Toast.makeText(this, "Datos guardados", Toast.LENGTH_SHORT).show();
                break;
            case Constante.ERROR:
                Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(this, "Error desconocido...", Toast.LENGTH_SHORT).show();
        }
    }
}