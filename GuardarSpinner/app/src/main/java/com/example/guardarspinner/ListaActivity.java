package com.example.guardarspinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.guardarspinner.adapter.AdaptadorRecycler;
import com.example.guardarspinner.model.Usuario;
import com.example.guardarspinner.util.Constante;

import java.util.ArrayList;
import java.util.List;

public class ListaActivity extends AppCompatActivity {

    private TextView txtNombreUsuario;
    private ImageView imgRed;
    private ArrayList<Usuario> usuarios;
    private RecyclerView recycler;
    private AdaptadorRecycler adaptadorRecycler;
    private RoomDB dataBase;
    private int idUser = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        recycler = (RecyclerView) findViewById(R.id.recycler);
        txtNombreUsuario = (TextView) findViewById(R.id.txtNombreUsuario);
        imgRed = (ImageView) findViewById(R.id.myImageView);

        dataBase = RoomDB.getInstance(this);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        usuarios = new ArrayList<>();

        extraerDatosDB();

    }

    private void extraerDatosDB() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                usuarios = (ArrayList<Usuario>) dataBase.usuariosDao().getAll();
                int id = 0;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        datosAMostrar();
                    }
                });
            }
        }).start();
    }

    private void datosAMostrar() {
        adaptadorRecycler = new AdaptadorRecycler(ListaActivity.this, usuarios);
    //    adaptadorRecycler.notifyDataSetChanged();
    }

    private void mostrarMensaje(int type, String mensaje) {
        switch (type) {
            case Constante.ERROR:
                Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(this, "Error desconocido...", Toast.LENGTH_SHORT).show();
        }
    }
}





















  /*  public void itemClick(Usuario usuario) {
        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra(Constante.ID_USER, usuario.getUser());
        startActivity(intent);
    }
*/