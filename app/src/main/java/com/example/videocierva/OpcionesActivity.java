package com.example.videocierva;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class OpcionesActivity extends AppCompatActivity {

    Button btninformacion, btncontacto, btnsalir;
    ImageButton miPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones);
        miPerfil = findViewById(R.id.miPerfilbtn);

        miPerfil.setOnClickListener(view -> {
            Intent intent = new Intent(OpcionesActivity.this, PerfilActivity.class);
            startActivity(intent);
        });

        btninformacion = findViewById(R.id.butInforma);

        btninformacion.setOnClickListener(view -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Información Sobre La Aplicación");
            builder.setMessage(getString(R.string.informacion));
            builder.setPositiveButton("OK",null);
            builder.create();
            builder.show();

        });

        btncontacto = findViewById(R.id.butContac);

        btncontacto.setOnClickListener(view -> {

            Intent abrirWeb = new Intent(Intent.ACTION_VIEW);
            abrirWeb.setData(Uri.parse("https://iescierva.net/"));
            startActivity(abrirWeb);

        });

        btnsalir = findViewById(R.id.butExit);

        btnsalir.setOnClickListener(view -> {
            this.finishAffinity();
        });

    }
}