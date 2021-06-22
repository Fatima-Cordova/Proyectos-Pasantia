package com.example.ciclodevida;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("CiclosDeVida","OnStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("CiclosDeVida","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("CiclosDeVida","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("CiclosDeVida","onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("CiclosDeVida","onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("CiclosDeVida","onDestroy");
    }
}