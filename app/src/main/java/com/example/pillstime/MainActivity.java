package com.example.pillstime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private int progressStatus = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        final ProgressBar pb_default = (ProgressBar) findViewById(R.id.progressBar);
       pb_default.getProgressDrawable().setColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.SRC_IN);
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(progressStatus < 100) {
                    // Update the progress status
                    progressStatus += 1;

                    // Try to sleep the thread for 20 milliseconds
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // Update the progress bar
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            pb_default.setProgress(progressStatus);

                        }
                    });
                }
            }
        }).start();


        Timer timer =new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run() {
                Intent intent=new Intent(MainActivity.this, Signin.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }
}