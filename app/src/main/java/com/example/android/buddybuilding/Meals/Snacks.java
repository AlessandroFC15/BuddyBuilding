package com.example.android.buddybuilding.Meals;

import com.example.android.buddybuilding.Food.Food;

import java.util.HashMap;

/**
 * Created by Alessandro on 18/04/2016.
 */
public class Snacks extends Meal {
    private static HashMap<String, Food> commonSnacksFoods;
    static {
        commonSnacksFoods = new HashMap<>();
        commonSnacksFoods.put("Pão Francês",
                new Food("Pão Francês", 50, 4.73, 25.33, 1.98));
        commonSnacksFoods.put("Queijo Mussarela",
                new Food("Queijo Mussarela", 25, 5.65, 0.75, 6.3));
    }

    public Snacks()
    {
        name = SNACKS;
    }

    public Snacks(Food food)
    {
        super(food);

        name = SNACKS;
    }

    public Snacks(final Snacks snacks)
    {
        super(snacks);
    }

    public String toString()
    {
        return ".: SNACKS :." + super.toString();
    }

    public static HashMap<String, Food> getCommonSnacksFoods()
    {
        return commonSnacksFoods;
    }
}
