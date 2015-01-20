package com.example.mo.bandit_v1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Button signUpButton = (Button) findViewById(R.id.signUpLogInButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
            }
        });

        Button loginButton = (Button) findViewById(R.id.loginLogInButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText emailLogInEditText = (EditText) findViewById(R.id.emailLogInEditText);
                EditText passwortLogInEditText = (EditText) findViewById(R.id.passwortLogInEditText);
                String email = emailLogInEditText.getText().toString();
                String passwort = passwortLogInEditText.getText().toString();

                Intent intent = new Intent(LoginActivity.this,MainMenuActivity.class);
                LoginData loginData = new LoginData(email,passwort);
                if(loginData.login()){
                    //intent.putExtra("profilID",profilData.getId());
                    startActivity(intent);
                }
                else{
                    TextView errorLogInTextView = (TextView) findViewById(R.id.errorLogInTextView);
                    errorLogInTextView.setText("Email and/or Password is incorrect");
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
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
