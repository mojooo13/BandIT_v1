package com.example.mo.bandit_v1;

import android.app.Activity;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Mo on 21.10.2014.
 */
public class SignUpData{
    String vorname;
    String nachname;
    String email;
    String passwort;
    String jsonString;

    public SignUpData(){
    }

    public SignUpData(String vorname,String nachname,String email,String passwort){
        this.vorname = vorname;
        this.nachname = nachname;
        this.email = email;
        this.passwort = passwort;

        JSONObject obj = new JSONObject();

        try {
            obj.put("firstName", vorname);
            obj.put("secondName", nachname);
            obj.put("email", email);
            obj.put("password", passwort);
            obj.put("command", "createAccount");

            //a = obj.getString("vorname");
            //System.out.println(a);

            jsonString = ""+obj;
            System.out.println(jsonString);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public boolean pushDataToServer(Activity activity){
        //push server -> return true/false
        ServerCommunication serverCommunication = new ServerCommunication();
        String line = serverCommunication.communication(jsonString);
        System.out.println("##############"+line);
        line = line.substring(line.indexOf("$")+1);
        try {
            JSONObject jsonObject = new JSONObject(line);
            String status = jsonObject.getString("status");
            if(status.equals("true")) {
                return true;
            }
            else if(status.equals("emailexists")){
                TextView errorSignUpTextView = (TextView) activity.findViewById(R.id.errorSignUpTextView);
                errorSignUpTextView.setText("Email already exists.");
            }
            else{
                TextView errorSignUpTextView = (TextView) activity.findViewById(R.id.errorSignUpTextView);
                errorSignUpTextView.setText(status);
            }

        }catch (Exception e){

        }

        return false;

    }
    public String getVorname() {
        return vorname;
    }
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }
    public String getNachname() {
        return nachname;
    }
    public void setNachname(String nachname) {
        this.nachname = nachname;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPasswort() {
        return passwort;
    }
    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }
}
