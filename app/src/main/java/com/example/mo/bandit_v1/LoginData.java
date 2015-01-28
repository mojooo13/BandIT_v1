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

        String line = "{\"command\":\"login\",\"password\":\"a\",\"email\":\"a\"}<br>command:login<br /><b>Notice</b>:  Undefined variable: profileSecondName in <b>C:\\xampp\\htdocs\\resources\\library\\notification.class.php</b> on line <b>65</b><br /><br /><b>Notice</b>:  Undefined variable: profileFirstName in <b>C:\\xampp\\htdocs\\resources\\library\\notification.class.php</b> on line <b>65</b><br /></br>Antwort${\"command\":\"getprofiledata\",\"firstName\":\"Mo\",\"secondName\":\"Hauch\",\"mobileNumber\":\"0664 1818191920\",\"email\":\"a\",\"genre\":\"Rock\",\"adress\":\"Steingasse 7\",\"instrument\":[\"gitarre\",\"trommel\"],\"status\":\"true\"}%{\"command\":\"getBandList\",\"bands\":[{\"bandName\":\"acdc\",\"genre\":\"x\",\"id\":\"1\"},{\"bandName\":\"abbc\",\"genre\":\"x\",\"id\":\"2\"},{\"bandName\":\"aba\",\"genre\":\"x\",\"id\":\"3\"}],\"status\":\"true\"}&{\"command\":\"getNotification\",\"bandRequest\":[{\"bandName\":\"aba\",\"tableId\":\"10\"}],\"eventRequest\":[{\"profileName\":\"MoHauch\",\"text\":\"This is working correclty\",\"date\":\"2010-10-10\",\"requestId\":\"9\"},{\"profileName\":\"\",\"text\":\"dadadam\",\"date\":\"2016-11-13\",\"requestId\":\"12\"},{\"profileName\":\"\",\"text\":\"dadadam\",\"date\":\"2016-11-13\",\"requestId\":\"12\"}],\"status\":\"true\"}%&";

        //String line = serverCommunication.communication(jsonString);//Serverantwort

        System.out.println("+++++++++++++"+line);
        line1 = line.substring(line.indexOf("$")+1,line.indexOf("%"));
        System.out.println("Line1: "+line1);
        line2 = line.substring(line.indexOf("%")+1,line.indexOf("&"));
        System.out.println("Line2: "+line2);
        line3 = line.substring(line.indexOf("&")+1,line.length()-2);
        System.out.println("Line3: "+line3);

        System.out.println(line2.isEmpty());
        if(line2.isEmpty())//wenn fehlgeschlagen
            return false;

        return true;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswort() {
        return passwort;
    }
}
