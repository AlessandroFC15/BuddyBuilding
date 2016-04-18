package com.example.android.buddybuilding;

import java.util.ArrayList;

/**
 * Created by Alessandro on 06/04/2016.
 */
public class Meal implements Comparable<Meal>{

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

    ArrayList<Food> foods = new ArrayList<>();

    Meal()
    {
        name = GENERIC_MEAL;
        totalProtein = 0;
        totalCarbs = 0;
        totalCalories = 0;
        totalFat = 0;
    }

    Meal(int nameOfMeal)
    {
        name = nameOfMeal;
        totalProtein = 0;
        totalCarbs = 0;
        totalCalories = 0;
        totalFat = 0;
    }

    public void addFood(String name, int serving, double protein, double carbohydrates, double totalFat)
    {
        Food newFood = new Food(name, serving, protein, carbohydrates, totalFat);

        foods.add(newFood);

        this.totalProtein += newFood.getProtein();
        this.totalCarbs += newFood.getCarbs();
        this.totalCalories += newFood.getCalories();
        this.totalFat += newFood.getTotalFat();
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

    public int getMealTotalCalories()
    {
        return totalCalories;
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
}
