package com.example.mo.bandit_v1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class InviteToBandActivity extends Activity {

    ArrayList<String> bands = new ArrayList<String>();
    ArrayList<String> genres = new ArrayList<String>();
    ArrayList<Integer> ids = new ArrayList<Integer>();

    private MyItemAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_to_band);

        Intent intent = getIntent();
        final Data data = intent.getParcelableExtra("data");
        int profilID = intent.getExtras().getInt("profilID");
        JSONObject  jsonObject = new JSONObject();
        try{
            jsonObject.put("command","getBandList");
            jsonObject.put("id",profilID);
            ServerCommunication serverCommunication = new ServerCommunication();
            String bandDatasJSONString = serverCommunication.communication(jsonObject.toString());
            System.out.println(bandDatasJSONString);
            bandDatasJSONString = bandDatasJSONString.substring(bandDatasJSONString.indexOf("$")+1);
            System.out.println(bandDatasJSONString);
            JSONObject bandDatasJSONObject = new JSONObject(bandDatasJSONString);
            JSONArray jsonArray = bandDatasJSONObject.getJSONArray("bands");
            for(int i = 0; i<jsonArray.length();i++){
                JSONObject  jsonObjectTemp = jsonArray.getJSONObject(i);
                String bandname = jsonObjectTemp.getString("bandName");
                String genre = jsonObjectTemp.getString("genre");
                int id = jsonObjectTemp.getInt("id");
                System.out.println(bandname+genre);
                bands.add(bandname);
                genres.add(genre);
                ids.add(id);
            }
            initDatensaetze();
            ListView bandListView = (ListView) findViewById(R.id.chooseBandInviteToBandListView);

            myAdapter = new MyItemAdapter();
            bandListView.setAdapter(myAdapter);

            bandListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Datensatz gewaehlterDatensatz = datensaetze.get(position);
                    int idBand = gewaehlterDatensatz.id;

                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("idBand",idBand);
                    setResult(RESULT_OK,returnIntent);
                    //finish();
                    String email = data.profilData.profilEmail;
                    String passwort = data.profilData.passwort;
                    LoginData loginData = new LoginData(email,passwort);
                    if(loginData.login().equals("true")){
                        Data dataUpdate = new Data(loginData.line1, loginData.line2, loginData.line3, loginData.line4);

                        //finish();
                        Intent intent = new Intent(InviteToBandActivity.this,MainMenuActivity.class);
                        intent.putExtra("data",dataUpdate);
                        intent.putExtra("profilID",5);
                        startActivity(intent);
                    }

                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private class Datensatz{
        public String bandname;  // besser setter und getter-Methoden schreiben, stört hier aber...
        public String genre; // die Umwandlung von Datum lasse ich weg - das ist ein anderes (großes) Problem
        public int id;
        public Datensatz(String bandnamename, String genre,int id) {
            this.bandname = bandnamename+": ";
            this.genre = genre;
            this.id = id;
        }
    }
    private ArrayList<Datensatz> datensaetze;

    private void initDatensaetze() {
        datensaetze = new ArrayList<Datensatz>();
        for (int i=0; i<bands.size(); i++) {
            Datensatz datensatz = new Datensatz(bands.get(i),genres.get(i),ids.get(i));
            datensaetze.add(datensatz);
        }
    }
    class MyItemAdapter extends BaseAdapter {
        private final LayoutInflater mInflater;
        public MyItemAdapter() {
            mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        public int getCount() {
            return datensaetze.size();
        }
        public Datensatz getItem(int position) {
            return datensaetze.get(position);
        }
        public long getItemId(int position) {
            return (long) position;
        }
        public View getView(int position, View convertView, ViewGroup parent) {
            LinearLayout itemView = (LinearLayout) mInflater.inflate(R.layout.bandlistitem, parent, false);
            bindView(itemView, position);
            return itemView;
        }
        private void bindView(LinearLayout view, int position) {
            Datensatz datensatz = getItem(position);
            view.setId((int) getItemId(position));
            TextView datumTextView = (TextView) view.findViewById(R.id.datum);
            TextView nameTextView = (TextView) view.findViewById(R.id.name);
            datumTextView.setText(datensatz.genre);
            nameTextView.setText(datensatz.bandname);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.invite_to_band, menu);
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


