package com.example.android.buddybuilding.User;

public interface PhysicallyActive {

    // Constants
    public static final int NOT_VERY_ACTIVE = 0;
    public static final int LIGHTLY_ACTIVE = 1;
    public static final int ACTIVE = 2;
    public static final int VERY_ACTIVE = 3;

    public void setActivityLevel(int choice);

    public int getActivityLevel();
}