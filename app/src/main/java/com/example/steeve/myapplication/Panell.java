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

public class Panell extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener {

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
        setContentView(R.layout.panel);
        myDialog = new Dialog(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        ListData = new ArrayList<>();


        Recycle = (RecyclerView) findViewById(R.id.recycleView);
        Recycle.setHasFixedSize(true);
        LinearLayout = new LinearLayoutManager(Panell.this);
        Recycle.setLayoutManager(LinearLayout);
        setSupportActionBar(toolbar);
        JSON_HTTP_CALL();
        // Get the intent, verify the action and get the query




        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Post.class);
                startActivity(intent);
            }
        });

        final ViewPager viewPager =findViewById(R.id.viewPager);


        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);










        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

         navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
       // HeaderElements();
        /**
         * Setup click events on the Navigation View Items.
         */


    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        MenuItemCompat.setOnActionExpandListener(searchItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                // Method called when the search view is expand.
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                // Method called when the search view is closed.
                return true;
            }
        });
        return true;
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
        AdapterList Adapter = new AdapterList(ListData, this);
        Recycle.setAdapter(Adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Model_CV) {
            // Handle the camera action
            Intent i = new Intent(Panell.this, Profil.class);
            startActivity(i);
        } else if (id == R.id.Lettre_Motivation) {
            Intent i = new Intent(Panell.this, Profil.class);
            startActivity(i);
        }else if (id == R.id.Mes_Publication) {
            Intent i = new Intent(Panell.this, MesPub.class);
            startActivity(i);
        }  else if (id == R.id.Temoignages) {
            Intent i = new Intent(Panell.this, Temoignage.class);
            startActivity(i);
        } else if (id == R.id.Aide) {

        } else if (id == R.id.nav_share) {

            try {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, "JOB+");
                String sAux = "\nLet me recommend you this application\n\n";
                sAux = sAux + "https://play.google.com/store/apps/details?id=the.package.id \n\n";
                i.putExtra(Intent.EXTRA_TEXT, sAux);
                startActivity(Intent.createChooser(i, "choose one"));
            } catch(Exception e) {
                //e.toString();
            }
        } else if (id == R.id.A_Propos) {

            apropos();


        }else if (id == R.id.Deconnection) {

            Intent i = new Intent(Panell.this, LoginActivity.class);
            startActivity(i);
            finish();


        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void HeaderElements() {

        View headerView = navigationView.getHeaderView(0);
        TextView NameMembre = (TextView) headerView.findViewById(R.id.Name);
        TextView EmailMembre = (TextView) headerView.findViewById(R.id.EmailMembre);
        ImageView ProfileImage = (ImageView) headerView.findViewById(R.id.Profil);

        Bundle bundle=getIntent().getExtras();
        String Name=(bundle.getString("Name"));
        String Mail=(bundle.getString("Mail"));
        final String url=(bundle.getString("Path_Profil"));

        NameMembre.setText(Name);
        EmailMembre.setText(Mail);

        Glide.with(this.getApplicationContext()).load(url)
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ProfileImage);


    }






    private void apropos(){
        LayoutInflater inflater = LayoutInflater.from(Panell.this);
        View subView = inflater.inflate(R.layout.apropos, null);


        AlertDialog.Builder builder = new AlertDialog.Builder(Panell.this);
        builder.setTitle("A Propos de Job+");
        builder.setView(subView);
        builder.create();
        builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();
    }
}

