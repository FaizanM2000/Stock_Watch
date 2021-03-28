package com.example.stockwatch;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

public class StockNameLoaderRunnable extends AsyncTask<Void, Void, String> {


    /*
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
        handleResults(sb.toString());

    }

    private void handleResults(Object o) {
        if(s == null) {
            Log.d(TAG, "handleResults: Failure in data download");
            mainActivity.runOnUiThread(mainActivity::downloadFailed);
            return;
        }

        final ArrayList<Stock> stockArrayList = parseJSON(s);
        mainActivity.runOnUiThread(() -> {
            if(stockArrayList != null)
                Toast.makeText(mainActivity,"loaded"+stockArrayList.size()+"stocks", Toast.LENGTH_SHORT);
            mainActivity.updateData(stockArrayList);

        });

    }
    private ArrayList<Stock> parseJSON(String s){
        ArrayList<Stock> stocklist = new ArrayList<>();
        try{
            JSONArray jObjMain = new JSONArray(s);
            for(int i = 0; i<jObjMain.length();i++){
                JSONObject jStock = (JSONObject) jObjMain.get(i);
                String name = jStock.getString("name");
                String ticker = jStock.getString("symbol");




            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
*/

    private MainActivity mainActivity;
    private static final String DATA_URL = "https://api.iextrading.com/1.0/ref-data/symbols";

    public StockNameLoaderRunnable(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    protected String doInBackground(Void... voids) {
        Uri dataUri = Uri.parse(DATA_URL);
        String urlToUse = dataUri.toString();
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(urlToUse);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            InputStream is = conn.getInputStream();
            BufferedReader reader = new BufferedReader((new InputStreamReader(is)));
            String line;
            while ((line = reader.readLine()) != null) sb.append(line).append('\n');
        } catch (Exception e) {
            return null;
        }

        return sb.toString();

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        HashMap<String,String> symbolNameList = parseJSON(s);
        mainActivity.updateData(symbolNameList);
    }

    private HashMap<String,String> parseJSON(String s) {

        HashMap<String,String> nameSymbolMap = new HashMap<>();
        try {
            JSONArray jObjMain = new JSONArray(s);

            for (int i = 0; i < jObjMain.length(); i++) {
                JSONObject jSymbolName = (JSONObject) jObjMain.get(i);
                String symbol = jSymbolName.getString("symbol");
                String name = jSymbolName.getString("name");
                nameSymbolMap.put(symbol, name);
            }
            return nameSymbolMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }





}
