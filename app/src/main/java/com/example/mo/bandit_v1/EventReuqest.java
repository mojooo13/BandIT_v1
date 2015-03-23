package com.example.mo.bandit_v1;

/**
 * Created by Mo on 23.02.2015.
 */
public class EventReuqest {
    int requestID;
    int senderID;
    int eventID;
    String eventName;
    String eventGenre;
    String eventLocation;
    String eventDate;
    String eventTime;
    String requestFirstName;
    String requestSecondName;
    String text;
    String bandName;

    public EventReuqest(int senderID, int requestID, int eventID, String eventName, String eventGenre, String eventLocation, String eventDate, String eventTime, String requestFirstName, String requestSecondName, String text, String bandName) {
        this.senderID = senderID;
        this.requestID = requestID;
        this.eventID = eventID;
        this.eventName = eventName;
        this.eventGenre = eventGenre;
        this.eventLocation = eventLocation;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.requestFirstName = requestFirstName;
        this.requestSecondName = requestSecondName;
        this.text = text;
        this.bandName = bandName;
    }
}
