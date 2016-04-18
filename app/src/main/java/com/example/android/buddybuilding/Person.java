package com.example.android.buddybuilding;

/**
 * Created by Alessandro on 04/04/2016.
 */
public abstract class Person implements Comparable<Person>, PhysicallyActive{

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
    protected int activityLevel;

    Person()
    {
        gender = Person.MALE;
        age = 20;
        height = 165;
        weight = 60;
        activityLevel = NOT_VERY_ACTIVE;
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

    public int getBMR()
    {
        return 32 * (int) getWeight();
    }

    public int compareTo(Person person)
    {
        if (weight > person.weight)
        {
            return 1;
        } else if (weight == person.weight)
        {
            return 0;
        } else
        {
            return -1;
        }
    }

    // Implementação da interface PhysicallyActive

    public void setActivityLevel(int choice)
    {
        if ((choice >= NOT_VERY_ACTIVE) && (choice <= VERY_ACTIVE))
        {
            activityLevel = choice;
        }
    }

    public int getActivityLevel(){
        return activityLevel;
    }
}
