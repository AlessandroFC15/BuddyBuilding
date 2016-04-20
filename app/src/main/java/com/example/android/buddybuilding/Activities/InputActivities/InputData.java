package com.example.android.buddybuilding.Activities.InputActivities;

import com.example.android.buddybuilding.User.Person;
import com.example.android.buddybuilding.User.User;

/**
 * Created by Alessandro on 19/04/2016.
 */
public class InputData {
    private static InputData inputData = new InputData();

    public static InputData getInstance() {
        return inputData;
    }

    public User.Goal goal;
    public User.WeeklyGoal weeklyGoal;
    public Person.ActivityLevel activityLevel;
    public Person.Gender gender;
    public int age;
    public double height;
    public double weight;

    private InputData() {

    }


}
