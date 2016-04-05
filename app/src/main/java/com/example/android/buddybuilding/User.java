package com.example.android.buddybuilding;

/**
 * Created by Alessandro on 03/04/2016.
 */
public class User extends Person{

    private static final User userData = new User();
    public static User getInstance() {return userData;}

    // Constants
    public static final int LOSE_WEIGHT = 0;
    public static final int MAINTAIN_WEIGHT = 1;
    public static final int GAIN_WEIGHT = 2;

    public static final int NOT_VERY_ACTIVE = 0;
    public static final int LIGHTLY_ACTIVE = 1;
    public static final int ACTIVE = 2;
    public static final int VERY_ACTIVE = 3;

    public static final int GAIN_250G = 0;
    public static final int GAIN_500G = 1;
    public static final int LOSE_250G = 2;
    public static final int LOSE_500G = 3;
    public static final int LOSE_750G = 4;
    public static final int LOSE_1KG = 5;

    private int goal;
    private int activityLevel;
    private int weeklyGoal;

    public void setGoal(int choice)
    {
        if ((choice >= LOSE_WEIGHT) && (choice <= GAIN_WEIGHT))
        {
            goal = choice;
        }
    }

    public int getGoal()
    {
        return goal;
    }

    public void setActivityLevel(int choice)
    {
        if ((choice >= NOT_VERY_ACTIVE) && (choice <= VERY_ACTIVE))
        {
            activityLevel = choice;
        }
    }

    public void setWeeklyGoal(int choice)
    {
        if ((choice >= GAIN_250G) && (choice <= LOSE_1KG))
        {
            weeklyGoal = choice;
        }
    }

    public int getWeeklyGoal()
    {
        return weeklyGoal;
    }
}
