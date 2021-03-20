package com.example.stockwatch;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class StockLoaderRunnable {

    private static final String TAG = "StockLoaderRunnable";
    private final MainActivity mainActivity;
    private static final String DATA_URL = " https://api.iextrading.com/1.0/ref-data/symbols";

    StockLoaderRunnable(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    public void run(){
        Uri dataUri = Uri.parse(DATA_URL);
        String urlToUse = dataUri.toString();
        Log.d(TAG, "run:"+urlToUse);

        StringBuilder sb = new StringBuilder();
        try{
            URL url = new URL(urlToUse);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            
            if(conn.getResponseCode() != HttpsURLConnection.HTTP_OK){
                Log.d(TAG, "run: HTTP ResponseCode not OK"+conn.getResponseCode());
                handleResults(null);
                return;
            }

            InputStream is = conn.getInputStream();
            BufferedReader reader = new BufferedReader((new InputStreamReader(is)));

            String line;
            while((line = reader.readLine())!= null){
                sb.append(line).append('\n');
            }

            Log.d(TAG, "run: "+sb.toString());



        }








        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void handleResults(Object o) {
    }


}
