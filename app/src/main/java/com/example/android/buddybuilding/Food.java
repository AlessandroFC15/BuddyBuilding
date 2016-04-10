package com.example.android.buddybuilding;

/**
 * Created by Alessandro on 05/04/2016.
 */
public class Food {
    public static final int MIN_SERVING_SIZE = 1;
    public static final int MAX_SERVING_SIZE = 1000;
    public static final int MAX_LENGTH_NAME = 25;

    String name;
    double protein;
    double carbohydrates;
    double totalFat;
    int calories;
    int servingSize;

    Food(String name, int servingSize, double protein, double carbohydrates, double totalFat)
    {
        this.servingSize = servingSize;
        this.name = name;
        this.protein = protein;
        this.carbohydrates = carbohydrates;
        this.totalFat = totalFat;

        calculateCalories();
    }

    private void calculateCalories()
    {
        calories = (int) ((9 * getTotalFat()) + (4 * getProtein()) + (4 * getCarbs()));
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

    public int getCalories() { return calories; }

    public String getName() {
        return name;
    }

    public int getServingSize() {return servingSize; }

}
