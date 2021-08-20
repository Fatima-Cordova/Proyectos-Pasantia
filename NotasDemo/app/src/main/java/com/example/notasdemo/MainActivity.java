package com.example.notasdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notasdemo.model.Note;
import com.example.notasdemo.model.User;
import com.example.notasdemo.model.UserWithNote;
import com.example.notasdemo.retrofit.RetrofitInstance;
import com.example.notasdemo.retrofit.RetrofitServices;
import com.example.notasdemo.retrofit.model.response.CSVUploadResponse;
import com.example.notasdemo.util.Cypher;
import com.example.notasdemo.util.ExportCSV;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.Cipher;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText edtEscribirNota;
    private Button btnAgregar, btnBorrar;
    private FloatingActionButton btnExportar;
    private RecyclerView recycler;
    private List<Note> listaNotas = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private RoomBD database;
    private ClaseAdaptadora adaptadora;
    private int idUser = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        idUser = intent.getIntExtra(InicioActivity.ID_USER, 0);

        edtEscribirNota = (EditText) findViewById(R.id.edtEscribirNota);
        btnAgregar = (Button) findViewById(R.id.btnAgregar);
        btnBorrar = (Button) findViewById(R.id.btnEliminar);
        btnExportar = (FloatingActionButton) findViewById(R.id.btnExportar);
        recycler = (RecyclerView) findViewById(R.id.recycler);

        database = RoomBD.getInstance(this);
        popularLista(idUser);

        linearLayoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(linearLayoutManager);
        adaptadora = new ClaseAdaptadora(MainActivity.this, listaNotas);
        recycler.setAdapter(adaptadora);


        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sText = edtEscribirNota.getText().toString().trim();
                if (!sText.equals("")) {
                    Note nota = new Note();
                    try {
                        nota.setText(Cypher.encrypt(sText));
                        nota.setIdUser(idUser);
                        database.notaDao().insert(nota);
                        edtEscribirNota.setText("");
                        listaNotas.clear();
                        popularLista(idUser);
                        adaptadora.notifyDataSetChanged();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            }
        });

        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.notaDao().reset(listaNotas);
                listaNotas.clear();
                popularLista(idUser);
                adaptadora.notifyDataSetChanged();
            }
        });
    }

    public void exportar (View view){
        if(listaNotas.size() > 0) {
            btnExportar.setEnabled(false);
            ExportCSV exportCSV = new ExportCSV(this, listaNotas);
            exportCSV.createCSV();
            showMessage("Archivo CSV exportado");
            subirData(exportCSV.getCsv());
        } else {
            showMessage("No hay notas para exportar");
        }
    }

    private void subirData (File csv) {

        MultipartBody.Part filePart = MultipartBody.Part.createFormData(
                "uploadFile",
                csv.getName(),
                RequestBody.create(MediaType.parse("text/plain" + "; charset=utf-8"), csv)
        );
        RetrofitServices retroServices = RetrofitInstance.getInstance().create(RetrofitServices.class);
        Call<CSVUploadResponse> responseCall = retroServices.uploadAttachment(filePart);

        responseCall.enqueue(new Callback<CSVUploadResponse>() {
            @Override
            public void onResponse(Call<CSVUploadResponse> call, Response<CSVUploadResponse> response) {
                btnExportar.setEnabled(true);
                showMessage("Archivo guardado en el servidor");
            }

            @Override
            public void onFailure(Call<CSVUploadResponse> call, Throwable t) {
                btnExportar.setEnabled(true);
                showMessage("Error al subir el archivo");
            }
        });
    }

    private void showMessage(String msj) {
        Toast.makeText(this, msj, Toast.LENGTH_SHORT).show();
    }

    private void popularLista(int idUser){
        UserWithNote userNote = new UserWithNote();
        if(idUser > 0){
            userNote = database.userDao().getAllNote(idUser);
            listaNotas.addAll(userNote.notas);
        }
    }
}