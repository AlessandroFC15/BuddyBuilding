package com.example.android.buddybuilding.Diet;

import com.example.android.buddybuilding.Food;
import com.example.android.buddybuilding.Meals.Breakfast;
import com.example.android.buddybuilding.Meals.Dinner;
import com.example.android.buddybuilding.Meals.Lunch;
import com.example.android.buddybuilding.Meals.Meal;
import com.example.android.buddybuilding.Meals.Snacks;
import com.example.android.buddybuilding.User.User;

import java.util.HashMap;

/**
 * Created by Alessandro on 06/04/2016.
 */
public abstract class Diet {
    protected double totalCaloriesTarget;
    private double totalCaloriesIntake;
    private double proteinIntake;
    private double carbsIntake;
    private double fatIntake;
    private HashMap<Integer, Meal> meals = new HashMap<>();

    public static Food lastFoodAdded = null;
    // public static boolean foodAdded;
    public static int mealChanged;

    public Diet()
    {
        setAttributesToDefault();

        addDefaultMeals();
    }

    public Diet(final Diet diet)
    {
        totalCaloriesTarget = diet.totalCaloriesTarget;
        totalCaloriesIntake = diet.totalCaloriesIntake;
        proteinIntake = diet.proteinIntake;
        carbsIntake = diet.carbsIntake;
        fatIntake = diet.fatIntake;
        meals = diet.meals;
    }

    protected void setAttributesToDefault()
    {
        totalCaloriesTarget = 0;
        totalCaloriesIntake = 0;
        proteinIntake = 0;
        carbsIntake = 0;
        fatIntake = 0;
    }

    protected void addDefaultMeals()
    {
        meals.put(Meal.BREAKFAST, new Breakfast());
        meals.put(Meal.LUNCH, new Lunch());
        meals.put(Meal.DINNER, new Dinner());
        meals.put(Meal.SNACKS, new Snacks());
    }

    public void addFoodToMeal(int nameOfmeal, Food food)
    {
        if (nameOfmeal >= Meal.BREAKFAST && nameOfmeal <= Meal.SNACKS)
        {
            Meal meal = meals.get(nameOfmeal);

            meal.addFood(food);

            mealChanged = nameOfmeal;

            lastFoodAdded = food;
        }
    }

    public double getDietCaloriesIntake()
    {
        double calories = 0;

        for (int nameOfMeal : meals.keySet())
        {
            Meal meal = meals.get(nameOfMeal);

            calories += meal.getCalories();
        }

        return calories;
    }

    public double getCaloriesFromMeal(int nameOfMeal)
    {
        return meals.get(nameOfMeal).getCalories();
    }

    public void setCaloriesTarget(int bmr, int weeklyGoal)
    {
        switch (weeklyGoal)
        {
            case (User.GAIN_250G):
                totalCaloriesTarget = bmr + 500;
                break;
            case (User.GAIN_500G):
                totalCaloriesTarget = bmr + 750;
                break;
            case (User.LOSE_250G):
                totalCaloriesTarget = bmr - 200;
                break;
            case (User.LOSE_500G):
                totalCaloriesTarget = bmr - 300;
                break;
            case (User.LOSE_750G):
                totalCaloriesTarget = bmr - 400;
                break;
            case (User.LOSE_1KG):
                totalCaloriesTarget = bmr - 500;
                break;
        }
    }

    public void setCaloriesTarget(double target)
    {
        if (target > 0)
        {
            totalCaloriesTarget = target;
        }
    }

    public double getCaloriesTarget()
    {
        return totalCaloriesTarget;
    }

    public HashMap<Integer, Meal> getAllMeals()
    {
        return meals;
    }

    // Constants
    public static final int LOSE_WEIGHT = 0;
    public static final int MAINTAIN_WEIGHT = 1;
    public static final int GAIN_WEIGHT = 2;

}
