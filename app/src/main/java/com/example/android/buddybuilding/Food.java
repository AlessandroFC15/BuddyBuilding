package com.example.android.buddybuilding;

/**
 * Created by Alessandro on 05/04/2016.
 */
public class Food {
    String name;
    double protein;
    double carbohydrates;
    double calories;
    double totalFat;

    Food(String name, double protein, double carbohydrates, double totalFat)
    {
        this.name = name;
        this.protein = protein;
        this.carbohydrates = carbohydrates;
        this.totalFat = totalFat;

        calculateCalories();
    }

    private void calculateCalories()
    {
        calories = (9 * getTotalFat()) + (4 * getProtein()) + (4 * getCarbs());
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

    public double getCalories() { return calories; }

}
