package com.example.android.buddybuilding.Diet;

import com.example.android.buddybuilding.User.User;

/**
 * Created by Alessandro on 19/04/2016.
 */
public class DietToGain extends Diet{
    public DietToGain()
    {
        super();
    }

    public DietToGain(int bmr, User.WeeklyGoal weeklyGoal)
    {
        setCaloriesTarget(bmr, weeklyGoal);

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

    /*
     Em DietToGain, a divisão dos macros será feita da seguinte maneira.
     50% | Carbs
     30% | Protein
     20% | Fat
     */

    protected void setMacrosTarget()
    {
        double carbsCalories = (totalCaloriesTarget * 50.0) / 100.0;
        double proteinCalories = (totalCaloriesTarget * 30.0) / 100.0;
        double fatCalories = (totalCaloriesTarget * 20.0) / 100.0;

        carbsTarget = carbsCalories / 4;
        proteinTarget = proteinCalories / 4;
        fatTarget = fatCalories / 9;
    }

}
