package com.example.mo.bandit_v1;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainMenuActivity extends Activity {

    ActionBar.Tab tab1, tab2, tab3;
    Fragment fragmentTab1 = new ProfilFragment();
    Fragment fragmentTab2 = new BandFragment();
    Fragment fragmentTab3 = new EventFragment();
    Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Intent intent = getIntent();
        data = intent.getParcelableExtra("data");

        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        tab1 = actionBar.newTab().setText("Profil");
        tab2 = actionBar.newTab().setText("Band");
        tab3 = actionBar.newTab().setText("Event");

        tab1.setTabListener(new MyTabListener(fragmentTab1));
        tab2.setTabListener(new MyTabListener(fragmentTab2));
        tab3.setTabListener(new MyTabListener(fragmentTab3));

        actionBar.addTab(tab1);
        actionBar.addTab(tab2);
        actionBar.addTab(tab3);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int profilID = getIntent().getExtras().getInt("profilID");

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        if(id == R.id.action_logout){
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("EXIT", true);
            startActivity(intent);
            //finish();
        }
        if(id == R.id.action_search){
            Intent intent = new Intent(MainMenuActivity.this,SearchActivity.class);
            intent.putExtra("profilID",profilID);
            intent.putExtra("data",data);
            startActivity(intent);
        }
        if(id == R.id.action_message){
            Intent intent = new Intent(MainMenuActivity.this,MessageActivity.class);

            intent.putExtra("data",data);
            intent.putExtra("profilID",profilID);
            startActivity(intent);        }

        return super.onOptionsItemSelected(item);
    }
}
