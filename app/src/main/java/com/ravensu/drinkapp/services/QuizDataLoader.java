package com.ravensu.drinkapp.services;

import com.google.gson.Gson;
import com.ravensu.drinkapp.models.Drink;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class QuizDataLoader {
    private final String randomSingleDrinkRequestUrl = "https://www.thecocktaildb.com/api/json/v1/1/random.php";
    private final ExecutorService executorService;
    private final Integer questionsCount;

    public QuizDataLoader(Integer questionsCount) {
        this.questionsCount = questionsCount;
        executorService = Executors.newFixedThreadPool(questionsCount);
    }

    public Future<ArrayList<Drink>> getRandomDrinksForQuiz(){
        return executorService.submit(() -> {
            ArrayList<Drink> drinks = new ArrayList<>();
            for (int i = 0; i < this.questionsCount; i++){
                try {
                    drinks.add(RequestRandomDrink());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return drinks;
        });
    }

    private String RequestDataFromAPI(String requestUrl){
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
            return body;
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Drink RequestRandomDrink() throws IOException {
        Gson gson = new Gson();
        try {
            JSONObject jsonObject = new JSONObject(Objects.requireNonNull(RequestDataFromAPI(randomSingleDrinkRequestUrl)));
            JSONArray jsonArray = jsonObject.getJSONArray("drinks");
            return gson.fromJson(jsonArray.getString(0), Drink.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
