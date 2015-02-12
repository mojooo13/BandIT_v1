package com.example.mo.bandit_v1;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Mo on 24.10.2014.
 */
public class BandData {

    int bandID;
    String bandName;
    String bandMembers;
    String bandGenre;
    String bandMusik;
    String bandInstruments;

    public BandData(String bandName, String bandMembers, String bandGenre, String bandMusik, String bandInstruments){
        this.bandName = bandName;
        this.bandMembers = bandMembers;
        this.bandGenre = bandGenre;
        this.bandMusik = bandMusik;
        this.bandInstruments = bandInstruments;
    }
    public  BandData(int id){
        this.bandID = id;

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("command","getBandData");
            jsonObject.put("id",id);

            ServerCommunication con = new ServerCommunication();
            String jsonString = con.communication(jsonObject.toString());
            jsonString = jsonString.substring(jsonString.indexOf("$")+1);
            jsonObject = new JSONObject(jsonString);

            //Daten von id holen
            bandName = jsonObject.getString("bandName");
            bandGenre = jsonObject.getString("BandGenre");
            bandMembers = jsonObject.getString("bandMembers");

            System.out.println(bandMembers);
            //bandMusik = "Highway To Hell";
            //bandInstruments = "Guitar";


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    //Create Band
    public  BandData(int profilID, String bandName, String bandGenre){
        this.bandName = bandName;
        this.bandGenre = bandGenre;

        //Daten vom Profil holen
        bandID = 15;
        bandMembers = "Moritz Hauch";
        bandMusik = "Highway To Hell";
        bandInstruments = "Guitar";
    }

    public String getBandName() {
        return bandName;
    }
    public void setBandName(String bandName) {
        this.bandName = bandName;
    }
    public String getBandMembers() {
        return bandMembers;
    }
    public void setBandMembers(String bandMembers) {
        this.bandMembers = bandMembers;
    }
    public String getBandGenre() {
        return bandGenre;
    }
    public void setBandGenre(String bandGenre) {
        this.bandGenre = bandGenre;
    }
    public String getBandInstruments() {
        return bandInstruments;
    }
    public void setBandInstruments(String bandInstruments) {
        this.bandInstruments = bandInstruments;
    }
    public String getBandMusik() {
        return bandMusik;
    }
    public void setBandMusik(String bandMusik) {
        this.bandMusik = bandMusik;
    }
}
