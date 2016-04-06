package com.example.android.buddybuilding;

import java.util.ArrayList;

/**
 * Created by Alessandro on 06/04/2016.
 */
public class Diet {

    ArrayList<Meal> meals = new ArrayList<>();

    Diet()
    {
        addDefaultMeals();
    }

    private void addDefaultMeals()
    {
        meals.add(new Meal("Breakfast"));
        meals.add(new Meal("Lunch"));
        meals.add(new Meal("Dinner"));
        meals.add(new Meal("Snacks"));
    }

}
