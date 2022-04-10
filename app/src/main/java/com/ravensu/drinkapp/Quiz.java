package com.ravensu.drinkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.ravensu.drinkapp.models.Drink;
import com.ravensu.drinkapp.services.QuizDataLoader;
import com.ravensu.drinkapp.services.QuizQuestion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Handler;

public class Quiz extends AppCompatActivity {

    private final String requestUrl = "https://www.thecocktaildb.com/api/json/v1/1/random.php";
    private final int quizSize = 10;
    public int currentQuestionIndex = 0;
    private int correctAnswers = 0;
    private int wrongAnswers = 0;
    private ArrayList<Drink> drinks = new ArrayList<>();
    private ArrayList<String> ingredients = new ArrayList<>();
    private TextView drinkNameTextView;
    private TextView quizQuestionTextView;
    private ImageView drinkThumbnailImageview;
    private ExecutorService executorService = Executors.newFixedThreadPool(1);
    private QuizQuestion currentQuizQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        this.drinkNameTextView = findViewById(R.id.drinkNameTextView);
        this.drinkThumbnailImageview = findViewById(R.id.drinkThumbnail);
        this.quizQuestionTextView = findViewById(R.id.questionTextView);
        loadDataForQuiz();
        showNextQuestion();
    }

    private void loadDataForQuiz() {
        QuizDataLoader quizDataLoader = new QuizDataLoader(quizSize);
        try {
            drinks = quizDataLoader.getRandomDrinksForQuiz().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Future<Bitmap> getDrinkThumbnailBitmap(String thumbnailUrl){
        return executorService.submit(() -> {
            Bitmap bmp = null;
            URL url = null;
            try {
                url = new URL(thumbnailUrl);
                bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bmp;
        });
    }

    private void setDrinkThumbnailImageview(int currentQuestionIndex){
        try {
            drinkThumbnailImageview.setImageBitmap(getDrinkThumbnailBitmap(drinks.get(currentQuestionIndex).getImgUrl()).get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void onClickYes(View view) {
        if (currentQuizQuestion.isQuestionAnswer()) correctAnswers += 1;
        else wrongAnswers += 1;
        showNextQuestion();
    }

    public void onClickNo(View view){
        if (!currentQuizQuestion.isQuestionAnswer()) correctAnswers += 1;
        else wrongAnswers += 1;
        showNextQuestion();
    }

    public void showNextQuestion(){
        if (currentQuestionIndex < drinks.size()) {
            currentQuizQuestion = new QuizQuestion(drinks.get(currentQuestionIndex));
            drinkNameTextView.setText(drinks.get(currentQuestionIndex).getName());
            setDrinkThumbnailImageview(currentQuestionIndex);
            currentQuestionIndex += 1;
        }
        else {
            Intent intent = new Intent(this, ResultsActivity.class);
            intent.putExtra("correctAnswers", correctAnswers);
            intent.putExtra("wrongAnswers", wrongAnswers);
            this.startActivity(intent);
        }
    }
}