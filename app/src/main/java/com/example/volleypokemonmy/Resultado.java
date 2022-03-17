package com.example.volleypokemonmy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class Resultado extends AppCompatActivity {

    TextView txtname;
    ImageView igvurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        txtname = findViewById(R.id.textView);
        igvurl = findViewById(R.id.imageView);

        Intent I = getIntent();
        String name = I.getStringExtra("name");
        String url = I.getStringExtra("url");

        txtname.setText(name);

        JsonObjectRequest MiPeticionImagen = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    int id = response.getInt("id");

                    String urlImagen = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/"+id+".png";

                    Picasso.with(getApplicationContext()).load(urlImagen).into(igvurl);


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        MySingleTon.getInstance(this).addToRequestQue(MiPeticionImagen);
    }
}