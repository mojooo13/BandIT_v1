package com.example.mo.bandit_v1;

/**
 * Created by Mo on 01.12.2014.
 */
public class NotificationData {
    int senderID;
    int receiverBandID;
    int notificationID;
    int[] notificationIDs;
    int status = 0; //1...Annahme 0...noch nicht beantwortet -1...abgelent
    String bandname;
    String date;
    String message;
    String sendername;

    //Aufruf der Message Activity
    public NotificationData(int senderID){
        this.senderID = senderID;

        //von Datenbank
        notificationIDs = new int[]{1,2,3,4};
    }

    //Message Detail Activity
    public NotificationData(int notificationID, int defaulT){
        this.notificationID = notificationID;

        //von Datenbank

        bandname = "ACDC";
        date = "20.11.2014";
        message = "Hallo ich würde Ihre Band ACDC gerne zu unserem Sommerfest einladen. Wir können ihnen 200€ Gage für " +
                "einen dreistündigen Auftritt bieten. Außerdem übernehmen wir die Versorgungs und Transportkosten. Mit " +
                "freundliche Grüßen Moritz Hauch";
        sendername = "Moritz Hauch";
    }


    //Einladung Event
    public NotificationData(String date, String message, int senderID, String bandname){
        this.senderID = senderID;
        this.message = message;
        this.date = date;
        this.bandname = bandname;
        status = 0;
    }

    public void getMessages(int notificationID){
        //Datenbank sucht nach id

        status = 1;
        date = "20.10.2014";
        message = "Hallo ich würde Ihre Band ACDC gerne zu unserem Sommerfest einladen. Wir können ihnen 200€ Gage für " +
                "einen dreistündigen Auftritt bieten. Außerdem übernehmen wir die Versorgungs und Transportkosten. Mit " +
                "freundliche Grüßen Moritz Hauch";
    }

    //Annahme/Ablehnen
    public void setStatus(int status){
        this.status = status;
    }

}
