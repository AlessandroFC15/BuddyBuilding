package com.example.android.buddybuilding;

/**
 * Created by Alessandro on 04/04/2016.
 */
public class Person {

    public static final double MIN_HEIGHT = 57;
    public static final double MAX_HEIGHT = 260;

    public static final double MIN_WEIGHT = 0;
    public static final double MAX_WEIGHT = 500;

    public static final int MIN_AGE = 0;
    public static final int MAX_AGE = 150;

    public static final int MALE = 0;
    public static final int FEMALE = 1;

    private int gender;
    private int age;
    private double height;
    private double weight;

    public void setGender(int choice)
    {
        if ((choice == MALE) || (choice == FEMALE))
        {
            gender = choice;
        }
    }

    public void setAge(int age)
    {
        if ((age >= MIN_AGE) || (age <= MAX_AGE))
        {
            this.age = age;
        }
    }

    public void setHeight(double height)
    {
        if ((height >= MIN_HEIGHT) || (height <= MAX_HEIGHT))
        {
            this.height = height;
        }
    }

    public void setWeight(double weight)
    {
        if ((weight >= MIN_WEIGHT) || (weight <= MAX_WEIGHT))
        {
            this.weight = weight;
        }
    }

}
