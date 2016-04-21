package com.example.android.buddybuilding.Meals;

import com.example.android.buddybuilding.Food.Food;

import java.util.ArrayList;

/**
 * Created by Alessandro on 18/04/2016.
 */
public class Breakfast extends Meal {

    private static ArrayList<Food> commonBreakfastFoods = new ArrayList<>();

    public Breakfast()
    {
        name = BREAKFAST;

        addCommonBreakfastFoods();

        // addDefaultFood();
    }

    public Breakfast(Food food)
    {
        super(food);

        name = BREAKFAST;

        addCommonBreakfastFoods();
    }

    public Breakfast(final Breakfast breakfast)
    {
        super(breakfast);

        addCommonBreakfastFoods();
    }

    private void addDefaultFood()
    {
        Food food = new Food("Pão", 50, 2, 35, 5);

        addFood(food);

        addCommonBreakfastFoods();
    }

    public String toString()
    {
        return ".: BREAKFAST :." + super.toString();
    }

    private void addCommonBreakfastFoods()
    {
        addPaoFrances();
        addQueijo();
    }

    private void addPaoFrances()
    {
        int servingSize = 50;
        double protein = 4.73;
        double carbs = 25.33;
        double fat = 1.98;

        Food food = new Food("Pão Francês", servingSize, protein, carbs, fat);

        commonBreakfastFoods.add(food);
    }

    private void addQueijo()
    {
        int servingSize = 25;
        double protein = 5.65;
        double carbs = 0.75;
        double fat = 6.3;

        Food food = new Food("Queijo Mussarela", servingSize, protein, carbs, fat);

        commonBreakfastFoods.add(food);
    }

    public static ArrayList<Food> getCommonBreakfastFoods()
    {
        return commonBreakfastFoods;
    }
}
