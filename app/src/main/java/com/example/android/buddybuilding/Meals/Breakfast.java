package com.example.android.buddybuilding.Meals;

import com.example.android.buddybuilding.Food.Food;

import java.util.HashMap;

/**
 * Created by Alessandro on 18/04/2016.
 */
public class Breakfast extends Meal {

    private static HashMap<String, Food> commonBreakfastFoods;
    static {
        commonBreakfastFoods = new HashMap<>();
        commonBreakfastFoods.put("Pão Francês",
                new Food("Pão Francês", 50, 4.73, 25.33, 1.98));
        commonBreakfastFoods.put("Queijo Mussarela",
                new Food("Queijo Mussarela", 25, 5.65, 0.75, 6.3));
    }

    public Breakfast()
    {
        name = BREAKFAST;

        // addDefaultFood();
    }

    public Breakfast(Food food)
    {
        super(food);

        name = BREAKFAST;
    }

    public Breakfast(final Breakfast breakfast)
    {
        super(breakfast);
    }

    protected void addDefaultFood()
    {
        Food food = new Food("Pão", 50, 2, 35, 5);

        addFood(food);
    }

    public String toString()
    {
        return ".: BREAKFAST :." + super.toString();
    }

    public static HashMap<String, Food> getCommonBreakfastFoods()
    {
        return commonBreakfastFoods;
    }

    public static void addFoodToCommonBreakfastFoods(Food food)
    {
        if (! commonBreakfastFoods.containsValue(food))
        {
            commonBreakfastFoods.put(food.getName(), food);
        }
    }

    public static void removeFoodFromCommonBreakfastFoods(String nameOfFood)
    {
        if (! commonBreakfastFoods.containsKey(nameOfFood))
        {
            commonBreakfastFoods.remove(nameOfFood);
        }
    }
}
