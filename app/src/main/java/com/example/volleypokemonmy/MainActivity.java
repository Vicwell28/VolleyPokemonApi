package com.example.volleypokemonmy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.volleypokemonmy.Adaptadores.PokemonAdapter;
import com.example.volleypokemonmy.Modelos.Pokemon;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView RV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RV=findViewById(R.id.RV);

        String url = "https://pokeapi.co/api/v2/pokemon?limit=500";

        JsonObjectRequest MiPeticion = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
           Gson gson = new Gson();

            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONArray Pokemonjson = response.getJSONArray("results");
                    final Type TipoListaPokemon = new TypeToken<List<Pokemon>>(){}.getType();
                    final List<Pokemon> PokemonLista = gson.fromJson(Pokemonjson.toString(), TipoListaPokemon);

                    PokemonAdapter PKA = new PokemonAdapter(PokemonLista);

                    RV.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    RV.setHasFixedSize(true);
                    RV.setAdapter(PKA);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        MySingleTon.getInstance(this).addToRequestQue(MiPeticion);
    }
}