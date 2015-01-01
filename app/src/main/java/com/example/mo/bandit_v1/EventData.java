package com.example.mo.bandit_v1;

/**
 * Created by Mo on 24.10.2014.
 */
public class EventData {
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

        //Daten von id holen
        eventName = "NOVA ROCK";
        eventDate = "02.09.2015";
        eventTime = "19:30";
        eventLocation = "Schüdlbauer";
        eventGenre = "Hard Rock";
        eventMusik = "High Way To Hell";
        idBands = new int[] {1,2,3,4};
    }

    public EventData(String eventName,String eventDate,String eventTime,String eventLocation,String eventBand,String eventGenre){
        this.eventName = eventName;
        this.eventTime = eventTime;
        this.eventDate = eventDate;
        this.eventLocation = eventLocation;
        this.eventBand = eventBand;
        this.eventGenre = eventGenre;

        //get Id
        idEvent = 21;
    }
    
}
