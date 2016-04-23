package com.example.android.buddybuilding.Diet;

import com.example.android.buddybuilding.CaloriesMeasurable;
import com.example.android.buddybuilding.Food.Food;
import com.example.android.buddybuilding.Helper;
import com.example.android.buddybuilding.Meals.Breakfast;
import com.example.android.buddybuilding.Meals.Dinner;
import com.example.android.buddybuilding.Meals.Lunch;
import com.example.android.buddybuilding.Meals.Meal;
import com.example.android.buddybuilding.Meals.Snacks;
import com.example.android.buddybuilding.User.User;

import java.util.HashMap;

public abstract class Diet implements CaloriesMeasurable {
    protected int totalCaloriesTarget;
    private int totalCaloriesIntake;
    private double proteinIntake;
    private double carbsIntake;
    private double fatIntake;
    private HashMap<Integer, Meal> meals = new HashMap<>();

    private Food lastFoodAdded = null;
    private int lastMealChanged;

    private static final int maxCaloriesTarget = 5000;
    protected static HashMap<String, Food> recommendedFoods;

    public Diet()
    {
        setAttributesToDefault();

        addDefaultMeals();
    }

    public Diet(int caloriesTarget)
    {
        setAttributesToDefault();

        addDefaultMeals();

        totalCaloriesTarget = (int) Helper.validateValue(caloriesTarget, 0, maxCaloriesTarget);
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

        calculateCalories();
    }

    public void addFoodToMeal(int nameOfmeal, Food food)
    {
        if (nameOfmeal >= Meal.BREAKFAST && nameOfmeal <= Meal.SNACKS)
        {
            Meal meal = meals.get(nameOfmeal);

            meal.addFood(food);

            totalCaloriesIntake += meal.getCalories();

            lastMealChanged = nameOfmeal;
            lastFoodAdded = food;
        }
    }

    public int getCaloriesFromMeal(int nameOfMeal)
    {
        return meals.get(nameOfMeal).getCalories();
    }

    public void setCaloriesTarget(int bmr, User.WeeklyGoal weeklyGoal)
    {
        switch (weeklyGoal)
        {
            case GAIN_250G:
                totalCaloriesTarget = bmr + weeklyGoal.getCalories();
                break;
            case GAIN_500G:
                totalCaloriesTarget = bmr + weeklyGoal.getCalories();
                break;
            case LOSE_250G:
                totalCaloriesTarget = bmr + weeklyGoal.getCalories();
                break;
            case LOSE_500G:
                totalCaloriesTarget = bmr + weeklyGoal.getCalories();
                break;
            case LOSE_750G:
                totalCaloriesTarget = bmr + weeklyGoal.getCalories();
                break;
            case LOSE_1KG:
                totalCaloriesTarget = bmr + weeklyGoal.getCalories();
                break;
        }
    }

    public void setCaloriesTarget(int target)
    {
        if (target > 0)
        {
            totalCaloriesTarget = target;
        }
    }

    public int getCaloriesTarget()
    {
        return totalCaloriesTarget;
    }

    public HashMap<Integer, Meal> getAllMeals()
    {
        return meals;
    }

    // Implementação da interface CaloriesMeasurable

    public void calculateCalories()
    {
        totalCaloriesIntake = 0;

        for (int nameOfMeal : meals.keySet())
        {
            totalCaloriesIntake += meals.get(nameOfMeal).getCalories();
        }
    }

    public int getCalories() { return totalCaloriesIntake;}

    public String toString()
    {
        String output;

        output = "\n>> Calories Target: " + totalCaloriesTarget +
                "\n>> Calories Intake: " + totalCaloriesIntake +
                "\n>> Protein Intake: " + proteinIntake +
                "\n>> Carbs Intake: " + carbsIntake +
                "\n>> Fat Intake: " + fatIntake +
                "\n>> Number of Meals: " + meals.size();

        return output;
    }

    public Food getLastFoodAdded() {
        return lastFoodAdded;
    }

    public void resetLastFoodAdded()
    {
        lastFoodAdded = null;
    }

    public int getLastMealChanged()
    {
        return lastMealChanged;
    }

    public static HashMap<String, Food> getRecommendedFoods() {return recommendedFoods; }
}
