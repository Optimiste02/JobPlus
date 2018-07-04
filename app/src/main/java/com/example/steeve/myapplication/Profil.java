package com.example.steeve.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Steeve on 6/4/2018.
 */

public class Profil extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean b = requestWindowFeature( Window.FEATURE_NO_TITLE );

        setContentView(R.layout.profil);
    }
}