package com.example.widgets1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText edtNombre;
    TextView txtMostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtNombre = findViewById(R.id.edtNombre);
        txtMostrar = findViewById(R.id.txtDatos);
    }

    public void ingresar (View view) {
        String nombre = edtNombre.getText().toString();
        txtMostrar.setText("Su nombre es: " +nombre);
    }
}