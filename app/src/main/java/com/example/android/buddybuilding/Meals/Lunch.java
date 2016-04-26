package com.example.android.buddybuilding.Meals;

import com.example.android.buddybuilding.Food.Food;

import java.util.HashMap;

/**
 * Created by Alessandro on 18/04/2016.
 */
public class Lunch extends Meal {

    private static HashMap<String, Food> commonLunchFoods;
    static {
        commonLunchFoods = new HashMap<>();
        commonLunchFoods.put("Frango",
                new Food("Frango", 150, 45, 1, 5));
    }

    public Lunch()
    {
        name = LUNCH;

        // addDefaultFood();
    }

    public Lunch(Food food)
    {
        super(food);

        name = LUNCH;

    }

    public Lunch(final Lunch lunch)
    {
        super(lunch);

    }

    protected void addDefaultFood()
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

    public static HashMap<String, Food> getCommonLunchFoods()
    {
        return commonLunchFoods;
    }
}
