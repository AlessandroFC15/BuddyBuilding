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

}
