package com.ravensu.drinkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    @Override
    public void onBackPressed() {

    }

    public void onClickStartQuiz(View view) {
        Intent intent = new Intent(this, Quiz.class);
        this.startActivity(intent);
    }
}