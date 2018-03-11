package com.example.vannga.coundowntimer;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ProgressBar;

/**
 * Created by VanNga on 3/14/2018.
 */

public class testProgressBar extends AppCompatActivity
{
    private ProgressBar progressBar;
    private Button btnPause;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnPause = (Button) findViewById(R.id.btnPause);
        final ObjectAnimator animation = ObjectAnimator.ofInt (progressBar, "progress", 0, 500); // see this max value coming back here, we animate towards that value
        animation.setDuration (50000); //in milliseconds
        animation.setInterpolator (new DecelerateInterpolator());
        animation.start ();

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animation.pause();
            }
        });

    }



}
