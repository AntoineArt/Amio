package com.example.artillan1u.amio;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
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
import java.util.Date;

public class DownloadAsync extends AsyncTask<URL, Integer, String>  {
    private Exception exception;
    private TextView valeur1;
    private TextView valeur2;
    private TextView valeur3;
    private Context mContext;

    DownloadAsync(TextView v1, TextView v2, TextView v3, Context c){//Context context, View rootView){
        this.valeur1 = v1;
        this.valeur2 = v2;
        this.valeur3 = v3;
        this.mContext = c;
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

            Double val1 = Double.parseDouble(capteur1.getString("value"));
            Double val2 = Double.parseDouble(capteur2.getString("value"));

            String ts1, ts2;
            ts1 = capteur1.getString("timestamp");
            ts2 = capteur1.getString("timestamp");

            Date date1 = new Date(Long.parseLong(ts1));
            Date date2 = new Date(Long.parseLong(ts2));

            ts1 = date1.toString();
            ts2 = date2.toString();

            ts1 = ts1.split(" ")[3];
            int heure1 = Integer.parseInt(ts1.split(":")[0]);

            ts2 = ts2.split(" ")[3];
            int heure2 = Integer.parseInt(ts2.split(":")[0]);

            int a = Integer.parseInt(PreferenceManager.getDefaultSharedPreferences(mContext).getString("beginning_hour", "0"));
            int b = Integer.parseInt(PreferenceManager.getDefaultSharedPreferences(mContext).getString("ending_hour", "0"));
        //Si heure1 ou heure2 est dans la plage horaire de nuit
            if ((a < heure1 || b > heure1 || a < heure2 || b > heure2) && (val1 > 50 || val2 > 50)){
                valeur3.setText("ALERTE");
                //mettre valeur3 à "ALERTE"
            } else {
                //sinon mettre valeur3 à String vide
                valeur3.setText("");
            }



        }
        catch(Exception e){
            Log.e("ERROR", e.getMessage(), e);
        }
    }

    protected void onProgressUpdate(){}

}
