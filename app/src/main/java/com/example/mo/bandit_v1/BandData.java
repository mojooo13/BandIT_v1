package com.example.mo.bandit_v1;

/**
 * Created by Mo on 24.10.2014.
 */
public class BandData {
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
