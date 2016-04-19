package com.example.android.buddybuilding.Meals;

import com.example.android.buddybuilding.Interfaces.CaloriesMeasurable;
import com.example.android.buddybuilding.Food;

import java.util.ArrayList;

/**
 * Created by Alessandro on 06/04/2016.
 */
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
        foods.add(newFood);

        this.totalProtein += newFood.getProtein();
        this.totalCarbs += newFood.getCarbs();
        this.totalCalories += newFood.getCalories();
        this.totalFat += newFood.getTotalFat();
    }

    public int getName()
    {
        return name;
    }

    public ArrayList<Food> getFoodsFromMeal()
    {
        return foods;
    }

    public int compareTo(Meal meal)
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

    public boolean equals(Object object)
    {
        if (object instanceof Meal)
        {
            return (totalCalories == ((Meal) object).totalCalories);
        } else
        {
            return false;
        }
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
}
