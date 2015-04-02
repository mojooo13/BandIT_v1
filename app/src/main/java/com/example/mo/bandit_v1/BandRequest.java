package com.example.mo.bandit_v1;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Mo on 23.02.2015.
 */
public class BandRequest implements Parcelable {
    String bandName;
    String text;
    String bandGenre;
    int bandID;
    int requestID;
    boolean status;

    public BandRequest(int requestID, int bandID, String bandGenre, String text, String bandName) {
        this.requestID = requestID;
        this.bandID = bandID;
        this.bandGenre = bandGenre;
        this.text = text;
        this.bandName = bandName;
    }

    protected BandRequest(Parcel in) {
        bandName = in.readString();
        text = in.readString();
        bandGenre = in.readString();
        bandID = in.readInt();
        requestID = in.readInt();
        status = in.readByte() != 0x00;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(bandName);
        dest.writeString(text);
        dest.writeString(bandGenre);
        dest.writeInt(bandID);
        dest.writeInt(requestID);
        dest.writeByte((byte) (status ? 0x01 : 0x00));
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<BandRequest> CREATOR = new Parcelable.Creator<BandRequest>() {
        @Override
        public BandRequest createFromParcel(Parcel in) {
            return new BandRequest(in);
        }

        @Override
        public BandRequest[] newArray(int size) {
            return new BandRequest[size];
        }
    };
}