package com.example.android.buddybuilding.Diet;

import com.example.android.buddybuilding.User.User;

/**
 * Created by Alessandro on 19/04/2016.
 */
public abstract class DietToLose extends Diet {
    public DietToLose() {
        super();
    }

    public DietToLose(int bmr, User.WeeklyGoal weeklyGoal) {
        setCaloriesTarget(bmr, weeklyGoal);
    }

    public DietToLose(final DietToLose diet) {
        super(diet);
    }

    public void setCaloriesTarget(int bmr, User.WeeklyGoal weeklyGoal) {
        switch (weeklyGoal) {
            case LOSE_250G:
                totalCaloriesTarget = bmr + weeklyGoal.getCalories();
                break;
            case LOSE_500G:
                totalCaloriesTarget = bmr + weeklyGoal.getCalories();
                break;
            case LOSE_750G:
                totalCaloriesTarget = bmr + weeklyGoal.getCalories();
                break;
            case LOSE_1KG:
                totalCaloriesTarget = bmr + weeklyGoal.getCalories();
            default:
                System.out.println("Error in setCaloriesTarget | DietToLose");
                totalCaloriesTarget = -1;
                break;
        }
    }

    public String toString()
    {
        return super.toString();
    }
}
