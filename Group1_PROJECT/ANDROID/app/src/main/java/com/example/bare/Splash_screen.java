package com.example.bare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splash_screen extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 2000;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        imageView=(ImageView)findViewById(R.id.imageView);
        new Handler().postDelayed(new Runnable() {
        public void run() {
            Intent homeIntent = new Intent(Splash_screen.this , MainActivity.class);
            startActivity(homeIntent);
            finish();
        }
    },SPLASH_TIME_OUT);


        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.splash);
        imageView.startAnimation(myanim);
    }

    }
