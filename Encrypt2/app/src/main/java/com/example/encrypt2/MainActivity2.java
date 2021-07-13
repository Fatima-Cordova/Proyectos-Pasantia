package com.example.encrypt2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    TextView txtNombre, txtEdad, txtDireccion, txtSexo;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        txtNombre = (TextView) findViewById(R.id.txtNombre);
        txtEdad = (TextView) findViewById(R.id.txtEdad);
        txtDireccion = (TextView) findViewById(R.id.txtDireccion);
        txtSexo = (TextView) findViewById(R.id.txtSexo);

        sharedPreferences = getSharedPreferences(MainActivity.ENCRYPTDATA, Context.MODE_PRIVATE);
        try {
            txtNombre.setText(Cypher.decrypt(sharedPreferences.getString(MainActivity.TEXTO,  "")));
            txtEdad.setText(Cypher.decrypt(sharedPreferences.getString(MainActivity.NUMERO, "")));
            txtDireccion.setText(Cypher.decrypt(sharedPreferences.getString(MainActivity.DIRECCION,  "")));
            txtSexo.setText(Cypher.decrypt(sharedPreferences.getString(MainActivity.GENERO,  "")));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void regresar(View view) {
        finish();
    }
}