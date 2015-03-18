package com.example.mo.bandit_v1;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by Mo on 24.10.2014.
 */
public class BandData implements Parcelable {

    int bandID;
    String bandName;
    String bandMembers;
    String bandGenre;
    String bandMusik;
    String bandInstruments;

    public BandData(){

    }
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
            bandGenre = jsonObject.getString("bandGenre");

            JSONArray memberArray = jsonObject.getJSONArray("members");
            System.out.println(memberArray.toString());

            String value = "";
            for(int i = 0;i< memberArray.length();i++){
                JSONObject jsonObject1 = memberArray.getJSONObject(i);
                value = value+jsonObject1.getString("profileMember")+", ";
            }
            value = value.substring(0,value.lastIndexOf(",")-1);
            bandMembers = value;
            System.out.println(bandGenre);
            System.out.println(bandMembers);
            //bandMusik = "Highway To Hell";
            //bandInstruments = "Guitar";


        } catch (JSONException e) {
            System.out.println("error");
            e.printStackTrace();
        }

    }

    public void postMusic(String filePath){
        System.out.println("path:"+filePath);
        String title = filePath.substring(filePath.lastIndexOf("/")+1);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("bandID",bandID);
            jsonObject.put("title",title);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ServerCommunication con = new ServerCommunication();
        con.postMusic(jsonObject.toString(),filePath);
    }

    //Create Band
    public  BandData(String bandName, String bandGenre, int bandID){
        this.bandName = bandName;
        this.bandGenre = bandGenre;
        this.bandID = bandID;
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

    protected BandData(Parcel in) {
        bandID = in.readInt();
        bandName = in.readString();
        bandMembers = in.readString();
        bandGenre = in.readString();
        bandMusik = in.readString();
        bandInstruments = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(bandID);
        dest.writeString(bandName);
        dest.writeString(bandMembers);
        dest.writeString(bandGenre);
        dest.writeString(bandMusik);
        dest.writeString(bandInstruments);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<BandData> CREATOR = new Parcelable.Creator<BandData>() {
        @Override
        public BandData createFromParcel(Parcel in) {
            return new BandData(in);
        }

        @Override
        public BandData[] newArray(int size) {
            return new BandData[size];
        }
    };
}
