package com.example.mo.bandit_v1;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Mo on 13.01.2015.
 */
public class Data implements Parcelable {//Klassen ins Intent
    ProfilData profilData = new ProfilData();
    //BandData[] bandData;
    BandData bandData;
    EventData eventData;
    NotificationData notificationData;
    String line1;
    String line2;
    String line3;

    public Data(String line1, String line2, String line3){
        this.line1 = line1;
        this.line2 = line2;
        this.line3 = line3;
        try {
            JSONObject jsonObject1 = new JSONObject(line1);
            JSONObject jsonObject2 = new JSONObject(line2);
            JSONObject jsonObject3 = new JSONObject(line3);

            profilData.profilVorname = jsonObject1.getString("firstName");
            profilData.profilNachname = jsonObject1.getString("secondName");
            profilData.telephonNr = jsonObject1.getString("mobileNumber");
            profilData.profilEmail = jsonObject1.getString("email");
            profilData.profilGenre = jsonObject1.getString("genre");
            profilData.profilAdress = jsonObject1.getString("adress");

            JSONArray jsonArr= jsonObject1.getJSONArray("instrument");
            profilData.profilInstruments = new String[jsonArr.length()];

            for(int i=0;i< profilData.profilInstruments.length;i++){
                profilData.profilInstruments[i]=jsonArr.get(i).toString();
            }
            for(int i = 0; i < profilData.profilInstruments.length; i++){
                System.out.println( profilData.profilInstruments[i]);
            }
            /*
            JSONArray jsonArray = jsonObject2.getJSONArray("bands");
            bandData = new BandData[jsonArray.length()];

            for(int i=0;i< bandData.length;i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                bandData[i].bandName=jsonObject.getString("bandName");
                bandData[i].bandGenre=jsonObject.getString("genre");
                System.out.println(bandData[i].bandGenre);
            }
            */


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected Data(Parcel in) {
        profilData = in.readParcelable(ProfilData.class.getClassLoader());
        bandData = (BandData) in.readValue(BandData.class.getClassLoader());
        eventData = (EventData) in.readValue(EventData.class.getClassLoader());
        notificationData = (NotificationData) in.readValue(NotificationData.class.getClassLoader());
        line1 = in.readString();
        line2 = in.readString();
        line3 = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //dest.writeValue(profilData);
        dest.writeParcelable(profilData, flags);
        dest.writeValue(bandData);
        dest.writeValue(eventData);
        dest.writeValue(notificationData);
        dest.writeString(line1);
        dest.writeString(line2);
        dest.writeString(line3);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Data> CREATOR = new Parcelable.Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };
}