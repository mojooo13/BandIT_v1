package com.example.mo.bandit_v1;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Mo on 24.10.2014.
 */
public class ProfilData implements Parcelable {
    int profilID;
    int[] bandIDs;
    String profilVorname;
    String profilNachname;
    String profilEmail;
    String profilAdress;
    String[] profilInstruments;
    String profilInstrument;
    String profilGenre;
    String passwort;
    String telephonNr;
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

    public ProfilData(){

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

    protected ProfilData(Parcel in) {
        profilID = in.readInt();
        profilVorname = in.readString();
        profilNachname = in.readString();
        profilEmail = in.readString();
        profilAdress = in.readString();
        profilInstrument = in.readString();
        profilGenre = in.readString();
        passwort = in.readString();
        telephonNr = in.readString();
        status = in.readByte() != 0x00;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(profilID);
        dest.writeString(profilVorname);
        dest.writeString(profilNachname);
        dest.writeString(profilEmail);
        dest.writeString(profilAdress);
        dest.writeString(profilInstrument);
        dest.writeString(profilGenre);
        dest.writeString(passwort);
        dest.writeString(telephonNr);
        dest.writeByte((byte) (status ? 0x01 : 0x00));
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ProfilData> CREATOR = new Parcelable.Creator<ProfilData>() {
        @Override
        public ProfilData createFromParcel(Parcel in) {
            return new ProfilData(in);
        }

        @Override
        public ProfilData[] newArray(int size) {
            return new ProfilData[size];
        }
    };
}
