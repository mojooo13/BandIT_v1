package com.example.mo.bandit_v1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONObject;

public class ProfileActivity extends Activity {

    int inviteToBandProfilID;
    int inviteToBandBandID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        final int id = getIntent().getExtras().getInt("id");
        ProfilData profilData = new ProfilData(id);

        inviteToBandProfilID = id;

        TextView firstName = (TextView)findViewById(R.id.profileFirstName);
        TextView secondName = (TextView)findViewById(R.id.profileSecondName);
        TextView adress = (TextView)findViewById(R.id.profileAdress);
        TextView instruments = (TextView)findViewById(R.id.profileInstruments);
        TextView genre = (TextView)findViewById(R.id.profileGenre);
        TextView number = (TextView)findViewById(R.id.profileMobileNumber);

        String instrumentString = "";
        for(int i = 0; i< profilData.getProfilInstruments().length;i++) {
            instrumentString = instrumentString + profilData.getProfilInstruments()[i] + ", ";
        }
        instrumentString = instrumentString.substring(0,instrumentString.lastIndexOf(","));

        firstName.setText("First Name: "+profilData.getProfilVorname());
        secondName.setText("Second Name: "+profilData.getProfilNachname());
        adress.setText("Adress: "+profilData.getProfilAdress());
        instruments.setText("Instruments: "+instrumentString);
        genre.setText("Genre: "+profilData.getProfilGenre());
        number.setText("Tel.: "+profilData.getTelephonNr());

        Button inviteToBandButton = (Button) findViewById(R.id.inviteToBandButton);
        inviteToBandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this,InviteToBandActivity.class);
                intent.putExtra("profilID",id);
                startActivityForResult(intent,1);
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                int idBand = data.getExtras().getInt("idBand");
                inviteToBandBandID = idBand;

                JSONObject jsonObject = new JSONObject();
                try{
                    jsonObject.put("command","updateBandMember");
                    jsonObject.put("profileId",inviteToBandProfilID);
                    jsonObject.put("bandId",inviteToBandBandID);
                    jsonObject.put("order","add");

                    ServerCommunication serverCommunication = new ServerCommunication();
                    String answer = serverCommunication.communication(jsonObject.toString());
                    System.out.println(answer);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
            if (resultCode == RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile, menu);
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
