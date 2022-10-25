package com.example.videocierva;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText correo, passwd;
    Button buttonLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        correo = findViewById(R.id.emailLog);
        passwd = findViewById(R.id.passwdLog);
        buttonLog = findViewById(R.id.butLog);

        buttonLog.setOnClickListener(view -> {
            if(!validarCampos()) {
                Toast.makeText(view.getContext(), "Correcto", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public boolean validarCampos() {

        boolean error = false;

        String correoLog = correo.getText().toString();
        String passwdLog = passwd.getText().toString();

        if(correoLog.isEmpty()) {
            correo.setError("Inserte el correo");
            error = true;
        }
        if(passwdLog.isEmpty()) {
            passwd.setError("Inserte la contraseña.");
            error = true;
        }
        if(!correoLog.isEmpty() && !correoLog.contains("@")) {
            correo.setError("Inserte un correo válido.");
            error = true;
        }

        return error;

    }

}