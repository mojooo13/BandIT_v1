package com.example.mo.bandit_v1;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Mo on 24.10.2014.
 */
public class ProfilData implements Parcelable{
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
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("command","getProfileData");
            jsonObject.put("id",profilID);

            ServerCommunication con = new ServerCommunication();
            String jsonString = con.communication(jsonObject.toString());
            jsonString = jsonString.substring(jsonString.indexOf("$")+1);
            jsonObject = new JSONObject(jsonString);

            if(jsonObject.getString("status").equals("true")) {
                this.profilID = profilID;
                profilVorname = jsonObject.getString("firstName");
                profilNachname = jsonObject.getString("secondName");
                profilEmail = jsonObject.getString("email");
                profilAdress = jsonObject.getString("adress");
                profilGenre = jsonObject.getString("genre");
                telephonNr = jsonObject.getString("mobileNumber");
                passwort = jsonObject.getString("password");

                profilInstrument = jsonObject.getString("instrument");
                profilInstruments = profilInstrument.split(",");
                for(int i = 0; i<profilInstruments.length;i++)
                    profilInstruments[i] = profilInstruments[i].substring(profilInstruments[i].indexOf("\"")+1,profilInstruments[i].lastIndexOf("\""));



            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
    public String[] getProfilInstruments() {
        return profilInstruments;
    }
    public String getProfilGenre() {
        return profilGenre;
    }
    public int getId() {
        return profilID;
    }
    public String getTelephonNr() {
        return telephonNr;
    }

    protected ProfilData(Parcel in) {
        profilID = in.readInt();
        profilVorname = in.readString();
        profilNachname = in.readString();
        profilEmail = in.readString();
        profilAdress = in.readString();
        profilInstrument = in.readString();
        int profilInstrumentsLength = in.readInt();
        profilInstruments = new String[profilInstrumentsLength];
        in.readStringArray(profilInstruments);
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
        dest.writeInt(profilInstruments.length);
        dest.writeStringArray(profilInstruments);
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
