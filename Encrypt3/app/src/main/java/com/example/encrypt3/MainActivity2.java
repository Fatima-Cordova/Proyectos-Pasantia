package com.example.encrypt3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

public class MainActivity2 extends AppCompatActivity {

    TextView txtNombre, txtEdad, txtDireccion, txtSexo, txtGson2;
    SharedPreferences sharedPreferences;
    Objeto objeto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        txtNombre = (TextView) findViewById(R.id.txtNombre);
        txtEdad = (TextView) findViewById(R.id.txtEdad);
        txtDireccion = (TextView) findViewById(R.id.txtDireccion);
        txtSexo = (TextView) findViewById(R.id.txtSexo);
        txtGson2 = (TextView) findViewById(R.id.txtGson2);


        sharedPreferences = getSharedPreferences(MainActivity.ENCRYPTDATA, Context.MODE_PRIVATE);
        try {
            String objetoJson = Cypher.decrypt(sharedPreferences.getString(MainActivity.GSON,  ""));
            txtGson2.setText(objetoJson);
            convertirCadenaAObjeto(objetoJson);
            txtNombre.setText(objeto.getName());
            txtEdad.setText(objeto.getAge());
            txtDireccion.setText(objeto.getAddress());
            txtSexo.setText(objeto.getGender());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void convertirCadenaAObjeto(String objetoJson){
        Gson gson = new Gson();
        objeto = gson.fromJson(objetoJson, Objeto.class);
    }

    public void regresar(View view) {
        finish();
    }
}