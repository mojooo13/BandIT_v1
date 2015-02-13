package com.example.mo.bandit_v1;

import android.os.Parcel;
import android.os.Parcelable;

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

        //Daten von id holen
        bandName = "ACDC";
        bandMembers = "Mo Hauch";
        bandGenre = "Hard Rock";
        bandMusik = "Highway To Hell";
        bandInstruments = "Guitar";
    }

    //Create Band
    public  BandData(String bandName, String bandGenre){
        this.bandName = bandName;
        this.bandGenre = bandGenre;
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
