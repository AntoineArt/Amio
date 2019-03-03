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

import static android.os.SystemClock.sleep;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView valeur1, valeur2, valeur3;
        valeur1 = (TextView) findViewById(R.id.valeur1);
        valeur2 = (TextView) findViewById(R.id.valeur2);
        valeur3 = (TextView) findViewById(R.id.valeur3);

        valeur1.setText("" + 0.0);
        valeur2.setText("" + 0.0);

        final Button refreshButton = (Button) findViewById(R.id.refreshButton);
        refreshButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //LayoutInflater layoutInflater = new
                //View myView = new LayoutInflater().inflate(R.layout.activity_main);
                new DownloadAsync(valeur1, valeur2, valeur3, getApplicationContext()).execute();

                //verification que valeur3 n'est pas une alerte
                if (valeur3.getText().equals("ALERTE")){
                    //Appeler la methode sendMail de la classe MainActivity
                    final String RECIPIENT = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("email_address", "jeremy.beauchesne@telecomnancy.net");; //mettre ici le mail des preferences
                    final String SUBJECT = "Notification de lumiere allumee";
                    final String BODY = "Une lumiere a ete detectee comme allumee dans les locaux de TNCY";

                    Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, RECIPIENT);
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, SUBJECT);
                    emailIntent.putExtra(Intent.EXTRA_TEXT, BODY);
                    emailIntent.setType("text/plain");
                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                }
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

    /*
    protected void sendMail(){

        final String RECIPIENT = "jeremy.beauchesne@telecomnancy.net"; //mettre ici le mail des preferences
        final String SUBJECT = "Notification de lumiere allumee";
        final String BODY = "Une lumiere a ete detectee comme allumee dans les locaux de TNCY";

        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, RECIPIENT);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, SUBJECT);
        emailIntent.putExtra(Intent.EXTRA_TEXT, BODY);
        emailIntent.setType("text/plain");
        startActivity(Intent.createChooser(emailIntent, "Send mail..."));
    }
    */
}
