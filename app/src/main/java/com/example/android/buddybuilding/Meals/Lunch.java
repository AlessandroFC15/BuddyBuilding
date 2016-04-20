package com.example.android.buddybuilding.Meals;

import com.example.android.buddybuilding.Food.Food;

/**
 * Created by Alessandro on 18/04/2016.
 */
public class Lunch extends Meal {

    public Lunch()
    {
        name = LUNCH;

        // addDefaultFood();
    }

    public Lunch(Food food)
    {
        super(food);
    }

    public Lunch(final Lunch lunch)
    {
        super(lunch);
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
}
