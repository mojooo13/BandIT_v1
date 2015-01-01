package com.example.mo.bandit_v1;

import android.content.Context;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Mo on 15.12.2014.
 */
public class Server {

    String jsonString;
    private static Context context;

    public void Server (String jsonString,Context c){
        context = c;


        this.jsonString = jsonString;

        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(jsonString);

        try {
            HttpResponse response = httpclient.execute(httpget);
            if(response != null) {
                String line = "";
                InputStream inputstream = response.getEntity().getContent();
                line = convertStreamToString(inputstream);
                Toast.makeText(context, line, Toast.LENGTH_LONG).show();
                Toast.makeText(context, "Error",Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, "Unable to complete your request", Toast.LENGTH_LONG).show();
            }
        } catch (ClientProtocolException e) {
            Toast.makeText(context, "Caught ClientProtocolException", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(context, "Caught IOException", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(context, "Caught Exception", Toast.LENGTH_SHORT).show();
        }
    }

    private String convertStreamToString(InputStream is) {
        String line = "";
        StringBuilder total = new StringBuilder();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        try {
            while ((line = rd.readLine()) != null) {
                total.append(line);
            }
        } catch (Exception e) {
            Toast.makeText(context, "Stream Exception", Toast.LENGTH_SHORT).show();
        }
        return total.toString();
    }
}
