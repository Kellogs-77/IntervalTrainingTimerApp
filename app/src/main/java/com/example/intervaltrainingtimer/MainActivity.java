package com.example.intervaltrainingtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    static ArrayList<String> activityNames = new ArrayList<String>();
    static ArrayList<String> times = new ArrayList<String>();
    static ArrayList<String> timerTimes = new ArrayList<>();
    static ArrayAdapter<String> arrayAdapter;
    SharedPreferences timerTimespref;
    SharedPreferences activityNamespref;
    SharedPreferences timesPref;


    public void go (View view){
        if(activityNames.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please enter activites first", Toast.LENGTH_LONG).show();
        }
        else {
            Intent i = new Intent(getApplicationContext(), Timers.class);

            startActivity(i);
        }
    }

    public void addNew(View view){
        Intent i = new Intent(getApplicationContext(), TimerSetUp.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerTimespref = getApplicationContext().getSharedPreferences("com.example.intervaltrainingtimer", Context.MODE_PRIVATE);
        activityNamespref = getApplicationContext().getSharedPreferences("com.example.intervaltrainingtimer", Context.MODE_PRIVATE);
        timesPref = getApplicationContext().getSharedPreferences("com.example.intervaltrainingtimer", Context.MODE_PRIVATE);

        HashSet<String> timerTimesSet = (HashSet<String>) timerTimespref.getStringSet("timerTimes", null);
        HashSet<String> activitySet = (HashSet<String>) timerTimespref.getStringSet("activites", null);
        HashSet<String> timeSet = (HashSet<String>) timerTimespref.getStringSet("times", null);

        if(timerTimesSet!=null && activitySet!=null && timeSet!=null){
            timerTimes = new ArrayList<>(timerTimesSet);
            activityNames = new ArrayList<>(activitySet);
            times = new ArrayList<>(timeSet);
        }


        ListView activites = (ListView) findViewById(R.id.activity);

//        activityNames.add("pushups");
//        activityNames.add("sprint")
//        ;
//        activityNames.add("situps");

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, activityNames);
        activites.setAdapter(arrayAdapter);

    }
}
