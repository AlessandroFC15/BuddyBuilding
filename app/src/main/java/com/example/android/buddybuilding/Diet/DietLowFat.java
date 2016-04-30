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

        carbsPercentage = 55;
        proteinPercentage = 35;
        fatPercentage = 10;

        setMacrosTarget();
    }

    public DietLowFat(int bmr, User.WeeklyGoal weeklyGoal)
    {
        super(bmr, weeklyGoal);

        carbsPercentage = 55;
        proteinPercentage = 35;
        fatPercentage = 10;

        setMacrosTarget();
    }

    public DietLowFat(final DietLowCarbs diet)
    {
        super(diet);
    }

    public String toString()
    {
        return ".: DietLowFat :." + super.toString();
    }

    public void setCaloriesTarget(int bmr, User.WeeklyGoal weeklyGoal) {
        if (weeklyGoal.isGoalToLose()) {
            totalCaloriesTarget = bmr + weeklyGoal.getCalories();
        } else {
            totalCaloriesTarget = -1;
        }
    }

    /*
     Em DietLowFat, a divisão dos macros será feita da seguinte maneira.
     55% | Carbs
     35% | Protein
     10% | Fat
     */
}
