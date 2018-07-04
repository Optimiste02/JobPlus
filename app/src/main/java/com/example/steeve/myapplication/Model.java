package com.example.steeve.myapplication;

/**
 * Created by Juned on 2/8/2017.
 */
public class Model {
    public String  Poste,Departement,Date_Limite,Date_Post,Profil_Image,Profil, Email,Path_Profil_Temoignage,UsernameTemoignage,temoignage,modifier,supprimer;



    public String getPath_Profil_Temoignage() {
        return Path_Profil_Temoignage;
    }

    public void setPath_Profil_Temoignage(String path_Profil_Temoignage) {
        Path_Profil_Temoignage = path_Profil_Temoignage;
    }

    public String getUsernameTemoignage() {
        return UsernameTemoignage;
    }

    public void setUsernameTemoignage(String usernameTemoignage) {
        UsernameTemoignage = usernameTemoignage;
    }

    public String getTemoignage() {
        return temoignage;
    }

    public void setTemoignage(String temoignage) {
        this.temoignage = temoignage;
    }

    public String getEmail() {
        return Email;

    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getProfil_Image() {
        return Profil_Image;
    }

    public void setProfil_Image(String profil_Image) {
        Profil_Image = profil_Image;
    }



    public String getProfil() {
        return Profil;
    }

    public void setProfil(String profil) {
        Profil = profil;
    }



    public String getPoste() {
        return Poste;
    }

    public void setPoste(String poste) {
        Poste = poste;
    }

    public String getDepartement() {
        return Departement;
    }

    public void setDepartement(String departement) {
        Departement = departement;
    }

    public String getDate_Limite() {
        return Date_Limite;
    }

    public void setDate_Limite(String date_Limite) {
        Date_Limite = date_Limite;
    }

    public String getDate_Post() {
        return Date_Post;
    }

    public void setDate_Post(String date_Post) {
        Date_Post = date_Post;
    }


}