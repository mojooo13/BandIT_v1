package com.example.mo.bandit_v1;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Mo on 13.01.2015.
 */
public class Data implements Parcelable {//Klassen ins Intent
    ProfilData profilData = new ProfilData();
    ArrayList<BandData> bandDatas;
    ArrayList<EventData> eventDatas;
    NotificationData notificationData = new NotificationData(new ArrayList<BandRequest>(),new ArrayList<EventReuqest>()); //gegen Nullpoint
    String line1;
    String line2;
    String line3;
    String line4;

    public Data(String line1, String line2, String line3, String line4){
        this.line1 = line1;
        this.line2 = line2;
        this.line3 = line3;
        this.line4 = line4;
        try {
            JSONObject jsonObject1 = new JSONObject(line1);
            JSONObject jsonObject2 = new JSONObject(line2);
            JSONObject jsonObject3 = new JSONObject(line3);
            JSONObject jsonObject4 = new JSONObject(line4);

            profilData.profilVorname = jsonObject1.getString("firstName");
            profilData.profilNachname = jsonObject1.getString("secondName");
            profilData.telephonNr = jsonObject1.getString("mobileNumber");
            profilData.profilEmail = jsonObject1.getString("email");
            profilData.profilGenre = jsonObject1.getString("genre");
            profilData.profilAdress = jsonObject1.getString("adress");
            profilData.profilID = jsonObject1.getInt("profileID");
            profilData.passwort = jsonObject1.getString("password");

            JSONArray jsonArr= jsonObject1.getJSONArray("instrument");
            profilData.profilInstruments = new String[jsonArr.length()];

            for(int i=0;i< profilData.profilInstruments.length;i++){
                profilData.profilInstruments[i]=jsonArr.get(i).toString();
            }
            for(int i = 0; i < profilData.profilInstruments.length; i++){
                System.out.println( profilData.profilInstruments[i]);
            }

            JSONArray jsonArray = jsonObject2.getJSONArray("bands");
            bandDatas = new ArrayList<BandData>();
            for(int i=0;i< jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                BandData bandData = new BandData(jsonObject.getString("bandName"),jsonObject.getString("genre"),jsonObject.getInt("id"));
                bandDatas.add(bandData);
            }

            try {
                JSONArray jsonArray2 = jsonObject3.getJSONArray("events");
                eventDatas = new ArrayList<EventData>();
                for (int i = 0; i < jsonArray2.length(); i++) {
                    JSONObject jsonObject = jsonArray2.getJSONObject(i);
                    EventData eventData = new EventData(jsonObject.getInt("eventID"), jsonObject.getString("eventName"), jsonObject.getString("eventGenre"));
                    eventDatas.add(eventData);
                }
            }catch (Exception e){
                e.printStackTrace();
            }


/*
            JSONArray jsonArray3 = jsonObject4.getJSONArray("bandRequest");
            JSONArray jsonArray4 = jsonObject4.getJSONArray("eventRequest");
            notificationData = new NotificationData();
            for(int i=0;i< jsonArray3.length();i++){
                JSONObject jsonObjectBand = jsonArray3.getJSONObject(i);
                JSONObject jsonObjectEvent = jsonArray4.getJSONObject(i);
                BandRequest bandRequest = new BandRequest(jsonObjectBand.getInt("requestId"),jsonObjectBand.getInt("bandId"),jsonObjectBand.getString("bandGenre"),jsonObjectBand.getString("text"),jsonObjectBand.getString("bandName"));
                EventReuqest eventReuqest = new EventReuqest(jsonObjectEvent.getInt("requestId"),jsonObjectEvent.getInt("requestSenderId"),jsonObjectEvent.getInt("eventID"),jsonObjectEvent.getString("eventName"),jsonObjectEvent.getString("eventGenre"),jsonObjectEvent.getString("eventLocation"),jsonObjectEvent.getString("eventDate"),jsonObjectEvent.getString("eventTime"),jsonObjectEvent.getString("requestFirstName"),jsonObjectEvent.getString("requestSecondName"),jsonObjectEvent.getString("text"),jsonObjectEvent.getString("bandname"));
                notificationData.bandRequests.add(bandRequest);
                notificationData.eventReuqests.add(eventReuqest);
            }
            */
            //BandRequest
            try {
                JSONArray jsonArray3 = jsonObject4.getJSONArray("bandRequest");
                notificationData = new NotificationData();
                for (int i = 0; i < jsonArray3.length(); i++) {
                    JSONObject jsonObjectBand = jsonArray3.getJSONObject(i);
                    BandRequest bandRequest = new BandRequest(jsonObjectBand.getInt("requestId"), jsonObjectBand.getInt("bandId"), jsonObjectBand.getString("bandGenre"), jsonObjectBand.getString("text"), jsonObjectBand.getString("bandName"));
                    notificationData.bandRequests.add(bandRequest);
                }
            }catch (Exception e){
                e.printStackTrace();
            }

            //EventRequest
            try {
                JSONArray jsonArray4 = jsonObject4.getJSONArray("eventRequest");
                for (int i = 0; i < jsonArray4.length(); i++) {
                    JSONObject jsonObjectEvent = jsonArray4.getJSONObject(i);
                    EventReuqest eventReuqest = new EventReuqest(jsonObjectEvent.getInt("requestId"), jsonObjectEvent.getInt("requestSenderId"), jsonObjectEvent.getInt("eventID"), jsonObjectEvent.getString("eventName"), jsonObjectEvent.getString("eventGenre"), jsonObjectEvent.getString("eventLocation"), jsonObjectEvent.getString("eventDate"), jsonObjectEvent.getString("eventTime"), jsonObjectEvent.getString("requestFirstName"), jsonObjectEvent.getString("requestSecondName"), jsonObjectEvent.getString("text"), jsonObjectEvent.getString("bandname"));
                    System.out.println(eventReuqest);
                    notificationData.eventReuqests.add(eventReuqest);
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected Data(Parcel in) {
        profilData = in.readParcelable(ProfilData.class.getClassLoader());
        //bandData = (BandData) in.readValue(BandData.class.getClassLoader());
        //eventData = (EventData) in.readValue(EventData.class.getClassLoader());
        //notificationData = (NotificationData) in.readValue(NotificationData.class.getClassLoader());
        line1 = in.readString();
        line2 = in.readString();
        line3 = in.readString();
        bandDatas = new ArrayList<BandData>();
        in.readTypedList(bandDatas,BandData.CREATOR);
        eventDatas = new ArrayList<EventData>();
        in.readTypedList(eventDatas,EventData.CREATOR);
        notificationData = in.readParcelable(NotificationData.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //dest.writeValue(profilData);
        dest.writeParcelable(profilData, flags);
        //dest.writeValue(bandData);
        //dest.writeValue(eventData);
        //dest.writeValue(notificationData);
        dest.writeString(line1);
        dest.writeString(line2);
        dest.writeString(line3);
        dest.writeTypedList(bandDatas);
        dest.writeTypedList(eventDatas);
        //dest.writeTypedList(notificationDatas);
        dest.writeParcelable(notificationData, flags);
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