package com.example.mo.bandit_v1;

/**
 * Created by Mo on 24.10.2014.
 */
public class ProfilData {
    int id;
    String profilVorname;
    String profilNachname;
    String profilEmail;
    String profilAdress;
    String profilInstrument;
    String profilGenre;

    public ProfilData(String profilVorname, String profilNachname, String profilEmail, String profilAdress, String profilInstrument, String profilGenre){
        this.profilVorname = profilVorname;
        this.profilNachname = profilNachname;
        this.profilEmail = profilEmail;
        this.profilAdress = profilAdress;
        this.profilInstrument = profilInstrument;
        this.profilGenre = profilGenre;
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
        return id;
    }
}
