package com.example.steeve.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.steeve.myapplication.Server.HTTP_JSON_URL;

/**
 * Created by Steeve on 6/22/2018.
 */

public class MesPub extends AppCompatActivity {
    java.util.List<Model> ListData;
    RecyclerView Recycle;
    JsonArrayRequest RequestOfJSonArray;
    RequestQueue Request;
    LinearLayoutManager LinearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_mes_publications);
      //  myDialog = new Dialog(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        ListData = new ArrayList<>();


        Recycle = (RecyclerView) findViewById(R.id.recycle_mesPub);
        Recycle.setHasFixedSize(true);
        LinearLayout = new LinearLayoutManager(MesPub.this);
        Recycle.setLayoutManager(LinearLayout);
//        setSupportActionBar(toolbar);
        JSON_HTTP_CALL();
        // Get the intent, verify the action and get the query


    }

    public void JSON_HTTP_CALL() {
        RequestOfJSonArray = new JsonArrayRequest(HTTP_JSON_URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        ParseJSonResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        Request = Volley.newRequestQueue(this);

        Request.add(RequestOfJSonArray);
    }
    public void ParseJSonResponse(JSONArray array) {
        for (int i = 0; i < array.length(); i++) {
            Model GetDataAdapter2 = new Model();
            JSONObject Json = null;
            try {
                Json = array.getJSONObject(i);
                GetDataAdapter2.setProfil_Image(Json.getString("Path"));
                GetDataAdapter2.setPoste(Json.getString("Poste"));
                GetDataAdapter2.setDepartement(Json.getString("Departement"));
                GetDataAdapter2.setDate_Limite(Json.getString("Date_Limite"));
                GetDataAdapter2.setDate_Post(Json.getString("Date_Post"));
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
            ListData.add(GetDataAdapter2);
        }
        AdapterMesPublications Adapter = new AdapterMesPublications(ListData, this);
        Recycle.setAdapter(Adapter);
    }
}
