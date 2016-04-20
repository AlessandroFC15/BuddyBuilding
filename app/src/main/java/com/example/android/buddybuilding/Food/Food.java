package com.example.android.buddybuilding.Food;

import com.example.android.buddybuilding.CaloriesMeasurable;

/**
 * Created by Alessandro on 05/04/2016.
 */
public class Food implements Comparable<Food>, CaloriesMeasurable, Adjustable {
    public static final int MIN_SERVING_SIZE = 1;
    public static final int MAX_SERVING_SIZE = 1000;
    public static final int MAX_LENGTH_NAME = 25;

    private String name;
    private double protein;
    private double carbohydrates;
    private double totalFat;
    private int calories;
    private int servingSize;

    public Food()
    {
        name = "";
        protein = 0;
        carbohydrates = 0;
        totalFat = 0;
        calories = 0;
        servingSize = 0;
    }

    public Food(String name, int servingSize, double protein, double carbohydrates, double totalFat)
    {
        this.servingSize = servingSize;
        this.name = name;
        this.protein = protein;
        this.carbohydrates = carbohydrates;
        this.totalFat = totalFat;

        calculateCalories();
    }

    public Food(final Food food)
    {
        name = food.name;
        protein = food.protein;
        carbohydrates = food.carbohydrates;
        totalFat = food.totalFat;
        calories = food.calories;
        servingSize = food.servingSize;
    }

    public double getProtein()
    {
        return protein;
    }

    public double getCarbs()
    {
        return carbohydrates;
    }

    public double getTotalFat()
    {
        return totalFat;
    }

    public String getName() {
        return name;
    }

    public int getServingSize() {return servingSize; }

    /*
     * The method compareTo will take into account the amount of calories present in each food
     */

    public int compareTo(Food food)
    {
        if (calories >= food.calories)
        {
            return 1;
        } else if (calories == food.calories)
        {
            return 0;
        } else
        {
            return -1;
        }
    }

    public boolean equals(Object object)
    {
        if (object instanceof Food)
        {
            return ((protein == ((Food) object).protein) &&
                    (carbohydrates == ((Food) object).carbohydrates) &&
                    (totalFat == ((Food) object).totalFat) &&
                    (servingSize == ((Food) object).servingSize));
        } else
        {
            return false;
        }
    }

    // Implementação da interface CaloriesMeasurable

    public void calculateCalories()
    {
        calories = (int) ((9 * getTotalFat()) + (4 * getProtein()) + (4 * getCarbs()));
    }

    public int getCalories() { return calories; }

    // Implementação da interface Adjustable

    public void changeProtein(double newValue)
    {
        if ((newValue >= 0) && (newValue <= servingSize))
        {
            protein = newValue;
        }
    }

    public void changeCarbs(double newValue)
    {
        if ((newValue >= 0) && (newValue <= servingSize))
        {
            carbohydrates = newValue;
        }
    }

    public void changeFat(double newValue)
    {
        if ((newValue >= 0) && (newValue <= servingSize))
        {
            totalFat = newValue;
        }
    }

    public void changeServingSize(int newValue)
    {
        if ((newValue >= MIN_SERVING_SIZE) && (newValue <= MAX_SERVING_SIZE))
        {
            servingSize = newValue;
        }
    }

    public void changeName(final String newValue)
    {
        if ((newValue.length() > 0) && (newValue.length() <= MAX_LENGTH_NAME))
        {
            name = newValue;
        }
    }

}
