package com.example.notas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SesionActivity extends AppCompatActivity {

    EditText edtUser, edtPass;
    Button btnIngresar;
    int codigo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sesion);
        edtUser = (EditText) findViewById(R.id.edtUsuario);
        edtPass = (EditText) findViewById(R.id.edtClave);
        btnIngresar = (Button) findViewById(R.id.btnIngresar);
        permisos();

    }
    private void permisos() {
        int escribir = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int lectura = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (escribir == PackageManager.PERMISSION_GRANTED && lectura == PackageManager.PERMISSION_GRANTED) {
        }
        if (ActivityCompat.shouldShowRequestPermissionRationale
                (this, Manifest.permission.WRITE_EXTERNAL_STORAGE) ||
                (ActivityCompat.shouldShowRequestPermissionRationale
                        (this, Manifest.permission.READ_EXTERNAL_STORAGE))) {
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Se necesitan permisos de lectura y escritura para continuar...");
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                });

                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        ActivityCompat.requestPermissions(SesionActivity.this,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, codigo);
                        ActivityCompat.requestPermissions(SesionActivity.this,
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, codigo);
                    }
                });
                builder.show();
            }
        }

        else{
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, codigo);
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, codigo);
        }
    }

    public void ingresar(View view) {
        String Usuario = edtUser.getText().toString();
        String Clave = edtPass.getText().toString();
        if (TextUtils.isEmpty(Usuario)){
            Toast.makeText(this, "Ingrese un usuario", Toast.LENGTH_LONG).show();
            edtUser.setError("Ingrese un usuario");
            edtUser.requestFocus();
        }
        else if (TextUtils.isEmpty(Clave)){
            Toast.makeText(this, "Ingrese su clave", Toast.LENGTH_SHORT).show();
            edtPass.setError("Ingrese su clave");
            edtPass.requestFocus();
        }
        if(Usuario.equals("1") && Clave.equals("1"))
        {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Ha digitado un dato erróneo", Toast.LENGTH_SHORT);
        }
    }
}