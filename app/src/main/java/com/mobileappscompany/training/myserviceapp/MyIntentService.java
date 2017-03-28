package com.mobileappscompany.training.myserviceapp;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by User on 1/31/2017.
 */

public class MyIntentService extends IntentService {

    public MyIntentService(){
        super("MyIntentService");
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        int secondsToWait = intent.getIntExtra(Constants.SECONDS_TO_WAIT, Constants.DEFAULT_WAITING_TIME);
        doLongThing(secondsToWait);
    }

    private void doLongThing(int i) {
        try {
            Thread.sleep(i * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.d("MAC_Tag", "You are done!!!");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
