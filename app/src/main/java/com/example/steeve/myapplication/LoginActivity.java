package com.example.steeve.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    Button BtnLogin;
    EditText EdtUsername, EdtPassword;
    TextView no_Account;
    CheckBox entreprise,utilisateur;
    String StrUsername, StrPassword,StrMail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        no_Account=(TextView) findViewById(R.id.noAcount);
        BtnLogin = (Button) findViewById(R.id.login);
        EdtUsername = (EditText) findViewById(R.id.userEmail);
        EdtPassword = (EditText) findViewById(R.id.userPassword);
        entreprise = (CheckBox) findViewById(R.id.check_entreprise);
        utilisateur = (CheckBox) findViewById(R.id.check_jobseeker);
        no_Account.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StrUsername = EdtUsername.getText().toString();
                StrMail = EdtUsername.getText().toString();
                StrPassword = EdtPassword.getText().toString();
                if (StrPassword.equals("") || StrPassword.equals("")) {
                    Toast.makeText(LoginActivity.this, "Remplissez les champs vides", Toast.LENGTH_SHORT).show();
                } else {

                    if (utilisateur.isChecked()){

                        LoginMember();
                    //  finish();
                }
                else if (entreprise.isChecked()){
                        LoginEntreprise();
                    }
                    else{
                        Toast.makeText(LoginActivity.this, "Veuillez choisir le type de votre compte", Toast.LENGTH_SHORT).show();
                    }
                }
            }


        });
    }

    private void LoginEntreprise() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.logEnt_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            String code = jsonObject.getString("code");

                            if (code.equals("login failed")) {
                                displayAlert(jsonObject.getString("msg"));
                            }
                            else {
                                //Toast.makeText(LoginActivity.this, "Vous etes connectez a votre compte entreprise", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, Panel.class);

                                startActivity(intent);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, "Erreur", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Email", StrMail);
                params.put("Password", StrPassword);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
        //Adding request to the queue
        requestQueue.add(stringRequest);
    }
    private void LoginMember() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.log_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            String code = jsonObject.getString("code");

                            if (code.equals("login failed")) {
                                displayAlert(jsonObject.getString("msg"));
                            }
                            else {
                               // Toast.makeText(LoginActivity.this, "Vous etes connectez a votre compte chercheur d'emploi", Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(LoginActivity.this, Panel.class);

                                    startActivity(intent);



                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, "Erreur", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Username", StrUsername);
                params.put("Mail", StrMail);
                params.put("Password", StrPassword);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
        //Adding request to the queue
        requestQueue.add(stringRequest);
    }
    public void displayAlert(String message) {
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
    }

}