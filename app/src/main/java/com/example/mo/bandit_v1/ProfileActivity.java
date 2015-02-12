package com.example.mo.bandit_v1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ProfileActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        int id = getIntent().getExtras().getInt("id");
        ProfilData profilData = new ProfilData(id);

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

        firstName.setText(profilData.getProfilVorname());
        secondName.setText(profilData.getProfilNachname());
        adress.setText(profilData.getProfilAdress());
        instruments.setText(instrumentString);
        genre.setText(profilData.getProfilGenre());
        number.setText(profilData.getTelephonNr());

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
