package com.example.android.buddybuilding.Diet;

import com.example.android.buddybuilding.Food.Food;
import com.example.android.buddybuilding.User.User;

import java.util.HashMap;

/**
 * Created by Alessandro on 19/04/2016.
 */
public class DietLowFat extends DietToLose {

    private static HashMap<String, Food> lowFatFoods;
    static {
        lowFatFoods = new HashMap<>();
        lowFatFoods.put("Fuck It", new Food("Fuck It"));
        lowFatFoods.put("Stockton", new Food("Stockton"));
        lowFatFoods.put("209", new Food("209"));
    }

    public DietLowFat()
    {
        super();
    }

    public DietLowFat(int bmr, User.WeeklyGoal weeklyGoal)
    {
        super(bmr, weeklyGoal);
    }

    public DietLowFat(final DietLowCarbs diet)
    {
        super(diet);
    }

    public String toString()
    {
        return ".: DietLowFat :." + super.toString();
    }
}
