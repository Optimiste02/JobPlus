package com.example.steeve.myapplication;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
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

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Blob;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Post extends AppCompatActivity implements
        View.OnClickListener {

    Button btnDatePicker, btnChooser, btnAnnuler, btnPublier;
    EditText txtDate, txtDepartement;
    EditText txtPoste, txtEmail;
    TextView txtFichier;
    Spinner Departement;
Dialog dialog;
    private ImageView Profil;
    private Bitmap bitmap;
    private Blob blob;
    public static final String PatternMail = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b";
    String Poste,Date,Departements, Email,PathHolder;
    int PICKFILE_RESULT_CODE = 1;
    public static final TextView mail = null;
    private int year, month, day;
     int mYear, mMonth, mDay, mHour, mMinute,NewId_Entreprise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post);
        btnAnnuler = (Button) findViewById(R.id.btnCanel);
        btnPublier = (Button) findViewById(R.id.btnPost);

        btnDatePicker = (Button) findViewById(R.id.btnDate);
        btnChooser = (Button) findViewById(R.id.btnChoose);
        txtDate = (EditText) findViewById(R.id.edtDate);
      //  RetrieveDatas();
        txtFichier = (TextView) findViewById(R.id.fichierText);
        // txtTime=(EditText)findViewById(R.id.fichierText);
        txtPoste= (EditText) findViewById(R.id.editTitrePost);
        txtEmail= (EditText) findViewById(R.id.editEmail);
        txtDepartement= (EditText) findViewById(R.id.edtDepart);
        btnDatePicker.setOnClickListener(this);
        btnChooser.setOnClickListener(this);
        btnAnnuler.setOnClickListener(this);
        btnPublier.setOnClickListener(this);


    }



    @Override
    public void onClick(View v) {

        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        } else if (v == btnChooser) {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("*/*");
            intent = Intent.createChooser(intent, "Choose a file");
            startActivityForResult(intent, 7);



            //TextView txtFichier = new TextView(this, intent);

        } else if (v == btnAnnuler) {
            Intent intent = new Intent(getApplicationContext(), Post.class);
            startActivity(intent);
        } else
            if(v == btnPublier) {

                Poste = txtPoste.getText().toString();
                Departements = txtDepartement.getText().toString();
                Date = txtDate.getText().toString();
                Email = txtEmail.getText().toString();

                PathHolder = txtFichier.getText().toString();

                Pattern p = Pattern.compile(PatternMail);
                Matcher m = p.matcher(Email);
                if (Poste.equalsIgnoreCase("")) {
                    txtPoste.setError("Identifiez le poste vacant !");
                }
                else if (Date.equalsIgnoreCase("")) {
                    txtDate.setError("Entrez la date limite !");
                }
                else if (Date.equalsIgnoreCase("")) {
                    txtDepartement.setError("Entrez le departement !");
                }
                else if (Date.equalsIgnoreCase("")) {
                    txtEmail.setError("Email de recrutement !");
                }
                else  if(Email.equalsIgnoreCase("")||!m.find()) {
                    //Toast.makeText(RegisterJobseekerActivity.this, " Format Mail Invalide", Toast.LENGTH_SHORT).show();
                    txtEmail.setError("Format Mail Invalide");
                }
                else {

                    RegisterPost();
                    finish();
                    Intent intent = new Intent(getApplicationContext(), Panell.class);
                    startActivity(intent);
                }

        }
    }





        // add button listener

    // Methode Bitmap de la photo



    //Fin




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub

        switch(requestCode){

            case 7:

                if(resultCode==RESULT_OK){

                     PathHolder = data.getData().getPath();
                     txtFichier.setText(PathHolder);

                    Toast.makeText(Post.this, PathHolder , Toast.LENGTH_LONG).show();

                }

                break;

        }
    }








    private void RegisterPost() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.regPost_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    String code = jsonObject.getString("code");
                    String message = jsonObject.getString("message");
                    Toast.makeText(getApplicationContext(),message, Toast.LENGTH_SHORT).show();
                    displayAlert(code);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
              // param.put("Id_Entreprise", Strnom);
                param.put("Poste", Poste);
                param.put("Departement", Departements);
                param.put("Date_Limite", Date);
                param.put("Email_Recrutement", Email);
              param.put("Path", PathHolder);
                // param.put("Id_Entreprise", String.valueOf(NewId_Entreprise));


                return param;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(Post.this);
        //Adding request to the queue
        requestQueue.add(stringRequest);
    }
    public void displayAlert(final String code) {
        if (code.equals("Req_Success")) {
            // Toast.makeText(Register.this, "SuccessFull", Toast.LENGTH_LONG).show();
          //  Intent intent = new Intent(Post.this, Panel.class);

          //  startActivity(intent);
             finish();
        } else if (code.equals("ReqFound")) {
           // Toast.makeText(Post.this, "Erreur", Toast.LENGTH_LONG).show();
        }
    }

}










    //Button buttonPick = (Button)findViewById(R.id.buttonpick);
    //textFile = (TextView)findViewById(R.id.textfile);



