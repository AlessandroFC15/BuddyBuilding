package com.example.android.buddybuilding;

/**
 * Created by Alessandro on 03/04/2016.
 */
public class User {

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

    private int goal;
    private int activityLevel;

    public void setGoal(int choice)
    {
        if ((choice == LOSE_WEIGHT) || (choice == MAINTAIN_WEIGHT) || (choice == GAIN_WEIGHT))
        {
            goal = choice;
        }
    }

    public void setActivityLevel(int choice)
    {
        //if (choice )
    }
}
