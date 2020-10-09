package com.example.intervaltrainingtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Timer;

public class TimerSetUp extends AppCompatActivity {
    EditText activityName;
    Spinner mins;
    Spinner sec;
    String time;
    SharedPreferences timerTimespref;
    SharedPreferences activityNamespref;
    SharedPreferences timesPref;

    public void done (View view){
        String exercise = activityName.getText().toString();

        if((int)mins.getSelectedItem() < 10 && (int)sec.getSelectedItem() < 10){
            time = "0"+mins.getSelectedItem().toString() + ":" + "0"+sec.getSelectedItem().toString();
        }
        else if((int)mins.getSelectedItem() < 10 || (int)sec.getSelectedItem() < 10){
            if((int)mins.getSelectedItem() < 10){
                time = "0"+mins.getSelectedItem().toString() + ":" +sec.getSelectedItem().toString();
            }
            else if((int)sec.getSelectedItem() < 10){
                time = mins.getSelectedItem().toString() + ":" + "0"+sec.getSelectedItem().toString();
            }
        }
        else{
            time = mins.getSelectedItem().toString() + ":" + sec.getSelectedItem().toString();
        }
        int timing = (int)mins.getSelectedItem()*60+(int)sec.getSelectedItem();
        Log.i("TIMING:   ", Integer.toString(timing));
        MainActivity.timerTimes.add(time);
        MainActivity.activityNames.add(exercise+" - "+time);
        MainActivity.times.add(Integer.toString(timing));

        timerTimespref = getApplicationContext().getSharedPreferences("com.example.intervaltrainingtimer", Context.MODE_PRIVATE);
        HashSet<String> timerTimesSet = new HashSet<>(MainActivity.timerTimes);
        timerTimespref.edit().putStringSet("timerTimes", timerTimesSet).apply();

        activityNamespref = getApplicationContext().getSharedPreferences("com.example.intervaltrainingtimer", Context.MODE_PRIVATE);
        HashSet<String> activitySet = new HashSet<>(MainActivity.activityNames);
        timerTimespref.edit().putStringSet("activites", activitySet).apply();

        timesPref = getApplicationContext().getSharedPreferences("com.example.intervaltrainingtimer", Context.MODE_PRIVATE);
        HashSet<String> timeSet = new HashSet<>(MainActivity.times);
        timerTimespref.edit().putStringSet("times", timeSet).apply();

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_set_up);

        mins = findViewById(R.id.mins);
        sec = findViewById(R.id.sec);
        Button done = findViewById(R.id.done);
        activityName = findViewById(R.id.activityName);

        ArrayList<Integer> minutes = new ArrayList<>();
        ArrayList<Integer> seconds = new ArrayList<>();
        for(int i = 0; i < 61; i ++){
            minutes.add(i);
            seconds.add(i);
        }

        ArrayAdapter<Integer> minAdapter = new ArrayAdapter<Integer>(TimerSetUp.this, android.R.layout.simple_spinner_dropdown_item, minutes);
        mins.setAdapter(minAdapter);
        ArrayAdapter<Integer> secAdapter = new ArrayAdapter<Integer>(TimerSetUp.this, android.R.layout.simple_spinner_dropdown_item, seconds);
        sec.setAdapter(secAdapter);

    }
}
