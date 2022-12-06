package com.example.videocierva;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PerfilActivity extends AppCompatActivity {

    Button btnCerrarSesion;
    TextView nomTxt, mailTxt, contraTxt;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        SharedPreferences preferences1 = getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);

        nomTxt = findViewById(R.id.nombreTxt);
        nomTxt.setText(preferences1.getString("name", ""));

        mailTxt = findViewById(R.id.correoTxt);
        mailTxt.setText(preferences1.getString("usuario", ""));

        contraTxt = findViewById(R.id.passwordTxt);
        contraTxt.setText(preferences1.getString("password", ""));


        btnCerrarSesion = findViewById(R.id.butCerrarSesion);

        btnCerrarSesion.setOnClickListener(view -> {
            SharedPreferences preferences = getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
            preferences.edit().clear().commit();

            Toast.makeText(PerfilActivity.this, "Sesión Cerrada", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(PerfilActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

    }
}