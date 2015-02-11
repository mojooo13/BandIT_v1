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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class SearchActivity extends Activity {

    private MyItemAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_search);
        setContentView(R.layout.searchlistitem);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Bundle extras = getIntent().getExtras();

        String searchName;
        final String searchGenre;
        final String searchType;



        Button searchProfileButton = (Button) findViewById(R.id.searchNameButton);
        searchProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new newSearchActivity("profile");
            }
        });
        Button searchBandButton = (Button) findViewById(R.id.searchBandButton);
        searchBandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new newSearchActivity("band");
            }
        });
        Button searchEventButton = (Button) findViewById(R.id.searchEventButton);
        searchEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new newSearchActivity("event");
            }
        });
        Button searchMusicButton = (Button) findViewById(R.id.searchMusicButton);
        searchMusicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new newSearchActivity("music");
            }
        });


        if (extras != null) {
            searchName = extras.getString("searchName");
            searchGenre = extras.getString("searchGenre");
            searchType = extras.getString("searchType");

            if(searchName != null && searchGenre != null && searchType != null) {


                initListDataTable(searchName, searchGenre, searchType);


                ListView searchListView = (ListView) findViewById(R.id.listResult);
                searchListView.setVisibility(View.VISIBLE);
                TextView resultEditText = (TextView) findViewById(R.id.result);
                resultEditText.setVisibility(View.VISIBLE);

                myAdapter = new MyItemAdapter();
                searchListView.setAdapter(myAdapter);

                searchListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        listEntry chosenEntry = listDataTable.get(position);
                        int intentId = chosenEntry.getId();

                        if(searchType.equals("profile")){
                                Intent intent = new Intent(SearchActivity.this,ProfileActivity.class);
                                System.out.println("id:"+intentId);
                                intent.putExtra("id",intentId);
                                startActivity(intent);
                        }
                    }
                });
            }
        }
    }

    private class listEntry{
        private String name;
        private String genre;
        private int id;

        public String getName() {
            return name;
        }
        public String getGenre() {
            return genre;
        }
        public int getId() {
            return id;
        }

        private listEntry(String name, String genre, int id) {
            this.name = name;
            this.genre = genre;
            this.id = id;
        }
    }

    private ArrayList<listEntry> listDataTable;

    private void initListDataTable(String name, String genre, String type) {
        listDataTable = new ArrayList<listEntry>();
        ServerCommunication con = new ServerCommunication();

        JSONObject jsonStringObject = new JSONObject();
        try {
            jsonStringObject.put("type",type);
            jsonStringObject.put("genre",genre);
            jsonStringObject.put("name",name);
            jsonStringObject.put("command","searchDataList");

            String serverAnswer =  con.communication(jsonStringObject.toString());
            String jsonString = serverAnswer.substring(serverAnswer.indexOf("$")+1);

            jsonStringObject = new JSONObject(jsonString);
            System.out.println(jsonStringObject.toString());

            if(jsonStringObject.getString("command").equals("searchDataList") && jsonStringObject.getString("status").equals("true")){
                JSONArray jsonStringArray = jsonStringObject.getJSONArray("list");

                for (int i=0; i<jsonStringArray.length(); i++) {
                    jsonStringObject = new JSONObject(jsonStringArray.getString(i));
                    listEntry newEntry = new listEntry(jsonStringObject.getString("name"),jsonStringObject.getString("genre"),jsonStringObject.getInt("id"));
                    listDataTable.add(newEntry);


                }
            }


        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    class MyItemAdapter extends BaseAdapter {
        private final LayoutInflater mInflater;
        public MyItemAdapter() {
            mInflater = (LayoutInflater) SearchActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
            LinearLayout itemView = (LinearLayout) mInflater.inflate(R.layout.searchlistitem, parent, false);
            bindView(itemView, position);
            return itemView;
        }
        private void bindView(LinearLayout view, int position) {
            listEntry entry = getItem(position);
            view.setId((int) getItemId(position));

            TextView searchNameTextView = (TextView) view.findViewById(R.id.searchName);
            TextView searchGenreTextView = (TextView) view.findViewById(R.id.searchGenre);

            searchNameTextView.setText(entry.getName());
            searchGenreTextView.setText(entry.getGenre());
        }
    }

    private class newSearchActivity{
        Intent nextIntent = new Intent(SearchActivity.this, SearchActivity.class);
        String searchName = "";
        String searchGenre = "";

        newSearchActivity(String searchCondition){
            EditText searchNameEditText = (EditText) findViewById(R.id.searchName);
            EditText searchGenreEditText = (EditText) findViewById(R.id.searchGenre);

            searchName = searchNameEditText.getText().toString();
            searchGenre = searchGenreEditText.getText().toString();

            nextIntent.putExtra("searchType",searchCondition);
            nextIntent.putExtra("searchName",searchName);
            nextIntent.putExtra("searchGenre",searchGenre);

            startActivity(nextIntent);

            finish();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search, menu);
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
