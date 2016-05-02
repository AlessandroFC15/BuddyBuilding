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
        recommendedFoods.put("Cottage Cheese", new Food("Cottage Cheese", 50, 6.2, 1.1, 1.8));
        recommendedFoods.put("Fat-free Yogurt", new Food("Fat-free Yogurt", 160, 5.8, 7.8, 0));
        recommendedFoods.put("Fat-free milk", new Food("Fat-free milk", 20, 6.7, 10, 0));
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
