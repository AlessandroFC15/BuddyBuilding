package com.example.android.buddybuilding.Diet;

import com.example.android.buddybuilding.User.User;

/**
 * Created by Alessandro on 19/04/2016.
 */
public class DietToMaintain extends Diet {
    /*
     Em DietToMaintain, a divisão dos macros será feita da seguinte maneira.
     50% | Carbs
     30% | Protein
     20% | Fat
     */

    public DietToMaintain()
    {
        super();

        carbsPercentage = 50;
        proteinPercentage = 30;
        fatPercentage = 20;

        setMacrosTarget();
    }

    public DietToMaintain(int bmr)
    {
        setCaloriesTarget(bmr, User.WeeklyGoal.MAINTAIN);

        carbsPercentage = 50;
        proteinPercentage = 30;
        fatPercentage = 20;

        setMacrosTarget();
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
