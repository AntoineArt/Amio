package com.example.artillan1u.amio;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.DownloadListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends FragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView valeur1, valeur2;
        valeur1 = (TextView) findViewById(R.id.valeur1);
        valeur2 = (TextView) findViewById(R.id.valeur2);

        valeur1.setText("" + 0.0);
        valeur2.setText("" + 0.0);

        final Button refreshButton = (Button) findViewById(R.id.refreshButton);
        refreshButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //LayoutInflater layoutInflater = new
                //View myView = new LayoutInflater().inflate(R.layout.activity_main);
                new DownloadAsync().execute();
            }
        });



        ToggleButton b1 = (ToggleButton) findViewById(R.id.toggleButton1);
        final TextView tv2 = (TextView) findViewById(R.id.textView2);

        b1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    startService(new Intent(getApplicationContext(), MainService.class));
                    tv2.setText("En cours");
                }
                else{
                    stopService(new Intent(getApplicationContext(), MainService.class));
                    tv2.setText("Arrêté");
                }
            }
        });

        final SharedPreferences prefs = this.getSharedPreferences("com.example.artillan1u", Context.MODE_PRIVATE);
        Boolean b = new Boolean(Boolean.TRUE);
        final String bKey = "bKey";
        prefs.edit().putBoolean(bKey, b).apply();

        CheckBox cb = (CheckBox) findViewById(R.id.checkBox);
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    Log.d("Checked", "Checked");
                    prefs.edit().putBoolean(bKey, Boolean.TRUE);
                }
                else {
                    Log.d("Unchecked", "Unchecked");
                    prefs.edit().putBoolean(bKey, Boolean.FALSE);
                }
            }
        });




        Log.d("MainActivity", "Creation de l'activité");
    }
}
