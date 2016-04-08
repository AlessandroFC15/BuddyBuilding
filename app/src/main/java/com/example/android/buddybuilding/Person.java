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

    protected int gender;
    protected int age;
    protected double height;
    protected double weight;
    protected int bmr;
    protected int activityLevel;

    Person()
    {
        gender = Person.MALE;
        age = 20;
        height = 165;
        weight = 60;
        activityLevel = NOT_VERY_ACTIVE;
        setBMR();
    }

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

            setBMR(); // Update BMR
        }
    }

    public double getWeight()
    {
        return weight;
    }

    /*
    Calculo da Taxa Metabólica Basal
    Assumiu-se a formula 32 * kg
     */
    public void setBMR()
    {
        bmr = 32 * (int) getWeight();
    }

    public int getBMR()
    {
        return bmr;
    }

    public void setActivityLevel(int choice)
    {
        if ((choice >= NOT_VERY_ACTIVE) && (choice <= VERY_ACTIVE))
        {
            activityLevel = choice;
        }
    }

    // Constants
    public static final int NOT_VERY_ACTIVE = 0;
    public static final int LIGHTLY_ACTIVE = 1;
    public static final int ACTIVE = 2;
    public static final int VERY_ACTIVE = 3;

}
