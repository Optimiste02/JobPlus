package com.example.steeve.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JAccess on 4/13/2018.
 */

public class AdapterTemoignage extends  RecyclerView.Adapter<ListViewHolder>{
    //Les Variables.............
    Context context;
    List<Model> AllData;
    int IdLogged= 3;





    // Constructeur ..........................
    public AdapterTemoignage(List<Model> GetAllData, Context context){
        super();
        this.AllData = new ArrayList<>();
        this.AllData = GetAllData;
        this.context = context;
    }


    // ListViewholder de la Cardview.................
    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_comments, parent, false);
        ListViewHolder listViewHolder = new ListViewHolder(view);
        return listViewHolder;
    }
    // onBindViewHolder de la Cardview.................
    @Override
    public void onBindViewHolder(final ListViewHolder listViewHolder, final int position) {
        // imageLoader = Singleton.getInstance(context).getImageLoader();

        //Attachement des donnees du serveur..............
        //listViewHolder.Image_Party.setImageUrl(AllData.get(position).getPath_Image(), imageLoader);
        //    listViewHolder.Path_Profil.setImageUrl(AllData.get(position).getPath_profil(), imageLoader);

/*

        Glide.with(context).load(AllData.get(position).getProfil_Image())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(listViewHolder.Profil_Image);
*/

        listViewHolder.temoignage.setText(AllData.get(position).getPoste());/*
        listViewHolder.Departement.setText(AllData.get(position).getDepartement());
        listViewHolder.Date_Limite.setText(AllData.get(position).getDate_Limite());
        listViewHolder.Date_Post.setText(AllData.get(position).getDate_Post());
//        listViewHolder.Email.setText(AllData.get(position).getDate_Post());
        // listViewHolder.Path.setText(AllData.get(position).getPath());
*/


        // Action Click  sur chaque cardview.............

/*
        listViewHolder.cardview_item.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                popupcomments();
            }


        });
*/




      /*  listViewHolder.Description_Party.setText(AllData.get(position).getDescription());
        listViewHolder.Username.setText(AllData.get(position).getUsername());
        listViewHolder.Date_Publication.setText(AllData.get(position).getDate_Publication());

*/  }

    private void popupcomments(){
        //ImageView image = new ImageView(this);

        LayoutInflater inflater = LayoutInflater.from(context);
        View subView = inflater.inflate(R.layout.popup, null);


        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        ImageView subImageViewShare = (ImageView)subView.findViewById(R.id.partager);
        ImageView subImageViewPostuler = (ImageView)subView.findViewById(R.id.post);
        builder.setTitle("Faites votre choix !");
        builder.setView(subView);
        builder.create();
        //subImageView = (ImageView) subView.findViewById(R.id.partager);
        builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.show();
        subImageViewShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareIt();
            }
        });

        subImageViewPostuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postuler();
            }
        });


    }

    private void postuler() {
        //sharingIntent.setType("text/plain");
        Intent emailIntent = new Intent(android.content.Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto","abc@gmail.com", null));
        //sharingIntent.setType("url/plain");
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");

        context.startActivity(Intent.createChooser(emailIntent, "Share via"));
    }

    private void shareIt() {
        //sharingIntent.setType("text/plain");
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        //sharingIntent.setType("url/plain");
        sharingIntent.setType("text/plain");
        sharingIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Title Of The Post");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, "http://www.codeofaninja.com");

        context.startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }


    // Methode Count de la Card..........
    @Override
    public int getItemCount() { return AllData.size();
    }

}