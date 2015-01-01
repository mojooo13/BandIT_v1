package com.example.mo.bandit_v1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class BandActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_band);

        final int bandID = getIntent().getExtras().getInt("bandID");
        BandData bandData = new BandData(bandID);

        TextView bandnameBandTextView = (TextView) findViewById(R.id.bandnameBandTextView);
        TextView genreBandTextView = (TextView) findViewById(R.id.genreBandTextView);
        TextView instrumentsBandTextView = (TextView) findViewById(R.id.instrumentsBandTextView);
        TextView membersBandTextView = (TextView) findViewById(R.id.membersBandTextView);


        bandnameBandTextView.setText(bandData.getBandName());
        genreBandTextView.setText(bandData.getBandGenre());
        instrumentsBandTextView.setText(bandData.getBandInstruments());
        membersBandTextView.setText(bandData.getBandMembers());


        Button editBandBandButton = (Button) findViewById(R.id.editBandBandButton);
        editBandBandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BandActivity.this,EditBandActivity.class);
                intent.putExtra("bandID",bandID);

                startActivity(intent);

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.band, menu);
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
        if(id == R.id.action_home_band){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
