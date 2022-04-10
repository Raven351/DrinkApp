package com.ravensu.drinkapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Drink {
    @SerializedName("strDrink")
    private String name;

    @SerializedName("strDrinkThumb")
    private String imgUrl;

    @SerializedName("strAlcoholic")
    private String isAlcoholicStr;

    private ArrayList<String> ingredientsList;

    public Drink(String name, String url, ArrayList<String> ingredientsList, String isAlcoholicStr) {
        this.name = name;
        this.imgUrl = url;
        this.ingredientsList = ingredientsList;
        this.isAlcoholicStr = isAlcoholicStr;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getIsAlcoholicStr() {
        return isAlcoholicStr;
    }

    public void setIsAlcoholicStr(String isAlcoholicStr) {
        this.isAlcoholicStr = isAlcoholicStr;
    }
}
