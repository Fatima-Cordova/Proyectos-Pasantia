package com.example.guardardatosencriptados;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guardardatosencriptados.model.Usuario;
import com.example.guardardatosencriptados.util.Constante;
import com.example.guardardatosencriptados.util.Cypher;


public class MainActivity extends AppCompatActivity {

    private EditText edtNombre, edtEdad, edtDireccion;
    private Button btnGuardar, btnMostrar;
    private TextView txtNotas;
    private RoomDB dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtNombre = (EditText) findViewById(R.id.edtNombre);
        edtEdad = (EditText) findViewById(R.id.edtEdad);
        edtDireccion = (EditText) findViewById(R.id.edtDireccion);
        txtNotas = (TextView) findViewById(R.id.txtNotas);
        btnMostrar = (Button) findViewById(R.id.btnMostrar);
        btnGuardar = (Button) findViewById(R.id.btnGuardar);

        dataBase = RoomDB.getInstance(this);
    }

    public void guardar(View view) {
        String nombre = edtNombre.getText().toString().trim();
        String edad =  edtEdad.getText().toString().trim();
        String direccion = edtDireccion.getText().toString().trim();


        if (nombre.isEmpty() || edad.isEmpty() || direccion.isEmpty()){
            mostrarMensaje(Constante.CAMPOS_VACIOS, "");
        } else {
            try {
                guardarDataBase(Cypher.encrypt(nombre), Cypher.encrypt(edad),Cypher.encrypt(direccion));
            } catch (Exception e) {
                mostrarMensaje(Constante.ERROR, "Error para guardar datos");
            }
        }
    }

    public void guardarDataBase(String nombre, String edad, String direccion){
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setEdad(edad);
        usuario.setDireccion(direccion);

        new Thread(new Runnable() {
            @Override
            public void run() {
                dataBase.usuariosDao().insert(usuario);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        edtNombre.setText("");
                        edtEdad.setText("");
                        edtDireccion.setText("");
                        mostrarMensaje(Constante.DATOS_GUARDADOS, "");
                    }
                });
            }
        }).start();
    }

    public void mostrar(View view) {
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        startActivity(intent);
    }


    private void mostrarMensaje(int msj, String mensaje) {
        switch (msj) {
            case Constante.CAMPOS_VACIOS:
                Toast.makeText(MainActivity.this, "Los campos no deben estar vacíos",Toast.LENGTH_SHORT).show();
                break;
            case Constante.DATOS_GUARDADOS:
                Toast.makeText(getApplicationContext(),"Datos guardados",Toast.LENGTH_LONG).show();
                break;
            case Constante.ERROR:
                Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_LONG).show();
                break;
            default:
                Toast.makeText(this, "Error desconocido...", Toast.LENGTH_SHORT).show();
        }
    }
}