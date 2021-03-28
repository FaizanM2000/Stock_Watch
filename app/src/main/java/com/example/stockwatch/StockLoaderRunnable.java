package com.example.stockwatch;

import android.net.Uri;
import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class StockLoaderRunnable extends AsyncTask<String,Void, String> {
    private MainActivity mainActivity;
    private static final String DATA_URL = "https://cloud.iexapis.com/stable/stock/";
    public StockLoaderRunnable(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
    private String API_KEY = "pk_404baacdfcbc4fc8a3f9826f077e127c";
    @Override
    protected String doInBackground(String... symbol) {

        String API_URL = DATA_URL + symbol[0] + "/quote?token="+API_KEY;
        System.out.println("API_URL: "+ API_URL);
        Uri dataUri = Uri.parse(API_URL);
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
            System.out.println("null was returned");
            return null;

        }
        return sb.toString();

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Stock newStock = parseJSON(s);
        mainActivity.updateStock(newStock);

    }

    private Stock parseJSON(String s) {
        Stock tempStock = new Stock();
        try {
            JSONObject jObjMain = new JSONObject(s);

            String symbol = jObjMain.getString("symbol");
            String name = jObjMain.getString("companyName");
            Double price = jObjMain.getDouble("latestPrice");
            Double priceChange = jObjMain.getDouble("change");
            Double changePercentage = jObjMain.getDouble("changePercent");

            tempStock.setName(name);
            tempStock.setTicker(symbol);
            tempStock.setPrice(price);
            tempStock.setNetchange(priceChange);
            tempStock.setPercentchange(changePercentage);
            return tempStock;
        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;
    }







}
