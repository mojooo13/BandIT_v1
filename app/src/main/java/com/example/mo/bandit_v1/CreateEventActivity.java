package com.example.mo.bandit_v1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import org.json.JSONException;
import org.json.JSONObject;


public class CreateEventActivity extends Activity {

    ImageView contactImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);


        Intent intent = getIntent();

        final Data data = intent.getParcelableExtra("data");


        contactImageView = (ImageView) findViewById(R.id.emptyBandCreateEventImageView);
        contactImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent , "Select Event-Logo"),1);
            }
        });

        Button saveCreateEventButton = (Button) findViewById(R.id.saveCreateEventButton);
        saveCreateEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText newEventnameCreateEventEditText = (EditText) findViewById(R.id.newEventnameCreateEventEditText);
                EditText newDateCreateEventEditDate = (EditText) findViewById(R.id.newDateCreateEventEditDate);
                EditText newTimeCreateEventEditTime = (EditText) findViewById(R.id.newTimeCreateEventEditTime);
                EditText newEventLocationCreateEventEditText = (EditText) findViewById(R.id.newEventLocationCreateEventEditText);
                EditText newGenresCreateEventEditText = (EditText) findViewById(R.id.newGenresCreateEventEditText);

                String newEventnameCreateEventString;
                String newDateCreateEventString;
                String newTimeCreateEventString;
                String newEventLocationCreateEventString;
                String newGenresCreateEventString;

                newEventnameCreateEventString = newEventnameCreateEventEditText.getText().toString();
                newDateCreateEventString = newDateCreateEventEditDate.getText().toString();
                newTimeCreateEventString = newTimeCreateEventEditTime.getText().toString();
                newEventLocationCreateEventString = newEventLocationCreateEventEditText.getText().toString();
                newGenresCreateEventString = newGenresCreateEventEditText.getText().toString();

                EventData eventData = new EventData(newEventnameCreateEventString,newDateCreateEventString,newTimeCreateEventString,
                        newEventLocationCreateEventString,newGenresCreateEventString);


                ServerCommunication serverCommunication = new ServerCommunication();
                JSONObject jsonObject= new JSONObject();
                try {
                    jsonObject.put("command","createEvent");
                    jsonObject.put("eventName",eventData.eventName);
                    jsonObject.put("eventDate",eventData.eventDate);
                    jsonObject.put("eventTime",eventData.eventTime);
                    jsonObject.put("eventLocation",eventData.eventLocation);
                    jsonObject.put("eventGenre",eventData.eventGenre);
                    jsonObject.put("profileId",data.profilData.profilID);

                    System.out.println(jsonObject.toString());
                    String jsonString = serverCommunication.communication(jsonObject.toString());
                    System.out.println("Antwort: "+jsonString);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
              //finish();
                String email = data.profilData.profilEmail;
                String passwort = data.profilData.passwort;
                LoginData loginData = new LoginData(email,passwort);
                if(loginData.login().equals("true")){
                    Data dataUpdated = new Data(loginData.line1, loginData.line2, loginData.line3, loginData.line4);

                    //finish();
                    Intent intent = new Intent(CreateEventActivity.this,MainMenuActivity.class);
                    intent.putExtra("data",dataUpdated);
                    intent.putExtra("profilID",5);
                    startActivity(intent);
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.create_event, menu);
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

    public void onActivityResult(int reqCode, int resCode, Intent data){
        if(resCode == Activity.RESULT_OK){
            if (reqCode == 1)
                contactImageView.setImageURI(data.getData());
        }
    }
}
