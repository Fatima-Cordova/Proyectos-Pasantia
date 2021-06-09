package com.example.spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.spinner.adapter.IconoAdaptador;
import com.example.spinner.model.IconoRedes;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<IconoRedes> nombre;
    private IconoAdaptador iconoAdaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initList();

        Spinner spIconos = findViewById(R.id.spIconos);
        iconoAdaptador = new IconoAdaptador(this, nombre);
        spIconos.setAdapter(iconoAdaptador);

        /*spIconos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                IconoRedes iconos = (IconoRedes)parent.getItemAtPosition(position);
                String nombreIconos = iconos.getNombre();
                Toast.makeText(MainActivity.this, nombreIconos+ "La red social que eligió es: ",Toast.LENGTH_LONG);
            }
        });*/
    }

    private void initList(){
        nombre = new ArrayList<>();
        nombre.add(new IconoRedes("Facebook", R.drawable.facebook));
        nombre.add(new IconoRedes("Instagram", R.drawable.instagram));
        nombre.add(new IconoRedes("Twitter", R.drawable.twitter));
        nombre.add(new IconoRedes("Whatsapp", R.drawable.whatsapp));
    }
}