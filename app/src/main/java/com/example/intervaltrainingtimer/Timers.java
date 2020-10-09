package com.example.intervaltrainingtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Timers extends AppCompatActivity {
    Boolean counterActive = false;
    TextView exercise;
    TextView timer;
    CountDownTimer countDownTimer;
    ArrayList<Integer> timerTimes = new ArrayList<Integer>();
    ArrayList<String> activities = new ArrayList<String>();
    ArrayList<String> times = new ArrayList<String>();
    int count = 0;
    Handler handler;

    public boolean setATimer(final int combinedTime, String activity, String time){
        exercise.setText(activity);
        timer.setText(time);
        count++;
        countDownTimer = new CountDownTimer(combinedTime*1000, 1000) {
            int minute = combinedTime/60;
            int second = combinedTime%60;
            //int otherSecond = second+1;
            String secondString = null;
            @Override
            public void onTick(long millisUntilFinished) {

                second--;

                if (second == 59) {
                    minute--;
                }

                if (second > 0 && second < 10) {
                    secondString = "0" + second;
                    timer.setText("" + minute + ":" + "0" + second);
                } else if (second == 0) {
                    secondString = "00";
                    timer.setText("" + minute + ":" + second);
                }

                else {
                    timer.setText("" + minute + ":" + second);
                }
            }
            @Override
            public void onFinish() {
                if(count == MainActivity.activityNames.size()){
                    Intent intent = new Intent(getApplicationContext(), Done.class);
                    startActivity(intent);
                }
                if(count < MainActivity.activityNames.size()){
                    exercise.setText(activities.get(count));
                    timer.setText(times.get(count));
                    handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            setATimer(timerTimes.get(count), activities.get(count), times.get(count));

                        }
                    }, 1000);

                }

            }
        }.start();

        return true;



    }

    //MAKE A METHOD THAT SETS A TIMER FOR I*1000 SEC AND CALL IT IN THE FOR LOOP EZPZ
    public void timerStart(){
//        counterActive = true;
//        int breakOff = 0;
//        String ex;
//        for(int i = 0; i < MainActivity.times.size(); i++){
//            ex = MainActivity.activityNames.get(i);
//            for(int j = 0; j <ex.length(); j++){
//               char hyphen = ex.charAt(j);
//               if(Character.valueOf(hyphen).compareTo(Character.valueOf('-')) == 0){
//                    breakOff = j-1;
//                   break;
//               }
//            }
//
//            String halfEx = ex.substring(0, breakOff);
//            String duration = ex.substring(breakOff+2);
//
//            if(i < MainActivity.times.size()-1){
//                setATimer(MainActivity.times.get(i), halfEx, duration, false);
//
//            }
//            else if(i == MainActivity.times.size()){
//                setATimer(MainActivity.times.get(i), halfEx, duration, true);
//
//            }
//
//
//        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timers);
        exercise = findViewById(R.id.exercise);
        timer = findViewById(R.id.timer);
        int breakOff = 0;
        for(int i = 0; i < MainActivity.activityNames.size(); i++){
            String ex = MainActivity.activityNames.get(i);
            for(int j = 0; j <ex.length(); j++){
                char hyphen = ex.charAt(j);
                if(Character.valueOf(hyphen).compareTo(Character.valueOf('-')) == 0){
                    breakOff = j-1;
                    break;
                }
            }
            String halfEx = ex.substring(0, breakOff);
            String duration = ex.substring(breakOff+2);
            timerTimes.add(Integer.parseInt(MainActivity.times.get(i)));
            activities.add(halfEx);
            times.add(duration);
        }
        exercise.setText(activities.get(0));
        timer.setText(times.get(0));
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                exercise.setText(activities.get(0));
                timer.setText(times.get(0));
                setATimer(timerTimes.get(0), activities.get(0), times.get(0));

            }
        }, 1000);



    }

}
