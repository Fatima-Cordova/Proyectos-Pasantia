package com.example.parametros;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtEscribir;
    Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtEscribir = (EditText) findViewById(R.id.edtEscribir);
        btnEnviar = (Button) findViewById(R.id.btnEnviar);
    }

    public void ingresar(View view){
        String dato = edtEscribir.getText().toString();

        Intent intent = new Intent(MainActivity.this, MensajeActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("dato", edtEscribir.getText().toString());
        intent.putExtras(bundle);
        startActivity(intent);
    }
}