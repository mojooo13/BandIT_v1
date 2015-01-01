package com.example.mo.bandit_v1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MessageDetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_detail);

        int notificationID = getIntent().getExtras().getInt("notificationID");
        final NotificationData notificationData = new NotificationData(notificationID,1);
        String date = notificationData.date;
        String message = notificationData.message;
        String sendername = notificationData.sendername;

        TextView dateNotificationDetailActivityTextView = (TextView) findViewById(R.id.dateNotificationDetailActivityTextView);
        TextView messageNotificationDetailActivityTextView = (TextView) findViewById(R.id.messageNotificationDetailActivityTextView);
        TextView sendernameNotificationDetailActivityTextView = (TextView) findViewById(R.id.sendernameNotificationDetailActivityTextView);

        dateNotificationDetailActivityTextView.setText(date);
        messageNotificationDetailActivityTextView.setText(message);
        sendernameNotificationDetailActivityTextView.setText(sendername);

        Button acceptMessageDetailActivityButton = (Button) findViewById(R.id.acceptMessageDetailActivityButton);
        Button declineMessageDetailActivityButton = (Button) findViewById(R.id.declineMessageDetailActivityButton);

        acceptMessageDetailActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationData.setStatus(1);
                finish();
            }
        });

        declineMessageDetailActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationData.setStatus(-1);
                finish();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.message_detail, menu);
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
