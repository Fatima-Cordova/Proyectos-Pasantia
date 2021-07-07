package com.example.parametros;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MensajeActivity extends AppCompatActivity {

    TextView txtMensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensaje);

        txtMensaje = (TextView) findViewById(R.id.txtTexto);

        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null){
            String dato = bundle.getString("dato");
            txtMensaje.setText("" +dato);
        }
    }

    public void regresar(View view){
        finish();
    }
}