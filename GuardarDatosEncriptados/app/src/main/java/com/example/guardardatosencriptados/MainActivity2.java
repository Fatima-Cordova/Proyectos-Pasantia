package com.example.guardardatosencriptados;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.guardardatosencriptados.model.Usuario;
import com.example.guardardatosencriptados.util.Constante;
import com.example.guardardatosencriptados.util.Cypher;

import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private TextView txtNotas;
    private RoomDB database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        txtNotas = (TextView) findViewById(R.id.txtListaNotas);

        guardarDataBase();
        database = RoomDB.getInstance(this);
    }

    private void guardarDataBase() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Usuario> lista = database.usuariosDao().getAll();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mostrarNotas(lista);
                    }
                });
            }
        }).start();
    }

    private void mostrarNotas(List<Usuario> listaNotas) {
        String datos = "";
        txtNotas.setText("");
        try {
            for(Usuario usuario: listaNotas) {
                datos+="Id: "+usuario.getId()+", Nombre: " + Cypher.decrypt(usuario.getNombre())+
                        ", Edad: "+Cypher.decrypt(usuario.getEdad())+ ", Direccion: "
                        + Cypher.decrypt(usuario.getDireccion())+ "\n";
            }
            txtNotas.setText(datos);
        } catch (Exception e) {
            mostrarMensaje(Constante.ERROR, "No se pueden leer los datos de la Base de Datos");
        }
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