package com.example.android.buddybuilding.Diet;

import com.example.android.buddybuilding.Food.Food;
import com.example.android.buddybuilding.User.User;

import java.util.HashMap;

/**
 * Created by Alessandro on 19/04/2016.
 */
public class DietLowCalorie extends DietToLose{
    static {
        recommendedFoods = new HashMap<>();
        recommendedFoods.put("Fuck It", new Food("Fuck It"));
        recommendedFoods.put("Stockton", new Food("Stockton"));
        recommendedFoods.put("209", new Food("209"));
    }

    public DietLowCalorie()
    {
        super();

        carbsPercentage = 50;
        proteinPercentage = 30;
        fatPercentage = 20;

        setMacrosTarget();
    }

    public DietLowCalorie(int bmr, User.WeeklyGoal weeklyGoal)
    {
        super(bmr, weeklyGoal);

        carbsPercentage = 50;
        proteinPercentage = 30;
        fatPercentage = 20;

        setMacrosTarget();
    }

    public DietLowCalorie(final DietLowCarbs diet)
    {
        super(diet);
    }

    public String toString()
    {
        return ".: DietLowCalorie :." + super.toString();
    }

    public void setCaloriesTarget(int bmr, User.WeeklyGoal weeklyGoal) {
        if (weeklyGoal.isGoalToLose()) {
            totalCaloriesTarget = bmr + weeklyGoal.getCalories() - 200;
        } else {
            totalCaloriesTarget = -1;
        }
    }

    /*
     Em DietLowCalorie, a divisão dos macros será feita da seguinte maneira.
     50% | Carbs
     30% | Protein
     20% | Fat
     */
}
