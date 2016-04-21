package com.example.android.buddybuilding.Meals;

import com.example.android.buddybuilding.Food.Food;

import java.util.ArrayList;

/**
 * Created by Alessandro on 18/04/2016.
 */
public class Lunch extends Meal {

    private static ArrayList<Food> commonLunchFoods = new ArrayList<>();

    public Lunch()
    {
        name = LUNCH;

        addCommonLunchFoods();

        // addDefaultFood();
    }

    public Lunch(Food food)
    {
        super(food);

        name = LUNCH;

        addCommonLunchFoods();
    }

    public Lunch(final Lunch lunch)
    {
        super(lunch);

        addCommonLunchFoods();
    }

    private void addDefaultFood()
    {
        Food frango = new Food("Frango", 150, 35, 0, 5);
        Food macarrao = new Food("Macarrão", 150, 0, 50, 5);

        addFood(frango);
        addFood(macarrao);
    }

    public String toString()
    {
        return ".: LUNCH :." + super.toString();
    }

    private void addCommonLunchFoods()
    {
        addFrango();
        addMacarrao();
    }

    private void addFrango()
    {
        int servingSize = 150;
        double protein = 45;
        double carbs = 1;
        double fat = 5;

        Food food = new Food("Frango", servingSize, protein, carbs, fat);

        commonLunchFoods.add(food);
    }

    private void addMacarrao()
    {
        int servingSize = 150;
        double protein = 5.65;
        double carbs = 40;
        double fat = 1.5;

        Food food = new Food("Macarrão", servingSize, protein, carbs, fat);

        commonLunchFoods.add(food);
    }

    public static ArrayList<Food> getCommonLunchFoods()
    {
        return commonLunchFoods;
    }
}
