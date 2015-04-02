package com.example.mo.bandit_v1;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Mo on 01.12.2014.
 */
public class NotificationData implements Parcelable {
    ArrayList<BandRequest> bandRequests = new ArrayList();
    ArrayList<EventReuqest> eventReuqests = new ArrayList();

    public NotificationData(ArrayList<BandRequest> bandRequests, ArrayList<EventReuqest> eventReuqests) {
        this.bandRequests = bandRequests;
        this.eventReuqests = eventReuqests;
    }

    public NotificationData() {

    }

    protected NotificationData(Parcel in) {
        bandRequests = new ArrayList<BandRequest>();
        in.readTypedList(bandRequests,BandRequest.CREATOR);
        eventReuqests = new ArrayList<EventReuqest>();
        in.readTypedList(eventReuqests,EventReuqest.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(bandRequests);
        dest.writeTypedList(eventReuqests);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<NotificationData> CREATOR = new Parcelable.Creator<NotificationData>() {
        @Override
        public NotificationData createFromParcel(Parcel in) {
            return new NotificationData(in);
        }

        @Override
        public NotificationData[] newArray(int size) {
            return new NotificationData[size];
        }
    };

}
