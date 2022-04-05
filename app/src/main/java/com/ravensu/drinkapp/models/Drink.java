package com.ravensu.drinkapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Drink {
    @SerializedName("strDrink")
    private String name;

    @SerializedName("strDrinkThumb")
    private String imgUrl;

    private ArrayList<String> ingredientsList;
    private boolean isAlcoholic;

    public Drink(String name, String url, ArrayList<String> ingredientsList, boolean isAlcoholic) {
        this.name = name;
        this.imgUrl = url;
        this.ingredientsList = ingredientsList;
        this.isAlcoholic = isAlcoholic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getIngredientsList() {
        return ingredientsList;
    }

    public void setIngredientsList(ArrayList<String> ingredientsList) {
        this.ingredientsList = ingredientsList;
    }

    public boolean isAlcoholic() {
        return isAlcoholic;
    }

    public void setAlcoholic(boolean alcoholic) {
        isAlcoholic = alcoholic;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
