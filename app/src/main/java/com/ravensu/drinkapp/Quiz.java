package com.ravensu.drinkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ravensu.drinkapp.models.Drink;

import org.json.JSONObject;

import java.util.ArrayList;

public class Quiz extends AppCompatActivity {

    private String requestUrl = "www.thecocktaildb.com/api/json/v1/1/random.php";
    private final int quizSize = 10;
    private int correctAnswers = 0;
    private int wrongAnswers = 0;
    private ArrayList<Drink> drinks = new ArrayList<>();
    private ArrayList<String> ingredients = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        loadDataForQuiz();
    }

    private void loadDataForQuiz() {
        Gson gson = new Gson();
        for (int i = 0; i < quizSize; i++){
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    requestUrl,
                    null,
                    response -> {
                        ingredients.add(response.toString());
                        Log.d("QuizR", "loadDataForQuiz: " + response.toString());
                    },
                    error -> {
                        Log.e("QuizR", "loadDataForQuiz: " + error.toString());
                    }
            );
        }
        TextView drinkName = findViewById(R.id.drinkNameTextView);
        drinkName.setText(ingredients.size());
    }
}