package com.example.android.buddybuilding.Meals;

import com.example.android.buddybuilding.Food.Food;

/**
 * Created by Alessandro on 18/04/2016.
 */
public class Dinner extends Meal {
    public Dinner()
    {
        name = DINNER;

        // addDefaultFood();
    }

    public Dinner(Food food)
    {
        super(food);

        name = DINNER;
    }

    public Dinner(final Dinner dinner)
    {
        super(dinner);
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
        return ".: DINNER :." + super.toString();
    }
}
