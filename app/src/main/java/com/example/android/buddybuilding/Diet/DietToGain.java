package com.example.android.buddybuilding.Diet;

import com.example.android.buddybuilding.User.User;

/**
 * Created by Alessandro on 19/04/2016.
 */
public class DietToGain extends Diet{
    /*
     Em DietToGain, a divisão dos macros será feita da seguinte maneira.
     50% | Carbs
     30% | Protein
     20% | Fat
     */

    public DietToGain()
    {
        super();

        carbsPercentage = 50;
        proteinPercentage = 30;
        fatPercentage = 20;

        setMacrosTarget();
    }

    public DietToGain(int bmr, User.WeeklyGoal weeklyGoal)
    {
        setCaloriesTarget(bmr, weeklyGoal);

        carbsPercentage = 50;
        proteinPercentage = 30;
        fatPercentage = 20;

        setMacrosTarget();
    }

    public DietToGain(final DietToGain diet)
    {
        super(diet);
    }

    public void setCaloriesTarget(int bmr, User.WeeklyGoal weeklyGoal)
    {
        switch (weeklyGoal)
        {
            case GAIN_250G:
                totalCaloriesTarget = bmr + weeklyGoal.getCalories();
                break;
            case GAIN_500G:
                totalCaloriesTarget = bmr + weeklyGoal.getCalories();
                break;
            default:
                System.out.println("Error in setCaloriesTarget | DietToGain");
                totalCaloriesTarget = -1;
                break;
        }
    }

    public String toString()
    {
        return ".: DietToGain :." + super.toString();
    }
}
