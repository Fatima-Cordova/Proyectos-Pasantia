package com.example.orientacion1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText edtNombre, edtEdad;
    TextView txtNombre, txtEdad, txtGenero;
    RadioButton rdbMujer, rdbHombre;
    Button btnIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNombre = (EditText) findViewById(R.id.edtNombre);
        edtEdad = (EditText) findViewById(R.id.edtEdad);
        txtNombre = (TextView) findViewById(R.id.txtNombre);
        txtEdad = (TextView) findViewById(R.id.txtEdad);
        txtGenero = (TextView) findViewById(R.id.txtGenero);
        rdbMujer = (RadioButton) findViewById(R.id.rdbMujer);
        rdbHombre = (RadioButton) findViewById(R.id.rdbHombre);
        btnIngresar = (Button) findViewById(R.id.btnIngresar);


        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = edtNombre.getText().toString();
                txtNombre.setText("Su nombre es: " + nombre);

                int edad = Integer.parseInt(edtEdad.getText().toString());
                txtEdad.setText("Su edad es: " + edad);

                if (rdbMujer.isChecked()){
                    String mujer = rdbMujer.getText().toString();
                    txtGenero.setText("Su genero es: " + mujer);
                }
                else if (rdbHombre.isChecked()){
                    String hombre = rdbHombre.getText().toString();
                    txtGenero.setText("Su genero es: " + hombre);
                }
            }
        });
    }
}