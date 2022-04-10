package com.ravensu.drinkapp.services;

import com.ravensu.drinkapp.models.Drink;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class QuizQuestion {
    private final Drink drink;
    private final QuizQuestionType quizQuestionType;
    private final boolean questionAnswer;
    private final String question;

    public QuizQuestion(Drink drink) {
        this.drink = drink;
        this.quizQuestionType = GenerateRandomQuestionType();
        this.question = setUpQuestion();
        this.questionAnswer = setUpAnswer();
    }

    private QuizQuestionType GenerateRandomQuestionType(){
        return QuizQuestionType.values()[new Random().nextInt(QuizQuestionType.values().length)];
    }

    private String setUpQuestion(){
        switch (quizQuestionType) {
            case IS_ALCOHOLIC:{
                return "Do you think this drink contains alcohol?";
            }
            default: {
                return "Invalid question";
            }
        }
    }

    private boolean setUpAnswer(){
        switch (quizQuestionType) {
            case IS_ALCOHOLIC:{
                return !this.drink.getIsAlcoholicStr().contains("Non");
            }
            default: {
                return false;
            }
        }
    }

    public String getQuestion() {
        return question;
    }

    public boolean isQuestionAnswer() {
        return questionAnswer;
    }
}
