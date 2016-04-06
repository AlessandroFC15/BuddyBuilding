package com.example.android.buddybuilding;

import java.util.ArrayList;

/**
 * Created by Alessandro on 06/04/2016.
 */
public class Meal {

    String name;
    double totalProtein;
    double totalCarbs;
    double totalCalories;
    double totalFat;

    ArrayList<Food> foods = new ArrayList<>();

    Meal()
    {
        name = "Meal";
        totalProtein = 0;
        totalCarbs = 0;
        totalCalories = 0;
        totalFat = 0;
    }

    Meal(String nameOfMeal)
    {
        name = nameOfMeal;
        totalProtein = 0;
        totalCarbs = 0;
        totalCalories = 0;
        totalFat = 0;
    }

    public void addFood(String name, double protein, double carbohydrates, double totalFat)
    {
        Food newFood = new Food(name, protein, carbohydrates, totalFat);

        foods.add(newFood);

        this.totalProtein += newFood.getProtein();
        this.totalCarbs += newFood.getCarbs();
        this.totalCalories += newFood.getCalories();
        this.totalFat += newFood.getTotalFat();
    }
}
