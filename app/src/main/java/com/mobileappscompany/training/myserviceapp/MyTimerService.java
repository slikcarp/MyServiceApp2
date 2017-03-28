package com.mobileappscompany.training.myserviceapp;

import android.app.AlarmManager;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MyTimerService extends Service {
    public MyTimerService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        try {
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                Handler mHandler = new Handler(Looper.getMainLooper());
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {

                        showToastMessage("Annoying toast message");

                    }
                });

            }
        }, 0, 5000);

        return Service.START_STICKY;
    }

    private void showToastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }
}
