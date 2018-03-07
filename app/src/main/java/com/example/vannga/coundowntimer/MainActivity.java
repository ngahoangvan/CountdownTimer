package com.example.vannga.coundowntimer;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")
public class MainActivity extends AppCompatActivity {
    private EditText edText;
    private Button btnStart,btnStop;
    private CountDownTimer timer;
    private TextView tvFinish;
    private boolean timerRunning;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.countdowntime);

        tvFinish = (TextView) findViewById(R.id.tvFinish);

        edText =(EditText) findViewById(R.id.edText);

        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);

    }

    @Override
    protected void onStart() {
        super.onStart();

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startStop();
            }
        });



        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
                edText.setText("0");
                btnStart.setText("Start");
            }
        });
    }


    public void startStop(){
        if(timerRunning){
            stopTime();
        }else{
            startTime();
        }
    }

    public void startTime(){
        timer = new CountDownTimer(Integer.parseInt(edText.getText().toString())*1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String hsm = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
                edText.setText(String.valueOf(millisUntilFinished/1000));
                tvFinish.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFinish() {
                edText.setText("0");
                btnStart.setText("Start");
                tvFinish.setVisibility(View.VISIBLE);
            }
        }.start();
        btnStart.setText("Pause");
        timerRunning = true;
    }
    public void stopTime(){
        timer.cancel();
        btnStart.setText("Start");
        timerRunning = false;
    }

    public void updateTime(){
        int min = Integer.parseInt(edText.getText().toString()) / 60000;
        int seconds = Integer.parseInt(edText.getText().toString()) % 60000 / 1000;

        String timeLeftText;

        timeLeftText = "" + min;
        timeLeftText += ":";
        if (seconds < 10) timeLeftText += "0";
        timeLeftText += seconds;

        edText.setText(timeLeftText);
    }

}