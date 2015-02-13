package com.example.mo.bandit_v1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class CreateBandActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_band);

        Button saveCreateBandButton = (Button) findViewById(R.id.saveCreateBandButton);
        saveCreateBandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //int profilID = getIntent().getExtras().getInt("profilID");

                EditText newBandnameCreateBandEditText = (EditText) findViewById(R.id.newBandnameCreateBandEditText);
                EditText newGenreCreateBandEditText = (EditText) findViewById(R.id.newGenreCreateBandEditText);

                String bandnameCreateBandString = newBandnameCreateBandEditText.getText().toString();
                String genreCreateBandString = newGenreCreateBandEditText.getText().toString();

                //BandData bandData = new BandData(profilID, bandnameCreateBandString,genreCreateBandString);

                finish();
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
