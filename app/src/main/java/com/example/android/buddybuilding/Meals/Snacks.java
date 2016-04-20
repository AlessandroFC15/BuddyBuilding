package com.example.android.buddybuilding.Meals;

import com.example.android.buddybuilding.Food.Food;

/**
 * Created by Alessandro on 18/04/2016.
 */
public class Snacks extends Meal {
    public Snacks()
    {
        name = SNACKS;
    }

    public Snacks(Food food)
    {
        super(food);
    }

    public Snacks(final Snacks snacks)
    {
        super(snacks);
    }

    public String toString()
    {
        return ".: SNACKS :." + super.toString();
    }
}
