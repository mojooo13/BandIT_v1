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

import org.json.JSONException;
import org.json.JSONObject;


public class CreateBandActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_band);

        Button saveCreateBandButton = (Button) findViewById(R.id.saveCreateBandButton);
        saveCreateBandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = getIntent();
                Data data = intent.getParcelableExtra("data");

                int id = data.profilData.profilID;

                EditText newBandnameCreateBandEditText = (EditText) findViewById(R.id.newBandnameCreateBandEditText);
                EditText newGenreCreateBandEditText = (EditText) findViewById(R.id.newGenreCreateBandEditText);

                String bandnameCreateBandString = newBandnameCreateBandEditText.getText().toString();
                String genreCreateBandString = newGenreCreateBandEditText.getText().toString();

                JSONObject obj = new JSONObject();
                String jsonString;
                String status="";

                try {
                    obj.put("bandName", bandnameCreateBandString);
                    obj.put("genre", genreCreateBandString);
                    obj.put("id", id);
                    obj.put("command", "createBand");

                    jsonString = ""+obj;
                    System.out.println(jsonString);

                    ServerCommunication serverCommunication = new ServerCommunication();
                    String line = serverCommunication.communication(jsonString);
                    System.out.println("++++++++++"+line);
                    line = line.substring(line.indexOf("$")+1,line.length());
                    System.out.println(line);
                    JSONObject jsonObject = new JSONObject(line);
                    status = jsonObject.get("status").toString();



                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
                if(status.equals("true")){
                    String email = data.profilData.profilEmail;
                    String passwort = data.profilData.passwort;
                    LoginData loginData = new LoginData(email,passwort);
                    if(loginData.login().equals("true")){
                        data = new Data(loginData.line1, loginData.line2, loginData.line3, loginData.line4);

                        //finish();
                        intent = new Intent(CreateBandActivity.this,MainMenuActivity.class);
                        intent.putExtra("data",data);
                        intent.putExtra("profilID",5);
                        startActivity(intent);
                    }

                }
                else if(status.equals("bandnameexists")){
                    TextView errorCreateBandTextView = (TextView) findViewById(R.id.errorCreateBandTextView);
                    errorCreateBandTextView.setText("Bandname already exists.\nPlease choose a other name.");
                }
                else{
                    TextView errorCreateBandTextView = (TextView) findViewById(R.id.errorCreateBandTextView);
                    errorCreateBandTextView.setText("Not succsessfull");
                }


            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.create_band, menu);
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
