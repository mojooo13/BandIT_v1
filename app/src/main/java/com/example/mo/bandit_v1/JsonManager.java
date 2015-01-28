package com.example.mo.bandit_v1;

/**
 * Created by Mo on 20.01.2015.
 */
public class JsonManager {
    String jsonString;

    public String login(String line){
        line = line.substring(line.indexOf("$")+1);
        return line;
    }

    public String signUp(String line){
        return jsonString;
    }
}
