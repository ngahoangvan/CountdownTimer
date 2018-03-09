package com.example.vannga.coundowntimer;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")
public class MainActivity extends AppCompatActivity {
    private TextView tvCountdown;
    private Button btnStart,btnStop,btnSetting;
    private CountDownTimer timer;
    private TextView tvFinish;
    private boolean timerRunning;
    private boolean firstTime;
    private long mTimeLeftInMillis  ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.countdowntime);

        tvFinish = (TextView) findViewById(R.id.tvFinish);

        tvCountdown =(TextView) findViewById(R.id.tvCountdown);

        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);
        btnSetting = (Button) findViewById(R.id.btnSetting) ;

        Intent intent = getIntent();
        mTimeLeftInMillis = intent.getLongExtra("dulieu", 0) * 1000 * 60;
        System.out.println(mTimeLeftInMillis);

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
                tvCountdown.setText("00:00:00");
                btnStart.setText("Start");
            }
        });

        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });

        updateTime();
    }

    public void startStop(){
        if(timerRunning){
            stopTime();
        }else{
            startTime();
        }
    }

    public void startTime(){


        timer = new CountDownTimer(mTimeLeftInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                String hsm = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
                tvCountdown.setText(hsm);
            }

            @Override
            public void onFinish() {
                tvCountdown.setText("00:00:00");
                btnStart.setText("Start");
                tvFinish.setVisibility(View.VISIBLE);
            }
        }.start();

        btnStart.setText("Pause");
        timerRunning = true;
    }

    public void stopTime(){
        timer.cancel();
        timerRunning = false;
        btnStart.setText("Start");
    }

    public void updateTime(){

        int hours = 0;
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
        while (minutes >= 60){
            minutes -=60;
            hours +=1;
        }
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d:%02d",hours, minutes, seconds);
        tvCountdown.setText(timeLeftFormatted);
    }

}