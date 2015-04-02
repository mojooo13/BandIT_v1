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

    ArrayList<String> field1 = new ArrayList<String>();
    ArrayList<String> field2 = new ArrayList<String>();

    private MyItemAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        Intent intent = getIntent();
        final Data data = intent.getParcelableExtra("data");

        for (int i = 0; i<data.notificationData.bandRequests.size();i++){
            field1.add("B-Request: ");
            field2.add(data.notificationData.bandRequests.get(i).bandName);
        }
        for (int i = 0; i<data.notificationData.eventReuqests.size();i++){
            field1.add("E-Request: ");
            field2.add(data.notificationData.eventReuqests.get(i).eventName);
        }

        initDatensaetze();
        ListView notificationListView = (ListView) findViewById(R.id.notificationListView);

        myAdapter = new MyItemAdapter();
        notificationListView.setAdapter(myAdapter);

        notificationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Datensatz gewaehlterDatensatz = datensaetze.get(position);
                //int notificationID = notificationIDs[position];
                Intent intent = new Intent(MessageActivity.this,MessageDetailActivity.class);
                if(gewaehlterDatensatz.typ.equals("B-Request: ")){
                    intent.putExtra("bandRequest",data.notificationData.bandRequests.get(position));//-> final data
                    intent.putExtra("status","band");
                }
                else{
                    intent.putExtra("eventRequest",data.notificationData.eventReuqests.get(position-data.notificationData.bandRequests.size()));
                    intent.putExtra("status","event");
                }


                startActivity(intent);
            }
        });
    }

    private class Datensatz {
        public String typ;  // besser setter und getter-Methoden schreiben, stört hier aber...
        public String name; // die Umwandlung von Datum lasse ich weg - das ist ein anderes (großes) Problem
        public Datensatz(String typ, String name) {
            this.typ = typ;
            this.name = name;
        }
    }

    private ArrayList<Datensatz> datensaetze;

    private void initDatensaetze() {
        datensaetze = new ArrayList<Datensatz>();
        for (int i=0; i<field1.size(); i++) {
            // hier aus Arrays auslesen, bei dir wahrscheinlich anders...
            Datensatz datensatz = new Datensatz(field1.get(i),field2.get(i));
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
            datumTextView.setText(datensatz.typ);
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
