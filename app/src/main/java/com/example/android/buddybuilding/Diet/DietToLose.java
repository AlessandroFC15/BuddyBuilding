package com.example.android.buddybuilding.Diet;

import com.example.android.buddybuilding.User.User;

/**
 * Created by Alessandro on 19/04/2016.
 */
public class DietToLose extends Diet {
    public DietToLose()
    {
    }

    public DietToLose(final DietToLose diet)
    {
        super(diet);
    }

    public void setCaloriesTarget(int bmr, int weeklyGoal)
    {
        switch (weeklyGoal)
        {
            case (User.LOSE_250G):
                totalCaloriesTarget = bmr - 200;
                break;
            case (User.LOSE_500G):
                totalCaloriesTarget = bmr - 300;
                break;
            case (User.LOSE_750G):
                totalCaloriesTarget = bmr - 400;
                break;
            case (User.LOSE_1KG):
                totalCaloriesTarget = bmr - 500;
            default:
                System.out.println("Error in setCaloriesTarget | DietToLose");
                totalCaloriesTarget = -1;
                break;
        }
    }
}
