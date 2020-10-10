package com.example.myappgithubuser3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class ReminderActivity extends AppCompatActivity {

    Switch notificationReminder;
    SharedPreferences sharedPreferences;
    private AlarmReceiver alarmReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        alarmReceiver = new AlarmReceiver();

        notificationReminder = findViewById(R.id.switch_reminder);
        checkSharedPreference();
        notificationReminder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (checked) {
                    saveSharedPreference(1);
                    setAlarmReceiver();
                    Toast.makeText(ReminderActivity.this, "Success", Toast.LENGTH_SHORT).show();
                } else {
                    saveSharedPreference(0);
                    cancelAlarmReceiver();
                    Toast.makeText(ReminderActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void setAlarmReceiver(){
        alarmReceiver.setOneTimeAlarm(getApplicationContext());
    }

    private void saveSharedPreference(int i) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("reminder", i);
        editor.apply();
    }

    private void cancelAlarmReceiver(){
        alarmReceiver.cancelAlarm(getApplicationContext());
    }

    private void checkSharedPreference() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int statusChecked = sharedPreferences.getInt("reminder", 0);
        if (statusChecked == 1) {
            notificationReminder.setChecked(true);
        } else {
            notificationReminder.setChecked(false);
        }
    }
}