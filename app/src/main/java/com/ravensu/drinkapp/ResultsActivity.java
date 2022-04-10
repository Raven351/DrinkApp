package com.ravensu.drinkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {
    private int correctAnswers = 0;
    private int wrongAnswers = 0;
    private TextView correctAnswersCountTextView;
    private TextView wrongAnswersCountTextView;
    private TextView passPercentageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Intent intent = getIntent();
        this.correctAnswers = intent.getIntExtra("correctAnswers", 0);
        this.wrongAnswers = intent.getIntExtra("wrongAnswers", 0);
        correctAnswersCountTextView = findViewById(R.id.correctAnswersCount);
        wrongAnswersCountTextView = findViewById(R.id.wrongAnswersCount);
        passPercentageTextView = findViewById(R.id.passPercentageValue);
        setResultValues();
    }

    private void setResultValues(){
        double correctAnswers = this.correctAnswers;
        double passPercentage = correctAnswers / 10 * 100;
        this.correctAnswersCountTextView.setText(String.valueOf(this.correctAnswers));
        this.wrongAnswersCountTextView.setText(String.valueOf(this.wrongAnswers));
        this.passPercentageTextView.setText(String.valueOf(passPercentage + "%"));
    }
}