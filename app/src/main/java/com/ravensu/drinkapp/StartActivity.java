package com.ravensu.drinkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new CountDownTimer(1000, 1000){

            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {

            }
        }.start();
        Intent mainMenuIntent = new Intent(this, MainMenu.class);
        this.startActivity(mainMenuIntent);
    }
}