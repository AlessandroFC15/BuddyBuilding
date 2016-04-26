package com.example.android.buddybuilding.Meals;

import android.support.annotation.NonNull;

import com.example.android.buddybuilding.CaloriesMeasurable;
import com.example.android.buddybuilding.Food.Food;

import java.util.ArrayList;

public abstract class Meal implements Comparable<Meal>, CaloriesMeasurable {

    public final static int BREAKFAST = 0;
    public final static int LUNCH = 1;
    public final static int DINNER = 2;
    public final static int SNACKS = 3;
    public final static int GENERIC_MEAL = 4;

    protected int name;
    protected double totalProtein;
    protected double totalCarbs;
    protected int totalCalories;
    protected double totalFat;
    protected ArrayList<Food> foods = new ArrayList<>();

    private static final int maxAmountOfFoods = 10;

    public Meal()
    {
        name = GENERIC_MEAL;
        totalProtein = 0;
        totalCarbs = 0;
        totalCalories = 0;
        totalFat = 0;
    }

    public Meal(Food food)
    {
        name = GENERIC_MEAL;
        totalProtein = 0;
        totalCarbs = 0;
        totalCalories = 0;
        totalFat = 0;

        addFood(food);
    }

    public Meal(final Meal meal)
    {
        name = meal.name;
        totalProtein = meal.totalProtein;
        totalCarbs = meal.totalCarbs;
        totalCalories = meal.totalCalories;
        totalFat = meal.totalFat;
        foods = meal.foods;
    }

    public void addFood(Food newFood)
    {
        if (foods.size() < maxAmountOfFoods)
        {
            foods.add(newFood);

            this.totalProtein += newFood.getProtein();
            this.totalCarbs += newFood.getCarbs();
            this.totalCalories += newFood.getCalories();
            this.totalFat += newFood.getTotalFat();
        }
    }

    public int getName()
    {
        return name;
    }

    public ArrayList<Food> getFoodsFromMeal()
    {
        return foods;
    }

    public int compareTo(@NonNull Meal meal)
    {
        if (totalCalories > meal.totalCalories)
        {
            return 1;
        } else if (totalCalories == meal.totalCalories)
        {
            return 0;
        } else
        {
            return -1;
        }
    }

    public boolean equals(Object object) {
        return object instanceof Meal && (totalCalories == ((Meal) object).totalCalories);
    }

    // Implementação da interface CaloriesMeasurable

    public void calculateCalories()
    {
        totalCalories = 0;

        for (Food food : foods)
        {
            totalCalories += food.getCalories();
        }
    }

    public int getCalories() { return totalCalories;}

    public String toString()
    {
        return "\n>> Protein: " + totalProtein +
                "\n>> Carbs: " + totalCarbs +
                "\n>> Fat: " + totalFat +
                "\n>> Calories: " + totalCalories +
                "\n>> Number of Foods: " + foods.size();
    }

    protected abstract void addDefaultFood();
}
