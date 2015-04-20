package com.example.mo.bandit_v1;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Mo on 21.10.2014.
 */
public class EventFragment extends Fragment {

    private MyItemAdapter myAdapter;

    int[] idEvents = {1,2,3,4};
    String[] eventNameArray;
    String[] eventGenreArray;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.event, container, false);

        Intent intent = getActivity().getIntent();
        final Data data = intent.getParcelableExtra("data");
        ArrayList<EventData> eventDatas = data.eventDatas;

        EventData[] eventData = eventDatas.toArray(new EventData[eventDatas.size()]);

        eventNameArray = new String[eventData.length];
        eventGenreArray = new String[eventData.length];

        for (int i = 0; i<eventDatas.size();i++){
            //EventData eventData = new EventData(idEvents[i]);
            eventNameArray[i] = eventData[i].eventName+": ";
            eventGenreArray[i] = eventData[i].eventGenre;
        }
        initDatensaetze();
        ListView eventListView = (ListView) view.findViewById(R.id.eventListView);
        myAdapter = new MyItemAdapter();
        eventListView.setAdapter(myAdapter);
        eventListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Datensatz gewaehlterDatensatz = datensaetze.get(position);
                int idEvent = idEvents[position];
                Intent intent = new Intent(getActivity(),EventActivity.class);
                intent.putExtra("idEvents",idEvents);
                startActivity(intent);
            }
        });

        Button createEventButton = (Button) view.findViewById(R.id.createEventButton);
        createEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),CreateEventActivity.class);
                intent.putExtra("data",data);
                startActivity(intent);
            }
        });

        return view;
    }

    private class Datensatz {
        public String name;  // besser setter und getter-Methoden schreiben, stört hier aber...
        public String genre; // die Umwandlung von Datum lasse ich weg - das ist ein anderes (großes) Problem
        public Datensatz(String name, String genre) {
            this.name = name;
            this.genre = genre;
        }
    }

    private ArrayList<Datensatz> datensaetze;

    private void initDatensaetze() {
        datensaetze = new ArrayList<Datensatz>();
        for (int i=0; i<eventNameArray.length; i++) {
            // hier aus Arrays auslesen, bei dir wahrscheinlich anders...
            Datensatz datensatz = new Datensatz(eventNameArray[i],eventGenreArray[i]);
            datensaetze.add(datensatz);
        }

    }

    class MyItemAdapter extends BaseAdapter {
        private final LayoutInflater mInflater;
        public MyItemAdapter() {
            mInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
            nameTextView.setText(datensatz.name);
        }
    }

    public void onActivityResult(int reqCode, int resCode, Intent data){
        if(resCode == Activity.RESULT_OK){
            if (reqCode == 1){

            }
        }
    }

}
