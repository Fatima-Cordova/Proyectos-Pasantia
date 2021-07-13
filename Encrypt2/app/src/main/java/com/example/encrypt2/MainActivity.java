package com.example.encrypt2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText edtNombre, edtEdad, edtDireccion;
    RadioButton rdbMujer, rdbHombre;
    TextView txtGenero;
    Button btnIngresar;
    SharedPreferences sharedPreferences;
    public final static String TEXTO = "texto";
    public final static String NUMERO = "numero";
    public final static String DIRECCION = "direccion";
    public final static String MUJER = "mujer";
    public final static String HOMBRE = "hombre";
    public final static String GENERO = "genero";
    public final static String ENCRYPTDATA = "encryptData";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNombre = (EditText) findViewById(R.id.edtNombre);
        edtEdad = (EditText) findViewById(R.id.edtEdad);
        edtDireccion = (EditText) findViewById(R.id.edtDireccion);
        txtGenero = (TextView)findViewById(R.id.txtGenero);
        rdbMujer = (RadioButton) findViewById(R.id.rdbMujer);
        rdbHombre = (RadioButton) findViewById(R.id.rdbHombre);
        btnIngresar = (Button) findViewById(R.id.btnEnviar);

        sharedPreferences = getSharedPreferences(ENCRYPTDATA, Context.MODE_PRIVATE);
        if (!sharedPreferences.getString(TEXTO, "").isEmpty()) {
            try {
                edtNombre.setText(Cypher.decrypt(sharedPreferences.getString(TEXTO, "")));
                edtEdad.setText(Cypher.decrypt(sharedPreferences.getString(NUMERO, "")));
                edtDireccion.setText(Cypher.decrypt(sharedPreferences.getString(DIRECCION, "")));
                txtGenero.setText(Cypher.decrypt(sharedPreferences.getString(GENERO, "")));
                rdbMujer.setText(Cypher.decrypt(sharedPreferences.getString(MUJER, "")));
                rdbHombre.setText(Cypher.decrypt(sharedPreferences.getString(HOMBRE, "")));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void enviar(View view){

        if (rdbMujer.isChecked()){
            String mujer = rdbMujer.getText().toString();
            txtGenero.setText(mujer);

        }
        else if (rdbHombre.isChecked()){
            String hombre = rdbHombre.getText().toString();
            txtGenero.setText(hombre);
        }

        try {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(TEXTO, Cypher.encrypt(edtNombre.getText().toString()));
            editor.putString(NUMERO, Cypher.encrypt(edtEdad.getText().toString()));
            editor.putString(DIRECCION, Cypher.encrypt(edtDireccion.getText().toString()));
            editor.putString(GENERO, Cypher.encrypt(txtGenero.getText().toString()));
            editor.putString(MUJER, Cypher.encrypt(rdbMujer.getText().toString()));
            editor.putString(HOMBRE, Cypher.encrypt(rdbHombre.getText().toString()));
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        startActivity(intent);
    }
}