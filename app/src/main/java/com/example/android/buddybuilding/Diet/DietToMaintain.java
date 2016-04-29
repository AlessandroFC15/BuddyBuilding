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

    /*
     Em DietToMaintain, a divisão dos macros será feita da seguinte maneira.
     50% | Carbs
     30% | Protein
     20% | Fat
     */

    protected void setMacrosTarget()
    {
        double carbsCalories = totalCaloriesTarget / 50.0;
        double proteinCalories = totalCaloriesTarget / 30.0;
        double fatCalories = totalCaloriesTarget / 20.0;

        carbsTarget = carbsCalories / 4;
        proteinTarget = proteinCalories / 4;
        fatTarget = fatCalories / 9;
    }
}
