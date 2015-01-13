package com.example.mo.bandit_v1;

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
        String a;
        try {
            obj.put("vorname", vorname);
            obj.put("nachname", nachname);
            obj.put("email", email);
            obj.put("passwort", passwort);
            obj.put("command", "signUp");

            //a = obj.getString("vorname");
            //System.out.println(a);

            jsonString = ""+obj;
            System.out.println(jsonString);


        }
        catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public boolean pushDataToServer(){
        boolean status;
        //push server -> return true/false
        //ServerCommunication serverCommunication = new ServerCommunication(jsonString);
        return true;
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
