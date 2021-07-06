package com.example.notasdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notasdemo.service.UserManager;

public class RegistrarActivity extends AppCompatActivity {

    EditText edtCorreo, edtContra;
    Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        edtCorreo = (EditText) findViewById(R.id.edtCorreo);
        edtContra = (EditText) findViewById(R.id.edtContra);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);

    }

    public void registrar(View view){
        String correo = edtCorreo.getText().toString();
        String contra = edtContra.getText().toString();

        UserManager userManager = new UserManager(this.getApplicationContext());

        new Thread(new Runnable() {
            @Override
            public void run() {
                userManager.ingresar(correo, contra);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),"Usuario registrado", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(RegistrarActivity.this, InicioActivity.class));
                        RegistrarActivity.this.finish();
                    }
                });
            }
        }).start();
    }
}