package com.example.android.buddybuilding.Diet;

import com.example.android.buddybuilding.User.User;

/**
 * Created by Alessandro on 19/04/2016.
 */
public class DietToMaintain extends Diet {
    public DietToMaintain()
    {
    }

    public DietToMaintain(int bmr)
    {
        setCaloriesTarget(bmr, User.WeeklyGoal.MAINTAIN);
    }

    public DietToMaintain(final DietToMaintain diet)
    {
        super(diet);
    }

    public void setCaloriesTarget(int bmr, User.WeeklyGoal weeklyGoal)
    {
        totalCaloriesTarget = bmr;
    }

    public String toString()
    {
        return ".: DietToMaintain :." + super.toString();
    }
}
