package com.example.steeve.myapplication;

/**
 * Created by Steeve on 6/28/2018.
 */

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import static android.view.View.*;

public class Modelcv extends AppCompatActivity {
    private ImageView Cvparfait, modelecv, moncv;
   private Button retour;
   private TextView titre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profil);

       //Cvparfait = (ImageView)findViewById( R.id.cvmaker );
        //modelecv = (ImageView)findViewById( R.id.moncvparfait );
       /* moncv = (ImageView)findViewById( R.id.modele );
        retour= (Button) findViewById( R.id.retour )*/;
        //titre = (TextView) findViewById( R.id.titre );

   /*     Cvparfait.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String url = "https://www.cvmaker.fr";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData( Uri.parse(url));
                startActivity(i);

            }
        });

        modelecv.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String url = "https://www.moncvparfait.fr";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData( Uri.parse(url));
                startActivity(i);

            }
        });*/

        Cvparfait.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String url = "http://www.modeles-de-cv.com";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData( Uri.parse(url));
                startActivity(i);

            }
        });

     /*   retour.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Panell.class);
                startActivity(intent);
            }
        });*/



    }
}