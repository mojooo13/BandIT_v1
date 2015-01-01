package com.example.mo.bandit_v1;

/**
 * Created by Mo on 24.10.2014.
 */
public class ProfilData {
    int profilID;
    int[] bandIDs;
    String profilVorname;
    String profilNachname;
    String profilEmail;
    String profilAdress;
    String profilInstrument;
    String profilGenre;
    String passwort;
    boolean status;

    //Sign Up
    public ProfilData(String profilVorname, String profilNachname, String profilEmail, String profilAdress, String profilInstrument, String profilGenre){
        this.profilVorname = profilVorname;
        this.profilNachname = profilNachname;
        this.profilEmail = profilEmail;
        this.profilAdress = profilAdress;
        this.profilInstrument = profilInstrument;
        this.profilGenre = profilGenre;
    }
    //Login
    public ProfilData(String profilEmail,String passwort){
        //search -> return status, id
        status = true; //oder false
        bandIDs = new int[]{1,2,3,4};
        profilID = 1; // oder -1 wenn nicht gefunden
    }

    //Profil Fragment
    public ProfilData(int profilID){
        //daten vom Server hohlen
        this.profilID = profilID;
        profilVorname = "Mo";
        profilNachname = "Hauch";
        profilEmail = "moritz.hauch1@gmx.net";
        profilAdress = "Rupert Gugg Str 4";
        profilInstrument = "Guitar";
        profilGenre = "Rock";
        passwort = "123456";
        bandIDs = new int[]{1,2,3,4};
    }

    public String getProfilVorname() {
        return profilVorname;
    }
    public String getProfilNachname() {
        return profilNachname;
    }
    public String getProfilEmail() {
        return profilEmail;
    }
    public String getProfilAdress() {
        return profilAdress;
    }
    public String getProfilInstrument() {
        return profilInstrument;
    }
    public String getProfilGenre() {
        return profilGenre;
    }
    public int getId() {
        return profilID;
    }
}
