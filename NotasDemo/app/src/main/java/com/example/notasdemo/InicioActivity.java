package com.example.notasdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notasdemo.dao.UserDao;
import com.example.notasdemo.model.User;
import com.example.notasdemo.service.UserManager;

public class InicioActivity extends AppCompatActivity {

    EditText edtUser, edtPass;
    Button btnIngresar;
    public final static String ID_USER = "ID_USER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        edtUser = (EditText) findViewById(R.id.edtUser);
        edtPass = (EditText) findViewById(R.id.edtPass);
        btnIngresar = (Button) findViewById(R.id.btnIngresar);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserManager userManager = new UserManager(InicioActivity.this);
                String correo = edtUser.getText().toString();
                String contra = edtPass.getText().toString();


                if (!correo.isEmpty() && !contra.isEmpty()){
                    User user = userManager.validar(correo, contra);
                    if(user.getId() > 0){
                        if (contra.equals(user.getPass())) {
                            Intent intent = new Intent(InicioActivity.this, MainActivity.class);
                            intent.putExtra(ID_USER, user.getId());
                            startActivity(intent);
                            InicioActivity.this.finish();
                        }else{
                            Toast.makeText(getApplicationContext(),"Contraseña inválida", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(),"Usuario inválido", Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"Los campos no deben estar vacíos", Toast.LENGTH_LONG).show();
                }
            }
        });
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