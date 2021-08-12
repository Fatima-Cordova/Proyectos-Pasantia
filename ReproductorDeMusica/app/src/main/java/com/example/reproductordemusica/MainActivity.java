package com.example.reproductordemusica;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.reproductordemusica.util.FileManager;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FileManager fileManager = new FileManager(this);
        ArrayList audioFiles;

        if(fileManager.initialize()){
            audioFiles = fileManager.getAllAudio();
        } else {
            Toast.makeText(this, "No existe", Toast.LENGTH_SHORT).show();
        }
    }
}