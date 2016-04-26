package com.example.android.buddybuilding.Diet;

import com.example.android.buddybuilding.Food.Food;
import com.example.android.buddybuilding.User.User;

import java.util.HashMap;

/**
 * Created by Alessandro on 19/04/2016.
 */
public class DietLowFat extends DietToLose {
    static {
        recommendedFoods = new HashMap<>();
        recommendedFoods.put("Fuck It", new Food("Fuck It"));
        recommendedFoods.put("Stockton", new Food("Stockton"));
        recommendedFoods.put("209", new Food("209"));
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

    public void setMacroDistribution()
    {

    }
}
