package com.example.guardarspinner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guardarspinner.adapter.Adaptador;
import com.example.guardarspinner.model.RedesSociales;
import com.example.guardarspinner.model.Usuario;
import com.example.guardarspinner.util.Constante;
import com.example.guardarspinner.util.Cypher;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText edtNombre;
    private Button btnGuardar, btnMostrar;
    private Spinner spIconos;
    private RoomDB dataBase;
    private ArrayList<RedesSociales> redesSociales;
    private Adaptador adaptador;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNombre = (EditText) findViewById(R.id.edtNombre);
        btnMostrar = (Button) findViewById(R.id.btnMostrar);
        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        spIconos = (Spinner) findViewById(R.id.spIconos);

        initList();
        dataBase = RoomDB.getInstance(this);
        adaptador = new Adaptador(this, redesSociales);
        spIconos.setAdapter(adaptador);
    }

    public void guardar (View view) {
        String nombre = edtNombre.getText().toString().trim();
        RedesSociales redesSociales = (RedesSociales) spIconos.getSelectedItem();

        if (nombre.isEmpty()){
            mostrarMsj(Constante.CAMPOS_VACIOS, "");

        } /*else if (redesSociales != null){
            mostrarMsj(Constante.ERROR, "No existe la red social");
        } */else {
            try {
                guardarDataBase(Cypher.encrypt(nombre),redesSociales);
            } catch (Exception e) {
                mostrarMsj(Constante.ERROR, "Error para guardar datos");
            }
        }
    }

    public void guardarDataBase(String nombre, RedesSociales redesSociales) {
        Usuario usuario = new Usuario();
        usuario.setIdSocialIcon(redesSociales.getIdSocial());
        usuario.setNameNetworkSocial(redesSociales.getNombreSocial());
        usuario.setUser(nombre);

        new Thread(new Runnable() {
            @Override
            public void run() {
                dataBase.usuariosDao().insert(usuario);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        edtNombre.setText("");
                        mostrarMsj(Constante.DATOS_GUARDADOS, "");
                    }
                });
            }
        }).start();
    }

    private void initList(){
        redesSociales = new ArrayList<>();
        redesSociales.add(new RedesSociales("Facebook", R.drawable.facebook));
        redesSociales.add(new RedesSociales("Instagram", R.drawable.instagram));
        redesSociales.add(new RedesSociales("Messenger", R.drawable.messenger));
        redesSociales.add(new RedesSociales("Snapchat", R.drawable.snapchat));
        redesSociales.add(new RedesSociales("Telegram", R.drawable.telegram));
        redesSociales.add(new RedesSociales("Twitter", R.drawable.twitter));
        redesSociales.add(new RedesSociales("Youtube", R.drawable.youtube));
    }

    public void mostrar (View view) {
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        startActivity(intent);
    }

    private void mostrarMsj(int msj, String mensaje) {
        switch (msj) {
            case Constante.CAMPOS_VACIOS:
                Toast.makeText(MainActivity.this, "Los campos no deben estar vacíos", Toast.LENGTH_SHORT).show();
                break;
            case Constante.DATOS_GUARDADOS:
                Toast.makeText(getApplicationContext(), "Datos guardados", Toast.LENGTH_LONG).show();
                break;
            case Constante.ERROR:
                Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();
                break;
            default:
                Toast.makeText(this, "Error desconocido...", Toast.LENGTH_SHORT).show();
        }
    }
}