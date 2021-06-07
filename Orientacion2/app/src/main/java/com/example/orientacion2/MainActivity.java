package com.example.orientacion2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText edtNumero1, edtNumero2;
    TextView txtResultado;
    Button btnIngresar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtNumero1 = (EditText) findViewById(R.id.edtNumero1);
        edtNumero2 = (EditText) findViewById(R.id.edtNumero2);
        txtResultado = (TextView) findViewById(R.id.txtResultado);
        btnIngresar = (Button) findViewById(R.id.btnIngresar);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num1 = 0;
                int num2 = 0;
                int res = 0;

                num1 = Integer.parseInt(edtNumero1.getText().toString());
                num2 = Integer.parseInt(edtNumero2.getText().toString());

                res = num1 + num2;
                txtResultado.setText("El resultado de la suma es: " +res);
            }
        });
    }
}
