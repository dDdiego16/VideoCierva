package com.example.videocierva;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnOpciones, btnPelis, btnSeries;
    ImageButton miPerfil;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOpciones = findViewById(R.id.butOpc);
        btnPelis = findViewById(R.id.butPelis);
        btnSeries = findViewById(R.id.butSeries);
        miPerfil = findViewById(R.id.miPerfilbtn);

        miPerfil.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, PerfilActivity.class);
            startActivity(intent);
        });

        btnOpciones.setOnClickListener((view -> {
            Intent intent = new Intent(MainActivity.this, OpcionesActivity.class);
            startActivity(intent);
        }));

        btnPelis.setOnClickListener((view -> {
            Intent intent = new Intent(MainActivity.this, PelisActivity.class);
            startActivity(intent);
        }));

        btnSeries.setOnClickListener((view -> {
            Intent intent = new Intent(MainActivity.this, SeriesActivity.class);
            startActivity(intent);
        }));

    }
}