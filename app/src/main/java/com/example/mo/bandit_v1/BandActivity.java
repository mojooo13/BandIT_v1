package com.example.mo.bandit_v1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


public class BandActivity extends Activity {
    private MyItemAdapter myAdapter;

    int inviteToEventEventID;
    int inviteToEventBandID;
    int inviteToEventProfilID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_band);

        final int bandID = getIntent().getExtras().getInt("id");
        final Data data = getIntent().getParcelableExtra("data");

        inviteToEventProfilID = data.profilData.profilID;
        inviteToEventBandID = bandID;

        final BandData bandData = new BandData(bandID);
        final boolean fromFragment = getIntent().getExtras().getBoolean("fromFragment");
        initListDataTable(bandID);

        System.out.println(bandData.getBandID());
        TextView bandnameBandTextView = (TextView) findViewById(R.id.bandnameBandTextView);
        TextView genreBandTextView = (TextView) findViewById(R.id.genreBandTextView);
        TextView membersBandTextView = (TextView) findViewById(R.id.membersBandTextView);
        final TextView filePathTextView = (TextView)findViewById(R.id.uploadFilePathTextView);
        ListView musicListView = (ListView) findViewById(R.id.bandMusicListView);

        myAdapter = new MyItemAdapter();
        musicListView.setAdapter(myAdapter);

        bandnameBandTextView.setText(bandData.getBandName());
        genreBandTextView.setText(bandData.getBandGenre());
        membersBandTextView.setText(bandData.getBandMembers());

        Button editBandBandButton = (Button) findViewById(R.id.editBandBandButton);
        editBandBandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("hi");
                Intent editBandActivity = new Intent(BandActivity.this,EditBandActivity.class);
                startActivity(editBandActivity);

            }
        });

       if(fromFragment){
            LinearLayout uploadLayout = (LinearLayout)findViewById(R.id.BandpageMusicUploadLinearLayout);
            uploadLayout.setVisibility(View.VISIBLE);
            editBandBandButton.setVisibility(View.VISIBLE);
        }
        Button chooseMusicButton = (Button) findViewById(R.id.bandChooseMusic);
        chooseMusicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();

            }
        });

        Button uploadMusicButton = (Button) findViewById(R.id.bandUploadMusic);
        uploadMusicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String m = "";
                m=filePathTextView.getText().toString();

                if(!filePathTextView.getText().equals("")){
                    bandData.postMusic(filePathTextView.getText().toString());
                }
                else{
                    filePathTextView.setText("Please Add a File");
                    m="";
                }

            }
        });



        Button leaveBandButton = (Button) findViewById(R.id.leaveBandBandButton);
        leaveBandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServerCommunication serverCommunication = new ServerCommunication();
                JSONObject jsonObject= new JSONObject();
                try {
                    jsonObject.put("command","updateBandMember");
                    jsonObject.put("bandId",bandData.bandID);
                    jsonObject.put("profileId",data.profilData.profilID);
                    jsonObject.put("order","delete");

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
                    Data dataUpdate = new Data(loginData.line1, loginData.line2, loginData.line3, loginData.line4);

                    //finish();
                    Intent intent = new Intent(BandActivity.this,MainMenuActivity.class);
                    intent.putExtra("data",dataUpdate);
                    intent.putExtra("profilID",5);
                    startActivity(intent);
                }
            }
        });

        Button inviteBandToEventButton = (Button) findViewById(R.id.inviteBandToEventBandButton);
        inviteBandToEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BandActivity.this,InviteToEventActivity.class);
                intent.putExtra("data",data);
                intent.putExtra("bandID",bandID);

                startActivityForResult(intent, 1);
            }
        });

        musicListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listEntry chosenEntry = listDataTable.get(position);
                String musicEntry = chosenEntry.getMusicEntry();

                Intent playMusicActivity = new Intent(BandActivity.this,PlayMusic.class);
                playMusicActivity.putExtra("adress","http://192.168.88.21/musicupload/" + musicEntry);
                startActivity(playMusicActivity);


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

    public static boolean isIntentAvailable(Context context, String action) {
        final PackageManager packageManager = context.getPackageManager();
        final Intent intent = new Intent(action);
        List<ResolveInfo> list =
                packageManager.queryIntentActivities(intent,
                        PackageManager.MATCH_DEFAULT_ONLY);
        return true;
        //return list.size() > 0;
    }

    private static final int FILE_SELECT_CODE = 0;

    private void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("Audio/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);



        try {
            if(isIntentAvailable(this,"*/*")) {
                startActivityForResult(
                        Intent.createChooser(intent, "Select a File to Upload"),
                        FILE_SELECT_CODE);
            }
        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(this, "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                Data data = intent.getParcelableExtra("data");
                int idEvent = intent.getExtras().getInt("idEvent");
                inviteToEventEventID = idEvent;
                String date = intent.getExtras().getString("date");

                JSONObject jsonObject = new JSONObject();
                try{
                    jsonObject.put("command","addBandToEventRequest");
                    jsonObject.put("profileId",inviteToEventProfilID);
                    jsonObject.put("bandId",inviteToEventBandID);
                    jsonObject.put("eventId",inviteToEventEventID);
                    jsonObject.put("text",data.profilData.profilVorname+data.profilData.profilNachname+" would like to add you to his event.");
                    //jsonObject.put("date",date);

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

        switch (requestCode) {
            case FILE_SELECT_CODE:
                if (resultCode == RESULT_OK) {
                    // Get the Uri of the selected file
                    Uri uri = intent.getData();
                    Log.d("TAG", "File Uri: " + uri.toString());
                    // Get the path
                    String path = "no path";
                    try {
                        path = FileUtils.getPath(this, uri);
                        if(path != null) {
                            System.out.println(path);
                        }
                        else{
                            path = "no path found";
                        }
                    TextView filepathTextView = (TextView) findViewById(R.id.uploadFilePathTextView);
                    filepathTextView.setText(path);

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


        super.onActivityResult(requestCode, resultCode, intent);
    }


 //-----------------ListView------------------------------------------------------------------
    private class listEntry{
        private String musicTitle;
        private String musicEntry;
        private int id;

     public String getMusicTitle() {
         return musicTitle;
     }
     public String getMusicEntry() {
         return musicEntry;
     }

     private listEntry(String musicTitle, int musicId, int bandId) {
            this.musicTitle = musicTitle;
            musicEntry = bandId + "_" + musicId+".mp3";
        }
    }

    private ArrayList<listEntry> listDataTable;

    private void initListDataTable(int bandId) {
        listDataTable = new ArrayList<listEntry>();
        ServerCommunication con = new ServerCommunication();

        JSONObject jsonStringObject = new JSONObject();
        try {
            jsonStringObject.put("bandId", bandId);
            jsonStringObject.put("command", "getMusicList");

            String serverAnswer = con.communication(jsonStringObject.toString());
            String jsonString = serverAnswer.substring(serverAnswer.indexOf("$") + 1);

            jsonStringObject = new JSONObject(jsonString);
            System.out.println(jsonStringObject.toString());

            if (jsonStringObject.getString("command").equals("getMusicList") && jsonStringObject.getString("status").equals("true")) {
                JSONArray jsonStringArray = jsonStringObject.getJSONArray("music");

                for (int i = 0; i < jsonStringArray.length(); i++) {
                    jsonStringObject = jsonStringArray.getJSONObject(i);
                    listEntry newEntry = new listEntry(jsonStringObject.getString("title"),jsonStringObject.getInt("musicId"),bandId);
                    listDataTable.add(newEntry);
                }
            }
            else{
                System.out.println("some kind of error occured");
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    class MyItemAdapter extends BaseAdapter {
        private final LayoutInflater mInflater;
        public MyItemAdapter() {
            mInflater = (LayoutInflater) BandActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        public int getCount() {
            return listDataTable.size();
        }
        public listEntry getItem(int position) {
            return listDataTable.get(position);
        }
        public long getItemId(int position) {
            return (long) position;
        }
        public View getView(int position, View convertView, ViewGroup parent) {
            LinearLayout itemView = (LinearLayout) mInflater.inflate(R.layout.musicplaylistitem, parent, false);
            bindView(itemView, position);
            return itemView;
        }
        private void bindView(LinearLayout view, int position) {
            listEntry entry = getItem(position);
            view.setId((int) getItemId(position));

            TextView musicTitleTextView = (TextView) view.findViewById(R.id.musicTitle);
            musicTitleTextView.setText(entry.getMusicTitle());
        }
    }
}

