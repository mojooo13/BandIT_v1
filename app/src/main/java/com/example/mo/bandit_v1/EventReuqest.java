package com.example.mo.bandit_v1;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Mo on 23.02.2015.
 */
public class EventReuqest implements Parcelable {
    int requestID;
    int requestSenderID;
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

    public EventReuqest(int requestID, int requestSenderID, int eventID, String eventName, String eventGenre, String eventLocation, String eventDate, String eventTime, String requestFirstName, String requestSecondName, String text, String bandName) {
        this.requestSenderID = requestSenderID;
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

    protected EventReuqest(Parcel in) {
        requestID = in.readInt();
        requestSenderID = in.readInt();
        eventID = in.readInt();
        eventName = in.readString();
        eventGenre = in.readString();
        eventLocation = in.readString();
        eventDate = in.readString();
        eventTime = in.readString();
        requestFirstName = in.readString();
        requestSecondName = in.readString();
        text = in.readString();
        bandName = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(requestID);
        dest.writeInt(requestSenderID);
        dest.writeInt(eventID);
        dest.writeString(eventName);
        dest.writeString(eventGenre);
        dest.writeString(eventLocation);
        dest.writeString(eventDate);
        dest.writeString(eventTime);
        dest.writeString(requestFirstName);
        dest.writeString(requestSecondName);
        dest.writeString(text);
        dest.writeString(bandName);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<EventReuqest> CREATOR = new Parcelable.Creator<EventReuqest>() {
        @Override
        public EventReuqest createFromParcel(Parcel in) {
            return new EventReuqest(in);
        }

        @Override
        public EventReuqest[] newArray(int size) {
            return new EventReuqest[size];
        }
    };
}
