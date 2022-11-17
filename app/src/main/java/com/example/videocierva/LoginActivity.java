package com.example.videocierva;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText correo, passwd;
    Button buttonLog;
    String correoLog, passwdLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        correo = findViewById(R.id.emailLog);
        passwd = findViewById(R.id.passwdLog);
        buttonLog = findViewById(R.id.butLog);

        buttonLog.setOnClickListener(view -> {
            correoLog = correo.getText().toString();
            passwdLog = passwd.getText().toString();
            if(!validarCampos(correoLog, passwdLog)) {
                lanzarLogin("http://192.168.1.83/dam/validar_usuario.php");
            }
        });

    }

    public boolean validarCampos(String correoLog, String passwdLog) {

        boolean error = false;

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

    private void lanzarLogin(String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            if (!response.isEmpty()) {
                guardarPreferencias();
                Toast.makeText(LoginActivity.this, "Correcto", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "Usuario o contraseña incorrecta", Toast.LENGTH_SHORT).show();
            }
          }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("usuario", correoLog);
                parametros.put("password", passwdLog);
                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void guardarPreferencias() {
        SharedPreferences preferences = getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("usuario", correoLog);
        editor.putString("password", passwdLog);
        editor.putBoolean("sesion", true);
        editor.commit();
    }

}