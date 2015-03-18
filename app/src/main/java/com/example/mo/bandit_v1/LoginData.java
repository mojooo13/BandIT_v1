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

        //String line = "{\"command\":\"login\",\"password\":\"a\",\"email\":\"a\"}<br>command:login{\"command\":\"login\",\"id\":\"1\",\"status\":\"true\"}</br>Antwort${\"command\":\"getprofiledata\",\"profileID\":\"1\",\"password\":\"a\",\"firstName\":\"Mo\",\"secondName\":\"Hauch\",\"mobileNumber\":\"0664 1818191920\",\"email\":\"a\",\"genre\":\"Rock\",\"adress\":\"Steingasse 7\",\"instrument\":[\"gitarre\",\"trommel\"],\"status\":\"true\"}%{\"command\":\"getBandList\",\"bands\":[{\"bandName\":\"acdc\",\"genre\":\"x\",\"id\":\"1\"},{\"bandName\":\"abbc\",\"genre\":\"x\",\"id\":\"2\"},{\"bandName\":\"aba\",\"genre\":\"x\",\"id\":\"3\"}],\"status\":\"true\"}&{\"command\":\"getEventList\",\"events\":[{\"eventID\":\"14\",\"eventName\":\"theevent11\",\"eventGenre\":\"x\"},{\"eventID\":\"15\",\"eventName\":\"Haaaa\",\"eventGenre\":\"Rock\"}],\"status\":\"true\"}!{\"command\":\"getNotification\",\"bandRequest\":[{\"bandName\":\"aba\",\"bandGenre\":\"x\",\"text\":\"das ist der 2te wert\",\"tableId\":\"3\"}],\"eventRequest\":{\"requestId\":\"3\",\"requestSenderId\":\"1\",\"requestFirstName\":\"c\",\"requestSecondName\":\"c\",\"eventID\":\"15\",\"eventName\":\"Haaaa\",\"eventGenre\":\"Rock\",\"eventLocation\":\"Braunau\",\"eventDate\":\"12.05.2015\",\"eventTime\":\"00:00:00\",\"text\":\"das ist ein sinnvoller text\"},\"status\":\"true\"}";
        //String line = "{\"command\":\"login\",\"password\":\"a\",\"email\":\"a\"}<br>command:login{\"command\":\"login\",\"id\":\"1\",\"status\":\"true\"}</br>Antwort${\"command\":\"getprofiledata\",\"password\":\"a\",\"firstName\":\"Mo\",\"secondName\":\"Hauch\",\"mobileNumber\":\"0664 1818191920\",\"email\":\"a\",\"genre\":\"Rock\",\"adress\":\"Steingasse 7\",\"instrument\":[\"gitarre\",\"trommel\"],\"status\":\"true\"}%{\"command\":\"getBandList\",\"bands\":[{\"bandName\":\"acdc\",\"genre\":\"x\",\"id\":\"1\"},{\"bandName\":\"abbc\",\"genre\":\"x\",\"id\":\"2\"},{\"bandName\":\"aba\",\"genre\":\"x\",\"id\":\"3\"}],\"status\":\"true\"}&{\"command\":\"getEventList\",\"events\":[{\"eventID\":\"14\",\"eventName\":\"theevent11\",\"eventGenre\":\"x\"},{\"eventID\":\"15\",\"eventName\":\"Haaaa\",\"eventGenre\":\"Rock\"}],\"status\":\"true\"}!{\"command\":\"getNotification\",\"bandRequest\":[{\"bandName\":\"aba\",\"tableId\":\"10\"}],\"eventRequest\":{\"requestId\":\"12\",\"requestFirstName\":\"c\",\"requestSecondName\":\"c\",\"text\":\"dadadam\"},\"status\":\"true\"}\n";
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
