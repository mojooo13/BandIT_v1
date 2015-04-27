package com.example.mo.bandit_v1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class EditBandActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_band);

        int bandId = getIntent().getExtras().getInt("BandID");
        final BandData bandData = new BandData(bandId);

        final Data data = getIntent().getParcelableExtra("data");

        TextView newBandnameEditBandTextView = (TextView) findViewById(R.id.newBandnameEditBandTextView);
        TextView newMemberEditBandTextView = (TextView) findViewById(R.id.newMemberEditBandTextView);
        TextView newGenreEditBandTextView = (TextView) findViewById(R.id.newGenreEditBandTextView);
        TextView newMusikEditBandTextView = (TextView) findViewById(R.id.newMusikEditBandTextView);
        TextView newInstrumentsEditBandTextView = (TextView) findViewById(R.id.newInstrumentsEditBandTextView);
        System.out.println(bandData.bandName);;
        System.out.println(bandData.bandMembers);
        newBandnameEditBandTextView.setText(bandData.getBandName());
        newMemberEditBandTextView.setText(bandData.getBandMembers());
        newGenreEditBandTextView.setText(bandData.getBandGenre());
        newMusikEditBandTextView.setText(bandData.getBandMusik());
        newInstrumentsEditBandTextView.setText(bandData.getBandInstruments());





        Button saveEditBandButton = (Button) findViewById(R.id.saveEditBandButton);
        saveEditBandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newBandnameEditBandString;
                String newMemberEditBandString;
                String newGenreEditBandString;
                String newMusikEditBandString;
                String newInstrumentEditBandString;
                EditText newBandnameEditBandEditText = (EditText)findViewById(R.id.newBandnameEditBandEditText);
                EditText newMemberEditBandEditText = (EditText)findViewById(R.id.newMembersEditBandEditText);
                EditText newGenreEditBandEditText = (EditText)findViewById(R.id.newGenreEditBandEditText);
                EditText newMusikEditBandEditText = (EditText)findViewById(R.id.newMusikEditBandEditText);
                EditText newInstrumentEditBandEditText = (EditText)findViewById(R.id.newInstrumentsEditBandEditText);

                newBandnameEditBandString = newBandnameEditBandEditText.getText().toString();
                newMemberEditBandString = newMemberEditBandEditText.getText().toString();
                newGenreEditBandString = newGenreEditBandEditText.getText().toString();
                newMusikEditBandString = newMusikEditBandEditText.getText().toString();
                newInstrumentEditBandString = newInstrumentEditBandEditText.getText().toString();
                bandData.setBandName(newBandnameEditBandString);
                bandData.setBandMembers(newMemberEditBandString);
                bandData.setBandGenre(newGenreEditBandString);
                bandData.setBandMusik(newMusikEditBandString);
                bandData.setBandInstruments(newInstrumentEditBandString);

                String email = data.profilData.profilEmail;
                String passwort = data.profilData.passwort;
                LoginData loginData = new LoginData(email,passwort);
                if(loginData.login().equals("true")){
                    Data dataUpdate = new Data(loginData.line1, loginData.line2, loginData.line3, loginData.line4);

                    //finish();
                    Intent intent = new Intent(EditBandActivity.this,MainMenuActivity.class);
                    intent.putExtra("data",dataUpdate);
                    intent.putExtra("profilID",5);
                    startActivity(intent);
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.edit_band, menu);
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
