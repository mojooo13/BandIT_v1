package com.example.mo.bandit_v1;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URISyntaxException;


public class BandActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_band);

        final int id = getIntent().getExtras().getInt("id");
        BandData bandData = new BandData(id);

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
                intent.putExtra("bandID",id);

                startActivity(intent);

            }
        });

        Button uploadMusicButton = (Button) findViewById(R.id.bandUploadMusic);
        editBandBandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showFileChooser();
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

    private static final int FILE_SELECT_CODE = 0;

    private void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("mp3");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(
                    Intent.createChooser(intent, "Select a File to Upload"),
                    FILE_SELECT_CODE);
        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(this, "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case FILE_SELECT_CODE:
                if (resultCode == RESULT_OK) {
                    // Get the Uri of the selected file
                    Uri uri = data.getData();
                    Log.d("TAG", "File Uri: " + uri.toString());
                    // Get the path
                    String path = null;
                    try {
                        path = FileUtils.getPath(this, uri);
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                    Log.d("TAG", "File Path: " + path);
                    // Get the file instance
                    // File file = new File(path);
                    // Initiate the upload
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}

