package com.example.steeve.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.steeve.myapplication.R;

import static android.view.View.*;

public class MainActivity extends AppCompatActivity {
    private ImageView btn_entrep, btn_jobseeker;
    private TextView connect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_entrep = (ImageView) findViewById(R.id.img_entreprise);
        btn_jobseeker = (ImageView) findViewById(R.id.img_jobseeker);
        connect = (TextView) findViewById(R.id.connection);



        btn_entrep.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        btn_jobseeker.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterJobseekerActivity.class);
                startActivity(intent);
            }
        });

        connect.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });


    }
}
