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


public class InviteToEventActivity extends Activity {

    ArrayList<String> events = new ArrayList<String>();
    ArrayList<String> genres = new ArrayList<String>();
    ArrayList<Integer> ids = new ArrayList<Integer>();
    ArrayList<String> dates = new ArrayList<String>();

    private MyItemAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_to_event);

        Intent intent = getIntent();

        final Data data = intent.getParcelableExtra("data");

        int profilID = data.profilData.profilID;
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("command","getEventList");
            jsonObject.put("id",profilID);

            ServerCommunication serverCommunication = new ServerCommunication();

            String eventDatasJSONString = serverCommunication.communication(jsonObject.toString());
            System.out.println(eventDatasJSONString);
            eventDatasJSONString = eventDatasJSONString.substring(eventDatasJSONString.indexOf("$")+1);
            System.out.println(eventDatasJSONString);
            JSONObject bandDatasJSONObject = new JSONObject(eventDatasJSONString);
            final JSONArray jsonArray = bandDatasJSONObject.getJSONArray("events");
            for(int i = 0; i<jsonArray.length();i++){
                JSONObject  jsonObjectTemp = jsonArray.getJSONObject(i);
                String eventname = jsonObjectTemp.getString("eventName");
                String genre = jsonObjectTemp.getString("eventGenre");
                int id = jsonObjectTemp.getInt("eventID");
                String date = jsonObjectTemp.getString("eventDate");
                System.out.println(eventname+genre);
                events.add(eventname);
                genres.add(genre);
                ids.add(id);
                dates.add(date);
            }
            initDatensaetze();
            ListView bandListView = (ListView) findViewById(R.id.chooseEventInviteToBandListView);

            myAdapter = new MyItemAdapter();
            bandListView.setAdapter(myAdapter);

            bandListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Datensatz gewaehlterDatensatz = datensaetze.get(position);
                    int idEvent = gewaehlterDatensatz.id;
                    String date = gewaehlterDatensatz.date;

                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("data",data);
                    returnIntent.putExtra("idEvent",idEvent);
                    returnIntent.putExtra("date",date);
                    setResult(RESULT_OK,returnIntent);
                    //finish();
                    String email = data.profilData.profilEmail;
                    String passwort = data.profilData.passwort;
                    LoginData loginData = new LoginData(email,passwort);
                    if(loginData.login().equals("true")){
                        Data dataUpdate = new Data(loginData.line1, loginData.line2, loginData.line3, loginData.line4);

                        //finish();
                        Intent intent = new Intent(InviteToEventActivity.this,MainMenuActivity.class);
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
        public String eventname;  // besser setter und getter-Methoden schreiben, stört hier aber...
        public String genre; // die Umwandlung von Datum lasse ich weg - das ist ein anderes (großes) Problem
        public int id;
        public String date;
        public Datensatz(String eventname, String genre,int id,String date) {
            this.eventname = eventname+": ";
            this.genre = genre;
            this.id = id;
            this.date = date;
        }
    }
    private ArrayList<Datensatz> datensaetze;

    private void initDatensaetze() {
        datensaetze = new ArrayList<Datensatz>();
        for (int i=0; i<events.size(); i++) {
            Datensatz datensatz = new Datensatz(events.get(i),genres.get(i),ids.get(i),dates.get(i));
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
            nameTextView.setText(datensatz.eventname);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.invite_to_event, menu);
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
