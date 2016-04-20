package com.example.android.buddybuilding.Diet;

import com.example.android.buddybuilding.User.User;

/**
 * Created by Alessandro on 19/04/2016.
 */
public class DietLowFat extends DietToLose {
    public DietLowFat()
    {
        super();
    }

    public DietLowFat(int bmr, User.WeeklyGoal weeklyGoal)
    {
        super(bmr, weeklyGoal);
    }

    public DietLowFat(final DietLowCarbs diet)
    {
        super(diet);
    }
}
