package com.example.mo.bandit_v1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;


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
        TextView mobilenumberEditTextTextView = (TextView) findViewById(R.id.MobileNumberEditTextProfile);

        final Intent intent = getIntent();

        vornameEditTextTextView.setText("First name: "+intent.getStringExtra("vorname"));
        nachnameEditTextTextView.setText("Second name: "+intent.getStringExtra("nachname"));
        adresseEditTextTextView.setText("Adress: "+intent.getStringExtra("adress"));
        instrumentEditTextTextView.setText("Instrument:");
        genreEditTextTextView.setText("Genre: "+intent.getStringExtra("genre"));
        emailEditTextTextView.setText("E-Mail "+intent.getStringExtra("email"));
        mobilenumberEditTextTextView.setText("Mobile number: "+intent.getStringExtra("mobileNumber"));

        final RadioButton radioAddButton = (RadioButton) findViewById(R.id.profileRadioButtonAdd);
        final RadioButton radioDeleteButton = (RadioButton) findViewById(R.id.profileRadioButtonDelete);


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
                EditText newMobileNumberProfileEditText = (EditText) findViewById(R.id.NewMobileNumberEditTextProfile);

                String newVornameString = newVornameEditProfilEditText.getText().toString();
                String newNachnameString = newNachnameEditProfilEditText.getText().toString();
                String newAdresseString = newAdresseEditProfilEditText.getText().toString();
                String newInstrumentString = newInstrumentEditProfilEditText.getText().toString();
                String newGenreString = newGenreEditProfilEditText.getText().toString();
                String newEmailString = newEmailEditProfilEditText.getText().toString();
                String newMobileNumberString = newMobileNumberProfileEditText.getText().toString();

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
                if(newMobileNumberString.isEmpty()){
                    newMobileNumberString = intent.getStringExtra("mobileNumber");
                }


                ProfilData profilData = new ProfilData(newVornameString, newNachnameString, newEmailString, newAdresseString, newInstrumentString, newGenreString);
                ServerCommunication con = new ServerCommunication();
                JSONObject jsonObject= new JSONObject();
                try {
                    jsonObject.put("command","updateProfileData");
                    jsonObject.put("firstName",newVornameString);
                    jsonObject.put("secondName",newNachnameString);
                    jsonObject.put("adress",newAdresseString);
                    jsonObject.put("mobileNumber",newMobileNumberString);
                    jsonObject.put("email",newEmailString);
                    jsonObject.put("genre",newGenreString);
                    jsonObject.put("instrument",newInstrumentString);
                    jsonObject.put("id",intent.getIntExtra("id",0));

                    if(radioAddButton.isChecked()){
                        jsonObject.put("instrumentStatus","add");
                    }
                    else if(radioDeleteButton.isChecked()){
                        jsonObject.put("instrumentStatus","delete");
                    }
                    else{
                        jsonObject.put("instrumentStatus","none");
                    }

                    System.out.println(jsonObject.toString());
                    String jsonString = con.communication(jsonObject.toString());
                    jsonString = jsonString.substring(jsonString.indexOf("$")+1);
                    System.out.println(jsonString);
                    jsonObject = new JSONObject(jsonString);

                    if(jsonObject.get("status").equals("emailAlreadyExits")){
                        Toast.makeText(EditProfilActivity.this,
                                "the chosen email already exists", Toast.LENGTH_SHORT).show();
                        System.out.println("error email already exits");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
