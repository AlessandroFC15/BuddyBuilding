package com.example.android.buddybuilding.Meals;

import com.example.android.buddybuilding.Food.Food;

import java.util.HashMap;

/**
 * Created by Alessandro on 18/04/2016.
 */
public class Dinner extends Meal {
    private static HashMap<String, Food> commonDinnerFoods;
    static {
        commonDinnerFoods = new HashMap<>();
        commonDinnerFoods.put("Pão Francês",
                new Food("Pão Francês", 50, 4.73, 25.33, 1.98));
        commonDinnerFoods.put("Queijo Mussarela",
                new Food("Queijo Mussarela", 25, 5.65, 0.75, 6.3));
    }


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

    protected void addDefaultFood()
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

    public static HashMap<String, Food> getCommonDinnerFoods()
    {
        return commonDinnerFoods;
    }
}
