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

    /*
     Em DietLowCalorie, a divisão dos macros será feita da seguinte maneira.
     50% | Carbs
     30% | Protein
     20% | Fat
     */

    protected void setMacrosTarget()
    {
        double carbsCalories = totalCaloriesTarget / 50.0;
        double proteinCalories = totalCaloriesTarget / 30.0;
        double fatCalories = totalCaloriesTarget / 20.0;

        carbsTarget = carbsCalories / 4;
        proteinTarget = proteinCalories / 4;
        fatTarget = fatCalories / 9;
    }
}
