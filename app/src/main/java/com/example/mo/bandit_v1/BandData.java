package com.example.mo.bandit_v1;

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

        //Daten von id holen
        bandName = "ACDC";
        bandMembers = "Mo Hauch";
        bandGenre = "Hard Rock";
        bandMusik = "Highway To Hell";
        bandInstruments = "Guitar";
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
