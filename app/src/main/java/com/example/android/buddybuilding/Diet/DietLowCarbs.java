package com.example.android.buddybuilding.Diet;

import com.example.android.buddybuilding.Food.Food;
import com.example.android.buddybuilding.User.User;

import java.util.HashMap;

public class DietLowCarbs extends DietToLose {
    static {
        recommendedFoods = new HashMap<>();
        recommendedFoods.put("Fuck It", new Food("Fuck It"));
        recommendedFoods.put("Stockton", new Food("Stockton"));
        recommendedFoods.put("209", new Food("209"));
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

    public static void addFoodToLowCarbsFoods(Food food)
    {
        if (! recommendedFoods.containsValue(food))
        {
            recommendedFoods.put(food.getName(), food);
        }
    }

    public static void removeFoodFromLowCarbsFoods(String nameOfFood)
    {
        if (! recommendedFoods.containsKey(nameOfFood))
        {
            recommendedFoods.remove(nameOfFood);
        }
    }

    /*
     Em DietLowCarbs, a divisão dos macros será feita da seguinte maneira.
     30% | Carbs
     40% | Protein
     30% | Fat
     */

    protected void setMacrosTarget()
    {
        double carbsCalories = totalCaloriesTarget / 30.0;
        double proteinCalories = totalCaloriesTarget / 40.0;
        double fatCalories = totalCaloriesTarget / 30.0;

        carbsTarget = carbsCalories / 4;
        proteinTarget = proteinCalories / 4;
        fatTarget = fatCalories / 9;
    }
}
