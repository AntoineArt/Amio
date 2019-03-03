package com.example.artillan1u.amio;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.DownloadListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
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
                new DownloadAsync(valeur1, valeur2).execute();
            }
        });


        final SharedPreferences prefs = this.getSharedPreferences("com.example.artillan1u", Context.MODE_PRIVATE);
        Boolean b = new Boolean(Boolean.TRUE);
        final String bKey = "bKey";
        prefs.edit().putBoolean(bKey, b).apply();




        Log.d("MainActivity", "Creation de l'activité" + PreferenceManager.getDefaultSharedPreferences(this).getAll());
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            // launch settings activity
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
