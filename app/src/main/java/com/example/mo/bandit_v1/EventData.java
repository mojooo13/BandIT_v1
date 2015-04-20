package com.example.mo.bandit_v1;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;



/**
 * Created by Mo on 24.10.2014.
 */
public class EventData implements Parcelable {
    int idEvent;
    int[] idBands;
    String eventName;
    String eventDate;
    String eventTime;
    String eventLocation;
    String eventBand;
    String eventGenre;
    String eventMusik;

    public EventData (int idEvent) {
        this.idEvent = idEvent;

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("command","getEventData");
            jsonObject.put("id",idEvent);

            ServerCommunication con = new ServerCommunication();
            String jsonString = con.communication(jsonObject.toString());
            jsonString = jsonString.substring(jsonString.indexOf("$")+1);
            jsonObject = new JSONObject(jsonString);

            eventName = jsonObject.getString("eventName");
            eventDate = jsonObject.getString("eventDate");
            //eventTime = "19:30"; muss noch hinzugefügt werden
            eventLocation = jsonObject.getString("eventLocation");
            eventGenre = jsonObject.getString("eventGenre");
            //eventMusik = "High Way To Hell"; löschen ?
            idBands = new int[] {1,2,3,4};


        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Daten von id holen

        eventName = "NOVA ROCK";
        eventDate = "02.09.2015";
        eventTime = "19:30";
        eventLocation = "Schüdlbauer";
        eventGenre = "Hard Rock";
        eventMusik = "High Way To Hell";
        //idBands = new int[] {1,2,3,4};
    }
    public EventData(int idEvent, String eventName, String  eventGenre){
        this.idEvent = idEvent;
        this.eventName = eventName;
        this.eventGenre = eventGenre;


    }

    public EventData(String eventName,String eventDate,String eventTime,String eventLocation,String eventGenre){
        this.eventName = eventName;
        this.eventTime = eventTime;
        this.eventDate = eventDate;
        this.eventLocation = eventLocation;
        this.eventBand = eventBand;
        this.eventGenre = eventGenre;

        //get Id
        idEvent = 21;
    }


    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }
    public void setIdBands(int[] idBands) {
        this.idBands = idBands;
    }
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }
    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }
    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }
    public void setEventBand(String eventBand) {
        this.eventBand = eventBand;
    }
    public void setEventGenre(String eventGenre) {
        this.eventGenre = eventGenre;
    }
    public void setEventMusik(String eventMusik) {
        this.eventMusik = eventMusik;
    }

    public int getIdEvent() {
        return idEvent;
    }
    public int[] getIdBands() {
        return idBands;
    }
    public String getEventName() {
        return eventName;
    }
    public String getEventDate() {
        return eventDate;
    }
    public String getEventTime() {
        return eventTime;
    }
    public String getEventLocation() {
        return eventLocation;
    }
    public String getEventBand() {
        return eventBand;
    }
    public String getEventGenre() {
        return eventGenre;
    }
    public String getEventMusik() {
        return eventMusik;
    }

    protected EventData(Parcel in) {
        idEvent = in.readInt();
        eventName = in.readString();
        eventDate = in.readString();
        eventTime = in.readString();
        eventLocation = in.readString();
        eventBand = in.readString();
        eventGenre = in.readString();
        eventMusik = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idEvent);
        dest.writeString(eventName);
        dest.writeString(eventDate);
        dest.writeString(eventTime);
        dest.writeString(eventLocation);
        dest.writeString(eventBand);
        dest.writeString(eventGenre);
        dest.writeString(eventMusik);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<EventData> CREATOR = new Parcelable.Creator<EventData>() {
        @Override
        public EventData createFromParcel(Parcel in) {
            return new EventData(in);
        }

        @Override
        public EventData[] newArray(int size) {
            return new EventData[size];
        }
    };

}
