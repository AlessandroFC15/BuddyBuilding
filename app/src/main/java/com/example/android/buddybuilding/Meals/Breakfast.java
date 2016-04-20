package com.example.android.buddybuilding.Meals;

import com.example.android.buddybuilding.Food.Food;

/**
 * Created by Alessandro on 18/04/2016.
 */
public class Breakfast extends Meal {

    public Breakfast()
    {
        name = BREAKFAST;

        // addDefaultFood();
    }

    public Breakfast(Food food)
    {
        super(food);
    }

    public Breakfast(final Breakfast breakfast)
    {
        super(breakfast);
    }

    private void addDefaultFood()
    {
        Food food = new Food("Pão", 50, 2, 35, 5);

        addFood(food);
    }

    public String toString()
    {
        return ".: BREAKFAST :." + super.toString();
    }
}
