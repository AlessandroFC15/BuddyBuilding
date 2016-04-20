package com.example.android.buddybuilding.Diet;

import com.example.android.buddybuilding.User.User;

public class DietLowCarbs extends DietToLose {
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

}
