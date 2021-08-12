package com.example.notasdemo;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.notasdemo.dao.UserDao;
import com.example.notasdemo.model.User;
import com.example.notasdemo.service.UserManager;

public class InicioActivity extends AppCompatActivity {

    EditText edtUser, edtPass;
    Button btnIngresar;
    int codigo = 0;
    public final static String ID_USER = "ID_USER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        edtUser = (EditText) findViewById(R.id.edtUser);
        edtPass = (EditText) findViewById(R.id.edtPass);
        btnIngresar = (Button) findViewById(R.id.btnIngresar);

        permisos();

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserManager userManager = new UserManager(InicioActivity.this);
                String correo = edtUser.getText().toString();
                String contra = edtPass.getText().toString();

                if (!correo.isEmpty() && !contra.isEmpty()) {
                    User user = userManager.validar(correo);
                    if(user.getId() > 0) {
                        if (contra.equals(user.getPass())) {
                            Intent intent = new Intent(InicioActivity.this, MainActivity.class);
                            intent.putExtra(ID_USER, user.getId());
                            startActivity(intent);
                            InicioActivity.this.finish();
                        } else {
                            Toast.makeText(getApplicationContext(),"Contraseña inválida", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(),"Usuario inválido", Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"Los campos no deben estar vacíos", Toast.LENGTH_LONG).show();
                }
            }
        });
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
                    ActivityCompat.requestPermissions(InicioActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, codigo);
                    ActivityCompat.requestPermissions(InicioActivity.this,
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

    public void registro (View view){
        startActivity(new Intent(InicioActivity.this, RegistrarActivity.class));
    }

    private Boolean validateInput(User user){
        if (user.getEmail().isEmpty() || user.getPass().isEmpty()){
            return false;
        }
        return true;
    }
}