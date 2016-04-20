package com.example.android.buddybuilding.Diet;

import com.example.android.buddybuilding.User.User;

/**
 * Created by Alessandro on 19/04/2016.
 */
public class DietLowCalorie extends DietToLose{
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
}
