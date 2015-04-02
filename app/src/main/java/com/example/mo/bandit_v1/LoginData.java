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
    String line1;
    String line2;
    String line3;
    String line4;


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

    public String login(){
        ServerCommunication serverCommunication = new ServerCommunication();

        //String line = "Input:{\"command\":\"login\",\"password\":\"b\",\"email\":\"b\"}<br>command:login{\"command\":\"login\",\"id\":\"2\",\"status\":\"true\"}</br>Antwort${\"command\":\"getprofiledata\",\"profileID\":\"2\",\"password\":\"b\",\"firstName\":\"eda \",\"secondName\":\"b\",\"mobileNumber\":\"\",\"email\":\"b\",\"genre\":\"\",\"adress\":\"\",\"instrument\":[\"nothing\"],\"status\":\"true\"}%{\"command\":\"getBandList\",\"bands\":[{\"bandName\":\"ABBA\",\"genre\":\"Pop\",\"id\":\"2\"},{\"bandName\":\"moos band\",\"genre\":\"Rock\",\"id\":\"6\"}],\"status\":\"true\"}&{\"command\":\"getEventList\",\"events\":[{\"eventID\":\"1\",\"eventName\":\"NOVA-ROCK\",\"eventGenre\":\"Rock\"}],\"status\":\"true\"}!{\"command\":\"getNotification\",\"bandRequest\":[{\"bandName\":\"moos band\",\"bandGenre\":\"Rock\",\"text\":\"Do u want to join our band?\",\"bandId\":\"6\",\"requestId\":\"6\"}],\"eventRequest\":[{\"requestId\":\"1\",\"requestSenderId\":\"2\",\"requestFirstName\":\"Moritz\",\"requestSecondName\":\"Hauch\",\"bandname\":\"ABBA\",\"eventID\":\"1\",\"eventName\":\"NOVA-ROCK\",\"eventGenre\":\"Rock\",\"eventLocation\":\"HTL Braunau\",\"eventDate\":\"10.03.2015\",\"eventTime\":\"16:00:00\",\"text\":\"do u want to join our event on march 15th?\"}],\"status\":\"true\"}\n";
        String line = serverCommunication.communication(jsonString);//Serverantwort

        if(line.isEmpty())
            return "nocon";

        System.out.println("+++++++++++++"+line);
        line1 = line.substring(line.indexOf("$")+1,line.indexOf("%"));
        System.out.println("Line1: "+line1);
        line2 = line.substring(line.indexOf("%")+1,line.indexOf("&"));
        System.out.println("Line2: "+line2);
        line3 = line.substring(line.indexOf("&")+1,line.indexOf("!"));
        System.out.println("Line3: "+line3);
        line4 = line.substring(line.indexOf("!")+1,line.length());
        System.out.println("Line4: "+line4);

        if(line2.isEmpty())//wenn fehlgeschlagen
            return "nopassword";
        return "true";
    }

    public String getEmail() {
        return email;
    }

    public String getPasswort() {
        return passwort;
    }
}
