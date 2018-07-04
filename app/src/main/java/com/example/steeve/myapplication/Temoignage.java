package com.example.steeve.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import me.relex.circleindicator.CircleIndicator;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.steeve.myapplication.VolleySingleton;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.steeve.myapplication.R.layout.panel;
import static com.example.steeve.myapplication.Server.HTTP_JSON_URL;
import static com.example.steeve.myapplication.Server.HTTP_JSON_URLL;

public class Temoignage extends AppCompatActivity{

    java.util.List<Model> ListData;
    RecyclerView Recycle;
    JsonArrayRequest RequestOfJSonArray;
    RequestQueue Request;
    LinearLayoutManager LinearLayout;
    NavigationView navigationView;
    private static final int NUM_PAGES =0 ;
    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comments);
        myDialog = new Dialog(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        ListData = new ArrayList<>();


        Recycle = (RecyclerView) findViewById(R.id.Recyclecom);
        Recycle.setHasFixedSize(true);
        LinearLayout = new LinearLayoutManager(Temoignage.this);
        Recycle.setLayoutManager(LinearLayout);
        setSupportActionBar(toolbar);
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

                GetDataAdapter2.setPoste(Json.getString("commentaires"));
                /*
                GetDataAdapter2.setProfil_Image(Json.getString("Path"));
                GetDataAdapter2.setDepartement(Json.getString("Departement"));
                GetDataAdapter2.setDate_Limite(Json.getString("Date_Limite"));
                GetDataAdapter2.setDate_Post(Json.getString("Date_Post"));
            */}
            catch (JSONException e) {
                e.printStackTrace();
            }
            ListData.add(GetDataAdapter2);
        }
        AdapterTemoignage Adapter = new AdapterTemoignage(ListData, this);
        Recycle.setAdapter(Adapter);
    }

}

