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

import java.util.ArrayList;


public class MessageActivity extends Activity {

    String[] dates;
    String[] senderName;

    private MyItemAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        final int profilID = getIntent().getExtras().getInt("profilID");
        final NotificationData notificationData = new NotificationData(profilID);

        final int[] notificationIDs = notificationData.notificationIDs;
        dates = new String[notificationIDs.length];
        senderName = new String[notificationIDs.length];

        for (int i = 0; i<notificationIDs.length;i++){
            NotificationData n = new NotificationData(notificationIDs[i],1);
            dates[i] = n.date+": ";
            senderName[i] = n.sendername;
        }
        initDatensaetze();
        ListView notificationListView = (ListView) findViewById(R.id.notificationListView);

        myAdapter = new MyItemAdapter();
        notificationListView.setAdapter(myAdapter);

        notificationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Datensatz gewaehlterDatensatz = datensaetze.get(position);
                int notificationID = notificationIDs[position];
                Intent intent = new Intent(MessageActivity.this,MessageDetailActivity.class);
                intent.putExtra("notificationID",notificationID);
                startActivity(intent);
            }
        });
    }

    private class Datensatz {
        public String name;  // besser setter und getter-Methoden schreiben, stört hier aber...
        public String date; // die Umwandlung von Datum lasse ich weg - das ist ein anderes (großes) Problem
        public Datensatz(String name, String date) {
            this.name = name;
            this.date = date;
        }
    }

    private ArrayList<Datensatz> datensaetze;

    private void initDatensaetze() {
        datensaetze = new ArrayList<Datensatz>();
        for (int i=0; i<senderName.length; i++) {
            // hier aus Arrays auslesen, bei dir wahrscheinlich anders...
            Datensatz datensatz = new Datensatz(senderName[i],dates[i]);
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
            TextView datumTextView = (TextView) view.findViewById(R.id.name);
            TextView nameTextView = (TextView) view.findViewById(R.id.datum);
            datumTextView.setText(datensatz.date);
            nameTextView.setText(datensatz.name);
        }
    }
    public void onActivityResult(int reqCode, int resCode, Intent data){
        if(resCode == Activity.RESULT_OK){
            if (reqCode == 1){

            }
        }
    }

    //
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.message, menu);
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
