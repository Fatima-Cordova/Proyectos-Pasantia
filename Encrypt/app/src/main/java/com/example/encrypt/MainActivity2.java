package com.example.encrypt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    TextView txtTexto, txtNumero;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        txtTexto = (TextView) findViewById(R.id.txtTexto);
        txtNumero = (TextView) findViewById(R.id.txtNumero);

        sharedPreferences = getSharedPreferences(MainActivity.ENCRYPTDATA, Context.MODE_PRIVATE);
        try {
            txtTexto.setText(Cypher.decrypt(sharedPreferences.getString(MainActivity.TEXTO,  "")));
            txtNumero.setText(Cypher.decrypt(sharedPreferences.getString(MainActivity.NUMERO, "")));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void regresar(View view) {
        finish();
    }
}