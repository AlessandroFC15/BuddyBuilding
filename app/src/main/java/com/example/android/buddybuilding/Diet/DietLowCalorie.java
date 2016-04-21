package com.example.android.buddybuilding.Diet;

import com.example.android.buddybuilding.Food.Food;
import com.example.android.buddybuilding.User.User;

import java.util.HashMap;

/**
 * Created by Alessandro on 19/04/2016.
 */
public class DietLowCalorie extends DietToLose{
    private static HashMap<String, Food> lowCalorieFoods;
    static {
        lowCalorieFoods = new HashMap<>();
        lowCalorieFoods.put("Fuck It", new Food("Fuck It"));
        lowCalorieFoods.put("Stockton", new Food("Stockton"));
        lowCalorieFoods.put("209", new Food("209"));
    }

    public DietLowCalorie()
    {
        super();
    }

    public DietLowCalorie(int bmr, User.WeeklyGoal weeklyGoal)
    {
        super(bmr, weeklyGoal);
    }

    public DietLowCalorie(final DietLowCarbs diet)
    {
        super(diet);
    }

    public String toString()
    {
        return ".: DietLowCalorie :." + super.toString();
    }
}
