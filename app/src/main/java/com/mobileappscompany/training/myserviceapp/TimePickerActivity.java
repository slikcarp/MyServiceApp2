package com.mobileappscompany.training.myserviceapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TimePicker;

public class TimePickerActivity extends AppCompatActivity {

    private TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        timePicker = (TimePicker) findViewById(R.id.timePicker);
    }

    @Override
    public void onBackPressed() {
        goBack(null);
        super.onBackPressed();
    }

    public void goBack(View view) {
        Intent returnIntent = new Intent();

        returnIntent.putExtra(Constants.HOUR_PARAMETER, timePicker.getCurrentHour());
        returnIntent.putExtra(Constants.MINUTE_PARAMETER, timePicker.getCurrentMinute());
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }
}
