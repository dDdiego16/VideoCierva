package com.example.videocierva;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
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

public class PelisActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    SearchView txtBuscar;
    private static final String URL_pelis = "http://192.168.1.83/dam/pelis.php";
    List<Pelicula> peliculaList;
    RecyclerView recyclerView;
    AdapterPelis mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pelis);

        txtBuscar = findViewById(R.id.buscadorPelis);
        recyclerView = findViewById(R.id.recyclerViewPelis);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        
        peliculaList = new ArrayList<>();
        loadpelis();

        txtBuscar.setOnQueryTextListener(this);
        
    }

    private void loadpelis() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_pelis,
                response -> {
                    try {
                        JSONArray array = new JSONArray(response);

                        for (int i = 0; i < array.length(); i++) {

                            JSONObject peli = array.getJSONObject(i);

                            peliculaList.add(new Pelicula(
                                    peli.getString("name_peli"),
                                    peli.getInt("age_peli"),
                                    peli.getString("category_peli"),
                                    peli.getString("image_peli"),
                                    peli.getString("disponible_peli")
                            ));
                        }

                        mAdapter = new AdapterPelis(PelisActivity.this, peliculaList);
                        recyclerView.setAdapter(mAdapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> Toast.makeText(PelisActivity.this, error.toString(), Toast.LENGTH_SHORT).show());
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