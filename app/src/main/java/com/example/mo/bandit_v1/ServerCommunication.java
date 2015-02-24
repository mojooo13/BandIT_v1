package com.example.mo.bandit_v1;

import android.content.Context;
import android.os.StrictMode;
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
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Mo on 15.12.2014.
 */
public class ServerCommunication {

    private static Context context;

    public String communication (String json){
        //context = c;

        String l="";

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        String query = null;
        try {
            query = URLEncoder.encode(json, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet("http://192.168.88.47/?json="+query);
        //192.168.88.47 => Zentrum der Macht
        try {
            HttpResponse response = httpclient.execute(httpget);
            if(response != null) {
                String line = "";
                InputStream inputstream = response.getEntity().getContent();
                line = convertStreamToString(inputstream);
                l=line;
                //Toast.makeText(context, line, Toast.LENGTH_LONG).show();
                //Toast.makeText(context, "Error",Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, "Unable to complete your request", Toast.LENGTH_LONG).show();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            //Toast.makeText(context, "Caught ClientProtocolException", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            //Toast.makeText(context, "Caught IOException", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            //Toast.makeText(context, "Caught Exception", Toast.LENGTH_SHORT).show();
        }
        return l;
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
            e.printStackTrace();
            Toast.makeText(context, "Stream Exception", Toast.LENGTH_SHORT).show();
        }
        return total.toString();
    }
}
