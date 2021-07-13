package com.example.encrypt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText edtTexto, edtNumero;
    Button btnEnviar;
    SharedPreferences sharedPreferences;
    public final static String TEXTO = "texto";
    public final static String NUMERO = "numero";
    public final static String NUM = "num";
    public final static String ENCRYPTDATA = "encryptData";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtTexto = (EditText) findViewById(R.id.edtTexto);
        edtNumero = (EditText) findViewById(R.id.edtNumero);;
        btnEnviar = (Button) findViewById(R.id.btnEnviar);

        sharedPreferences = getSharedPreferences(ENCRYPTDATA, Context.MODE_PRIVATE);
        if (! sharedPreferences.getString(TEXTO, "").isEmpty()){
            try {
                edtTexto.setText(Cypher.decrypt(sharedPreferences.getString(TEXTO, "")));
                edtNumero.setText(Cypher.decrypt(sharedPreferences.getString(NUMERO, "")));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void enviar(View view) {

        try {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(TEXTO, Cypher.encrypt(edtTexto.getText().toString()));
            editor.putString(NUMERO, Cypher.encrypt(edtNumero.getText().toString()));
            editor.putInt(NUM, Integer.parseInt(edtNumero.getText().toString()));
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        startActivity(intent);
    }
}