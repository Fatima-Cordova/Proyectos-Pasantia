package com.example.recycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    RecyclerView recycler;

    String redesSociales[];
    int imagenes[] = {R.drawable.discord,R.drawable.facebook, R.drawable.instagram, R.drawable.linkedin,
            R.drawable.pinterest, R.drawable.snapchat,R.drawable.telegram,R.drawable.twitter,R.drawable.whatsapp,
                      R.drawable.youtube};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler = (RecyclerView)findViewById(R.id.recycler);

        redesSociales = getResources().getStringArray(R.array.redes_sociales);

        ClaseAdaptadora adaptador = new ClaseAdaptadora(this, redesSociales, imagenes);
        recycler.setAdapter(adaptador);
        recycler.setLayoutManager(new LinearLayoutManager(this));
    }
}