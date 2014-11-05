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


public class EditProfilActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);

        TextView vornameEditTextTextView = (TextView) findViewById(R.id.vornameEditProfilTextView);
        TextView nachnameEditTextTextView = (TextView) findViewById(R.id.nachnameEditProfilTextView);
        TextView adresseEditTextTextView = (TextView) findViewById(R.id.adresseEditProfilTextView);
        TextView instrumentEditTextTextView = (TextView) findViewById(R.id.instrumentEditProfilTextView);
        TextView genreEditTextTextView = (TextView) findViewById(R.id.genreEditProfilTextView);
        TextView emailEditTextTextView = (TextView) findViewById(R.id.emailEditProfilTextView);

        final Intent intent = getIntent();

        vornameEditTextTextView.setText(intent.getStringExtra("vorname"));
        nachnameEditTextTextView.setText(intent.getStringExtra("nachname"));
        adresseEditTextTextView.setText(intent.getStringExtra("adress"));
        instrumentEditTextTextView.setText(intent.getStringExtra("instrument"));
        genreEditTextTextView.setText(intent.getStringExtra("genre"));
        emailEditTextTextView.setText(intent.getStringExtra("email"));



        Button saveEditProfilButton = (Button) findViewById(R.id.saveEditProfilButton);
        saveEditProfilButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText newVornameEditProfilEditText = (EditText) findViewById(R.id.newVornameEditProfilEditText);
                EditText newNachnameEditProfilEditText = (EditText) findViewById(R.id.newNachnameEditProfilEditText);
                EditText newAdresseEditProfilEditText = (EditText) findViewById(R.id.newAdresseEditProfilEditText);
                EditText newInstrumentEditProfilEditText = (EditText) findViewById(R.id.newInstrumentEditProfilEditText);
                EditText newGenreEditProfilEditText = (EditText) findViewById(R.id.newGenreEditProfilEditText);
                EditText newEmailEditProfilEditText = (EditText) findViewById(R.id.newEmailEditProfilEditText);

                String newVornameString = newVornameEditProfilEditText.getText().toString();
                String newNachnameString = newNachnameEditProfilEditText.getText().toString();
                String newAdresseString = newAdresseEditProfilEditText.getText().toString();
                String newInstrumentString = newInstrumentEditProfilEditText.getText().toString();
                String newGenreString = newGenreEditProfilEditText.getText().toString();
                String newEmailString = newEmailEditProfilEditText.getText().toString();

                if(newVornameString.isEmpty()){
                    newVornameString = intent.getStringExtra("vorname");
                }
                if(newNachnameString.isEmpty()){
                    newNachnameString = intent.getStringExtra("nachname");
                }
                if(newAdresseString.isEmpty()){
                    newAdresseString = intent.getStringExtra("adress");
                }
                if(newInstrumentString.isEmpty()){
                    newInstrumentString = intent.getStringExtra("instrument");
                }
                if(newGenreString.isEmpty()){
                    newGenreString = intent.getStringExtra("genre");
                }
                if(newEmailString.isEmpty()){
                    newEmailString = intent.getStringExtra("email");
                }

                ProfilData profilData = new ProfilData(newVornameString, newNachnameString, newEmailString, newAdresseString, newInstrumentString, newGenreString);

                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.edit_profil, menu);
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
