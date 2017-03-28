package com.mobileappscompany.training.myserviceapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);

        initAlarmIntent();

        Intent i = new Intent(this, MyService.class);
        startService(i);

//        runTimerService();
    }

    private void initAlarmIntent() {
    /* Retrieve a PendingIntent that will perform a broadcast */
        Intent alarmIntent = new Intent(this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);
    }

    private void runTimerService() {
        Intent i = new Intent(this, MyTimerService.class);
        startService(i);
    }

    public void runIntentService(View view) {
        runIntentService();
    }

    private void runIntentService() {
        String timeToWaitString = editText.getText().toString();

        int timeToWait = Constants.DEFAULT_WAITING_TIME;
        if(timeToWaitString.isEmpty()) {
            timeToWait = Integer.valueOf(timeToWaitString);
        }

        Intent i = new Intent(this, MyIntentService.class);
        i.putExtra(Constants.SECONDS_TO_WAIT, timeToWait);
        startService(i);
    }

    public void startAlarm(View view) {
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int interval = 8000;

        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
        Toast.makeText(this, "Alarm Set", Toast.LENGTH_SHORT).show();
    }

    public void stopAlarm(View view) {
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        manager.cancel(pendingIntent);
        Toast.makeText(this, "Alarm Canceled", Toast.LENGTH_SHORT).show();
    }

    public void pickATime(View view) {
        Intent i = new Intent(this, TimePickerActivity.class);
        startActivityForResult(i, Constants.ACTIVITY_PICK_A_TIME);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == Constants.ACTIVITY_PICK_A_TIME) {
            AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);

            int horePicked = data.getIntExtra(Constants.HOUR_PARAMETER, 12);
            int minutePicked = data.getIntExtra(Constants.MINUTE_PARAMETER, 0);

            /* Set the alarm to start at 10:30 AM */
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.set(Calendar.HOUR_OF_DAY, horePicked);
            calendar.set(Calendar.MINUTE, minutePicked);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);

            /* Repeating on every 20 minutes interval */
            long repeatingInterva = 1000 * 5;//1000 * 60 * 20;
            manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                    repeatingInterva, pendingIntent);
            Log.d("MAC_Tag", "Done");
            Toast.makeText(this, "Your message has set to " + horePicked + ":" + minutePicked, Toast.LENGTH_SHORT).show();
        }

    }
}
