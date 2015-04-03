package com.example.mo.bandit_v1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;


public class MessageDetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        final BandRequest bandRequest;
        EventReuqest eventReuqest;




        Intent intent = getIntent();
        String status = intent.getStringExtra("status");
        final Data data = intent.getParcelableExtra("data");
        if(status.equals("band")){
            String bandname;
            String bandgenre;
            String text;

            setContentView(R.layout.bandrequest_detail);
            bandRequest = intent.getParcelableExtra("bandRequest");
            bandname = bandRequest.bandName;
            bandgenre = bandRequest.bandGenre;
            text = bandRequest.text;

            TextView bandNameTextView = (TextView) findViewById(R.id.bandnameBandRequest);
            TextView bandGenreTextView = (TextView) findViewById(R.id.bandgenreBandRequest);
            TextView messageTextView = (TextView) findViewById(R.id.textEventRequest);

            bandNameTextView.setText("BandName: "+bandname);
            bandGenreTextView.setText("BandGenre: "+bandgenre);
            messageTextView.setText("Message: "+text);

            Button acceptButton = (Button) findViewById(R.id.acceptButtontBandRequest);
            Button declineButton = (Button) findViewById(R.id.declineButtonBandRequest);

            acceptButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ServerCommunication serverCommunication = new ServerCommunication();
                    JSONObject jsonObject= new JSONObject();
                    try {
                        jsonObject.put("command","updateBandMember");
                        jsonObject.put("profileId",data.profilData.profilID);
                        jsonObject.put("order","accept");
                        jsonObject.put("bandId",bandRequest.bandID);

                        System.out.println(jsonObject.toString());
                        String jsonString = serverCommunication.communication(jsonObject.toString());

                        System.out.println("Antwort: "+jsonString);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    finish();
                }
            });

            declineButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ServerCommunication serverCommunication = new ServerCommunication();
                    JSONObject jsonObject= new JSONObject();
                    try {
                        jsonObject.put("command","updateBandMember");
                        jsonObject.put("profileId",data.profilData.profilID);
                        jsonObject.put("order","delete");
                        jsonObject.put("bandId",bandRequest.bandID);

                        System.out.println(jsonObject.toString());
                        String jsonString = serverCommunication.communication(jsonObject.toString());

                        System.out.println("Antwort: "+jsonString);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    finish();
                }
            });
        }
        else{
            setContentView(R.layout.eventrequest_detail);
            eventReuqest = intent.getParcelableExtra("eventRequest");

            String eventname;
            String eventgenre;
            String date;
            String time;
            String location;
            String senderFirstName;
            String senderSecoundName;
            String message;


            eventname = eventReuqest.eventName;
            eventgenre = eventReuqest.eventGenre;
            date = eventReuqest.eventDate;
            time = eventReuqest.eventTime;
            location = eventReuqest.eventLocation;
            senderFirstName = eventReuqest.requestFirstName;
            senderSecoundName = eventReuqest.requestSecondName;
            message = eventReuqest.text;

            TextView eventnameTextView = (TextView) findViewById(R.id.eventnameEventRequest);
            TextView eventgenreTextView = (TextView) findViewById(R.id.genreEventRequest);
            TextView dateTextView = (TextView) findViewById(R.id.dateEventRequest);
            TextView timeTextView = (TextView) findViewById(R.id.timeEventRequest);
            TextView locationTextView = (TextView) findViewById(R.id.locationEventRequest);
            TextView senderFirstNameTextView = (TextView) findViewById(R.id.senderFirstNameEventRequest);
            TextView senderSecoundNameTextView = (TextView) findViewById(R.id.senderSecoundNameEventRequest);
            TextView messageTextView = (TextView) findViewById(R.id.textEventRequest);

            eventnameTextView.setText("EventName: "+eventname);
            eventgenreTextView.setText("EventGenre: "+eventgenre);
            dateTextView.setText("Date: "+date);
            timeTextView.setText("Time: "+time);
            locationTextView.setText("Location: "+location);
            senderFirstNameTextView.setText("Sender Firstname: "+senderFirstName);
            senderSecoundNameTextView.setText("Sender Secoundname: "+senderSecoundName);
            messageTextView.setText("Message: "+message);

            Button acceptButton = (Button) findViewById(R.id.acceptButtontEventRequest);
            Button declineButton = (Button) findViewById(R.id.declineButtonEventRequest);

            acceptButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            declineButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.message_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
