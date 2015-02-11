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
public class BandFragment extends Fragment {

    //BandData bandData = new BandData("ACDC", "Moritz Hauch", "Rock N Roll", "High Way To Hell", "Guitar");
    //String[] bandNameArray = {"ACDC:","CCR:","CC TOP:","ABBA:"};
    //String[] bandGenreArray = {"Hard-Rock","Rock","Country","Pop"};
    //int[] idPositionArray = {5,7,9,45};
    //int[] idBands = {1,2,3,4};
    String[] bandNameArray;
    String[] bandGenreArray;



    //final ProfilData profilData = new ProfilData(profilID);

    private MyItemAdapter myAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.band, container, false);

        int profilID = getActivity().getIntent().getExtras().getInt("profilID");
        final ProfilData profilData = new ProfilData(profilID);

        final int[] idBands = profilData.bandIDs;
        bandNameArray = new String[idBands.length];
        bandGenreArray = new String[idBands.length];

        for (int i = 0; i<idBands.length;i++){
            BandData bandData = new BandData(idBands[i]);
            bandNameArray[i] = bandData.bandName+": ";
            bandGenreArray[i] = bandData.bandGenre;
        }

        initDatensaetze();
        ListView bandListView = (ListView) view.findViewById(R.id.bandListView);
        myAdapter = new MyItemAdapter();
        bandListView.setAdapter(myAdapter);
        bandListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Datensatz gewaehlterDatensatz = datensaetze.get(position);
                int idBand = idBands[position];
                Intent intent = new Intent(getActivity(),BandActivity.class);
                intent.putExtra("bandID",idBand);
                startActivity(intent);
            }
        });

        Button createBandButton = (Button) view.findViewById(R.id.createBandBandButton);
        createBandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),CreateBandActivity.class);
                intent.putExtra("profilID",profilData.getId());
                startActivity(intent);
            }
        });

        return view;
    }

    private class Datensatz{
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
        for (int i=0; i<bandNameArray.length; i++) {
            // hier aus Arrays auslesen, bei dir wahrscheinlich anders...
            Datensatz datensatz = new Datensatz(bandNameArray[i],bandGenreArray[i]);
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
