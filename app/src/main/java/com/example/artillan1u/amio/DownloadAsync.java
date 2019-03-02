package com.example.artillan1u.amio;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadAsync extends AsyncTask<URL, Integer, String>  {
    private Exception exception;
    private TextView valeur1;
    private TextView valeur2;

    DownloadAsync(TextView v1, TextView v2){//Context context, View rootView){
        this.valeur1 = v1;
        this.valeur2 = v2;
    }

    protected void onPreExecute(){
    }

    @Override
    protected String doInBackground(URL... urls) {

        InputStream stream = null;
        String result;

        try{

            URL url = new URL("http://iotlab.telecomnancy.eu/rest/data/1/light1/last");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            int responseCode = urlConnection.getResponseCode();

            stream = urlConnection.getInputStream();


            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                result = stringBuilder.toString();
                Log.d("truc1", result);
            }
            finally{
                urlConnection.disconnect();
            }
        }
        catch(Exception e){
            Log.e("ERROR", e.getMessage(), e);
            return "";
        }
        return result;
    }

    protected void onPostExecute(String result){
        JSONObject jObject, jObject2, capteur1, capteur2;

        try{
            jObject = new JSONObject(result);
            JSONArray dataString = jObject.getJSONArray("data");
            capteur1 = dataString.getJSONObject(0);
            capteur2 = dataString.getJSONObject(1);
            Log.d("truc", capteur1.getString("value"));
            Log.d("truc", capteur2.getString("value"));

            valeur1.setText(capteur1.getString("value"));
            valeur2.setText(capteur2.getString("value"));


        }
        catch(Exception e){
            Log.e("ERROR", e.getMessage(), e);
        }
    }

    protected void onProgressUpdate(){}

}
