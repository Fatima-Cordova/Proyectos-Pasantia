package com.example.encrypt3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    EditText edtNombre, edtEdad, edtDireccion;
    RadioButton rdbMujer, rdbHombre;
    TextView txtGenero, txtGson;
    Button btnIngresar;
    SharedPreferences sharedPreferences;
    public final static String NOMBRE = "nombre";
    public final static String EDAD = "edad";
    public final static String DIRECCION = "direccion";
    public final static String GENERO = "genero";
    public final static String GSON = "gson";
    public final static String ENCRYPTDATA = "encryptData";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNombre = (EditText) findViewById(R.id.edtNombre);
        edtEdad = (EditText) findViewById(R.id.edtEdad);
        edtDireccion = (EditText) findViewById(R.id.edtDireccion);
        txtGenero = (TextView)findViewById(R.id.txtGenero);
        txtGson = (TextView)findViewById(R.id.txtGson);
        rdbMujer = (RadioButton) findViewById(R.id.rdbMujer);
        rdbHombre = (RadioButton) findViewById(R.id.rdbHombre);
        btnIngresar = (Button) findViewById(R.id.btnEnviar);

        sharedPreferences = getSharedPreferences(ENCRYPTDATA, Context.MODE_PRIVATE);
        if (!sharedPreferences.getString(NOMBRE, "").isEmpty()) {
            try {
                edtNombre.setText(Cypher.decrypt(sharedPreferences.getString(NOMBRE, "")));
                edtEdad.setText(Cypher.decrypt(sharedPreferences.getString(EDAD, "")));
                edtDireccion.setText(Cypher.decrypt(sharedPreferences.getString(DIRECCION, "")));
                txtGenero.setText(Cypher.decrypt(sharedPreferences.getString(GENERO, "")));
                txtGson.setText(Cypher.decrypt(sharedPreferences.getString(GSON, "")));
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

        Objeto objeto = new Objeto();
        Gson gson = new Gson();
        objeto.setName(edtNombre.getText().toString());
        objeto.setAge(edtEdad.getText().toString());
        objeto.setAddress(edtDireccion.getText().toString());
        objeto.setGender(txtGenero.getText().toString());
        String objetoEnCadena = gson.toJson(objeto);
        txtGson.setText(objetoEnCadena);

        try {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(GSON, Cypher.encrypt(objetoEnCadena));
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
    }
}
