package com.example.android.buddybuilding;

import java.util.HashMap;

/**
 * Created by Alessandro on 06/04/2016.
 */
public class Diet {

    private int goal;

    private double totalCaloriesTarget;
    private double totalCaloriesIntake;
    private double proteinIntake;
    private double carbsIntake;
    private double fatIntake;

    HashMap<Integer, Meal> meals = new HashMap<>();

    Diet()
    {
        goal = GAIN_WEIGHT;
        totalCaloriesTarget = 0;
        totalCaloriesIntake = 0;
        proteinIntake = 0;
        carbsIntake = 0;
        fatIntake = 0;
        addDefaultMeals();
    }

    public void setDietGoal(int choice)
    {
        if ((choice >= LOSE_WEIGHT) && (choice <= GAIN_WEIGHT))
        {
            goal = choice;
        }
    }

    public int getDietGoal()
    {
        return goal;
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

    public double getDietCaloriesIntake()
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
