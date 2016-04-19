package com.example.android.buddybuilding.Diet;

import com.example.android.buddybuilding.User.User;

/**
 * Created by Alessandro on 19/04/2016.
 */
public class DietToGain extends Diet{
    public DietToGain()
    {

    }

    public DietToGain(final DietToGain diet)
    {
        super(diet);
    }

    public void setCaloriesTarget(int bmr, int weeklyGoal)
    {
        switch (weeklyGoal)
        {
            case (User.GAIN_250G):
                totalCaloriesTarget = bmr + 500;
                break;
            case (User.GAIN_500G):
                totalCaloriesTarget = bmr + 750;
                break;
            default:
                System.out.println("Error in setCaloriesTarget | DietToGain");
                totalCaloriesTarget = -1;
                break;
        }
    }

}
