package com.example.android.buddybuilding;

import java.util.HashMap;

/**
 * Created by Alessandro on 06/04/2016.
 */
public class Diet {
    private double totalCalories;
    private double proteinIntake;
    private double carbsIntake;
    private double fatIntake;

    HashMap<Integer, Meal> meals = new HashMap<>();

    Diet()
    {
        addDefaultMeals();
    }

    private void addDefaultMeals()
    {
        meals.put(Meal.BREAKFAST, new Meal(Meal.BREAKFAST));
        meals.put(Meal.LUNCH, new Meal(Meal.LUNCH));
        meals.put(Meal.DINNER, new Meal(Meal.DINNER));
        meals.put(Meal.SNACKS, new Meal(Meal.SNACKS));
    }

    public void addFoodToMeal(int nameOfmeal, Food food)
    {
        if (nameOfmeal >= Meal.BREAKFAST && nameOfmeal <= Meal.SNACKS)
        {
            Meal meal = meals.get(nameOfmeal);

            meal.addFood(food);
        }
    }

    public double getDietTotalCalories()
    {
        double calories = 0;

        for (int nameOfMeal : meals.keySet())
        {
            Meal meal = meals.get(nameOfMeal);

            calories += meal.getMealTotalCalories();
        }

        return calories;
    }

    public double getCaloriesFromMeal(int nameOfMeal)
    {
        return meals.get(nameOfMeal).getMealTotalCalories();
    }

}
