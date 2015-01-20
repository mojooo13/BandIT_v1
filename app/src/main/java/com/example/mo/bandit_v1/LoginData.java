package com.example.mo.bandit_v1;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Mo on 21.10.2014.
 */
public class LoginData {
    String email;
    String passwort;
    String jsonString="";

    public LoginData(String email,String passwort){
        this.email = email;
        this.passwort = passwort;

        JSONObject obj = new JSONObject();

        try {
            obj.put("email", email);
            obj.put("password", passwort);
            obj.put("command", "login");

            jsonString = ""+obj;
            System.out.println(jsonString);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public boolean login(){
        ServerCommunication serverCommunication = new ServerCommunication();
        String line = serverCommunication.communication(jsonString);
        System.out.println("+++++++++++++"+line);

        return false;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswort() {
        return passwort;
    }
}
