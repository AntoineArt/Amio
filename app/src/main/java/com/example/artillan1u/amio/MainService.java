package com.example.artillan1u.amio;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

public class MainService extends Service {
    public void onCreate(){
        Log.d("MainService", "Service démarré");
    }

    public void onDestroy(){
        Log.d("MainService", "Service arrêté");
    }



    public int onStartCommand(Intent intent, int flags, int startId){
        //Démarrage du service
        return START_STICKY;
    }

    public MainService() {
        final Handler h = new Handler();
        final Runnable r = new Runnable() {
            @Override
            public void run() {
                Log.d("Repetitive task", "Repetitive task");
            }
        };
        h.postDelayed(r, 3000);
        //Trouver comment le rendre cyclique, là il ne s'exécute qu'une fois
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.

        throw new UnsupportedOperationException("Not yet implemented");
    }
}
