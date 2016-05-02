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

        setMacrosTarget();
    }

    public DietToLose(final DietToLose diet) {
        super(diet);
    }

    public String toString() {
        return super.toString();
    }



}
