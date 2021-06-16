package com.example.recyclerclick;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ClaseAdaptadora adaptadora;
    ArrayList<Listas> listaNotas;
    RecyclerView recycler;
    EditText edtNotas;
    Button btnAgregar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAgregar = (Button)findViewById(R.id.btnAgregar);
        edtNotas = (EditText)findViewById(R.id.edtNota);
        recycler = (RecyclerView) findViewById(R.id.recycler);

        recycler.setLayoutManager(new LinearLayoutManager(this));
        listaNotas = new ArrayList ();

        recycler.setHasFixedSize (true);
        adaptadora = new ClaseAdaptadora(listaNotas);
        recycler.setAdapter(adaptadora);

    }



    public void agregar (View view) {
        String nota = edtNotas.getText().toString();
        listaNotas.add(new Listas(nota));
        edtNotas.getText().clear();
        adaptadora.notifyDataSetChanged();


        adaptadora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),
                        "" + listaNotas.get(recycler.getChildAdapterPosition(view)).getAnotacion(), Toast.LENGTH_SHORT).show();
            }
        });
        recycler.setAdapter(adaptadora);
        adaptadora.onLongClick(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Eliminar nota");
                alert.setMessage(listaNotas.get(recycler.getChildAdapterPosition(v)).getAnotacion())
                        .setPositiveButton("Si", new DialogInterface.OnClickListener () {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                               // adaptadora.removeItem(position);
                            }
                        })
                        .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, "Elija otra nota", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        }).show();
                return false;
            }
        });
    }
}





























 //recycler.setAdapter(adaptadora);
       /* adaptadora.onLongClick(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("");
                alert.setMessage(listaNotas.get(recycler.getChildAdapterPosition(v)).getAnotacion())
                        .setPositiveButton("Si", new DialogInterface.OnClickListener () {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }).show();

                return false;
            }
        });*/