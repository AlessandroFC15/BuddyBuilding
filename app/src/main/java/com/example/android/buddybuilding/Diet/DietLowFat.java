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

    /*
     Em DietLowFat, a divisão dos macros será feita da seguinte maneira.
     55% | Carbs
     35% | Protein
     10% | Fat
     */

    protected void setMacrosTarget()
    {
        double carbsCalories = totalCaloriesTarget / 55.0;
        double proteinCalories = totalCaloriesTarget / 35.0;
        double fatCalories = totalCaloriesTarget / 10.0;

        carbsTarget = carbsCalories / 4;
        proteinTarget = proteinCalories / 4;
        fatTarget = fatCalories / 9;
    }
}
