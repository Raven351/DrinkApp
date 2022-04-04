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
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.ravensu.drinkapp.models.Drink;

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
import java.util.logging.Handler;

public class Quiz extends AppCompatActivity {

    private final String requestUrl = "https://www.thecocktaildb.com/api/json/v1/1/random.php";
    private final int quizSize = 10;
    private int correctAnswers = 0;
    private int wrongAnswers = 0;
    private ArrayList<Drink> drinks = new ArrayList<>();
    private ArrayList<String> ingredients = new ArrayList<>();
    private TextView drinkNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        this.drinkNameTextView = findViewById(R.id.drinkNameTextView);
        loadDataForQuiz();
        drinkNameTextView.setText(drinks.get(0).getName());
    }

    private void loadDataForQuiz() {
        Gson gson = new Gson();
        for (int i = 0; i < quizSize; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        URL url = new URL(requestUrl);
                        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                        StringBuilder sb = new StringBuilder();
                        String line;
                        while ((line = bufferedReader.readLine()) != null){
                            sb.append(line).append('\n');
                        }
                        String body = sb.toString();
                        urlConnection.disconnect();
                        JSONObject jsonObject = new JSONObject(body);
                        JSONArray jsonArray = jsonObject.getJSONArray("drinks");
                        Drink drink = gson.fromJson(jsonArray.getString(0), Drink.class);
                        drinks.add(drink);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}