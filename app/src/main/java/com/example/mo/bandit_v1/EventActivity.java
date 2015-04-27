package com.example.mo.bandit_v1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class EventActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        final int id = getIntent().getExtras().getInt("id");
        EventData eventData = new EventData(id);
        System.out.println("hallo");

        TextView eventName = (TextView) findViewById(R.id.eventName);
        TextView eventDate = (TextView) findViewById(R.id.eventDate);
        TextView eventTime = (TextView) findViewById(R.id.eventTime);
        TextView eventLocation = (TextView) findViewById(R.id.eventLocation);
        TextView eventBands = (TextView) findViewById(R.id.eventBands);
        TextView eventGenre = (TextView) findViewById(R.id.eventGenre);

        eventName.setText(eventData.eventName);
        eventDate.setText(eventData.eventDate);
        eventTime.setText(eventData.eventTime);
        eventLocation.setText(eventData.eventLocation);
        eventGenre.setText(eventData.eventGenre);
        String bandnames="";
        for (int i = 0;i<eventData.bandNames.length;i++){
            if(eventData.bandNames[i].isEmpty()) {
                bandnames = bandnames + " " + eventData.bandNames[i];
            }
        }
        eventBands.setText(bandnames);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.event, menu);
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
        if(id == R.id.action_home_event){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
