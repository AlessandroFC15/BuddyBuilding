package com.example.android.buddybuilding.Diet;

import com.example.android.buddybuilding.Food.Food;
import com.example.android.buddybuilding.User.User;

import java.util.HashMap;

public class DietLowCarbs extends DietToLose {
    private static HashMap<String, Food> lowCarbsFoods;
    static {
        lowCarbsFoods = new HashMap<>();
        lowCarbsFoods.put("Fuck It", new Food("Fuck It"));
        lowCarbsFoods.put("Stockton", new Food("Stockton"));
        lowCarbsFoods.put("209", new Food("209"));
    }

    public DietLowCarbs()
    {
        super();
    }

    public DietLowCarbs(int bmr, User.WeeklyGoal weeklyGoal)
    {
        super(bmr, weeklyGoal);
    }

    public DietLowCarbs(final DietLowCarbs diet)
    {
        super(diet);
    }

    public String toString()
    {
        return ".: DietLowCarbs :." + super.toString();
    }

}
