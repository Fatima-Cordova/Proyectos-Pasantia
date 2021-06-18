package com.example.buscador;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recycler;
    EditText edtBuscador;
    Button btnBuscar;

    String redesSociales[];
    List<Redes> filtrarNombre = new ArrayList<>();
    int imagenes[] = {R.drawable.discord,R.drawable.facebook, R.drawable.instagram, R.drawable.linkedin,
            R.drawable.pinterest, R.drawable.snapchat,R.drawable.telegram,R.drawable.twitter,R.drawable.whatsapp,
            R.drawable.youtube};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler = (RecyclerView) findViewById(R.id.recycler);
        edtBuscador = (EditText) findViewById(R.id.edtBuscador);
        btnBuscar = (Button) findViewById(R.id.btnBuscar);

        redesSociales = getResources().getStringArray(R.array.redes_sociales);


        ClaseAdaptadora adaptador = new ClaseAdaptadora(redesSociales, imagenes, this);
        recycler.setAdapter(adaptador);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        edtBuscador.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                filtrarNombre.clear();
                if(s.toString().isEmpty()){
                    recycler.setAdapter(new ClaseAdaptadora(getApplicationContext(),filtrarNombre));
                    adaptador.notifyDataSetChanged();
                }
                else{
                    buscar(s.toString());
                }
            }
        });
    }

    private void buscar(String red){
        recycler.setAdapter(new ClaseAdaptadora(getApplicationContext(),filtrarNombre));
        adaptador.notifyDataSetChanged();
    }
}