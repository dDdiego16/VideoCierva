package com.example.videocierva;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class SeriesActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    SearchView txtBuscar;
    private static final String URL_series = "http://192.168.1.83/dam/series.php";
    List<Serie> seriesList;
    RecyclerView recyclerView;
    AdapterSeries mAdapter;
    ImageButton miPerfil;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series);

        txtBuscar = findViewById(R.id.buscadorSeries);
        recyclerView = findViewById(R.id.recyclerViewSeries);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        miPerfil = findViewById(R.id.miPerfilbtn);

        miPerfil.setOnClickListener(view -> {
            Intent intent = new Intent(SeriesActivity.this, PerfilActivity.class);
            startActivity(intent);
        });

        seriesList = new ArrayList<>();
        loadseries();

        txtBuscar.setOnQueryTextListener(this);

    }

    private void loadseries() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_series,
                response -> {
                    try {
                        JSONArray array = new JSONArray(response);

                        for (int i = 0; i < array.length(); i++) {

                            JSONObject serie = array.getJSONObject(i);

                            seriesList.add(new Serie(
                                    serie.getString("name_serie"),
                                    serie.getInt("age_serie"),
                                    serie.getInt("season_serie"),
                                    serie.getString("category_serie"),
                                    serie.getString("image_serie"),
                                    serie.getString("disponible_serie")
                            ));
                        }

                        mAdapter = new AdapterSeries(SeriesActivity.this, seriesList);
                        recyclerView.setAdapter(mAdapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> Toast.makeText(SeriesActivity.this, error.toString(), Toast.LENGTH_SHORT).show());
        Volley.newRequestQueue(this).add(stringRequest);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        mAdapter.filtrado(s);
        return false;
    }
}