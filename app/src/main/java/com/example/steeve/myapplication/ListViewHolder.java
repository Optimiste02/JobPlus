package com.example.steeve.myapplication;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by JAccess on 12/17/2017.
 */
public class ListViewHolder extends RecyclerView.ViewHolder {


    public TextView Poste,Departement,Date_Limite,Date_Post, Path, Email,UsernameTemoignage,temoignage;
public ImageView Profil_Image, Profil,Path_Profil_Temoignage,supprimer,modifier;
    public View cardview_item;

    public ListViewHolder(View itemView) {
        super(itemView);
        Poste = (TextView) itemView.findViewById(R.id.Poste);
        temoignage = (TextView) itemView.findViewById(R.id.temoignage);
        UsernameTemoignage = (TextView) itemView.findViewById(R.id.UsernameTemoignage);
        Departement = (TextView) itemView.findViewById(R.id.Departement);
        Date_Limite = (TextView) itemView.findViewById(R.id.Date_Limite);
        Date_Post = (TextView) itemView.findViewById(R.id.Date_Poste);
        Profil_Image = (ImageView) itemView.findViewById(R.id.Profil_Image);
        Path_Profil_Temoignage = (ImageView) itemView.findViewById(R.id.Path_Profil_Temoignage);
        Profil = (ImageView) itemView.findViewById(R.id.Profil);
        Email = (TextView) itemView.findViewById(R.id.editEmail) ;
        cardview_item = (CardView) itemView.findViewById(R.id.cardview_item);
        Path = (TextView) itemView.findViewById(R.id.fichierText);
      // supprimer  = (ImageView) itemView.findViewById(R.id.supprimer);
        //modifier  = (ImageView) itemView.findViewById(R.id.modifier);

    }


}