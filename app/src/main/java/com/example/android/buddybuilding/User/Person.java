package com.example.android.buddybuilding.User;

import com.example.android.buddybuilding.Activities.InputActivities.InputData;
import com.example.android.buddybuilding.Helper;

/**
 * Created by Alessandro on 04/04/2016.
 */
public abstract class Person implements Comparable<Person>, PhysicallyActive {

    public static final double MIN_HEIGHT = 57;
    public static final double MAX_HEIGHT = 260;

    public static final double MIN_WEIGHT = 0;
    public static final double MAX_WEIGHT = 500;

    public static final int MIN_AGE = 0;
    public static final int MAX_AGE = 150;

    public enum Gender {
        MALE, FEMALE
    }

    public enum ActivityLevel {
        NOT_VERY_ACTIVE("Not Very Active"),
        LIGHTLY_ACTIVE("Lightly Active"),
        ACTIVE("Active"),
        VERY_ACTIVE("Very Active");

        String description;

        ActivityLevel(String description) {
            this.description = description;
        }

        public String toString() {
            return description;
        }
    }

    protected Gender gender;
    protected int age;
    protected double height;
    protected double weight;
    protected ActivityLevel activityLevel;

    Person() {
        gender = Gender.MALE;
        age = 20;
        height = 165;
        weight = 60;
        activityLevel = ActivityLevel.NOT_VERY_ACTIVE;
    }

    Person(Gender gender, int age, double height, double weight, ActivityLevel activityLevel) {
        setGender(gender);
        this.age = (int) Helper.validateValue(age, MIN_AGE, MAX_AGE);
        this.height = Helper.validateValue(height, MIN_HEIGHT, MAX_HEIGHT);
        this.weight = Helper.validateValue(weight, MIN_WEIGHT, MAX_WEIGHT);
        this.activityLevel = activityLevel;
    }

    Person(final Person oldPerson) {
        gender = oldPerson.gender;
        age = oldPerson.age;
        height = oldPerson.height;
        weight = oldPerson.weight;
        activityLevel = oldPerson.activityLevel;
    }

    Person(final InputData input) {
        setGender(input.gender);
        age = (int) Helper.validateValue(input.age, MIN_AGE, MAX_AGE);
        height = Helper.validateValue(input.height, MIN_HEIGHT, MAX_HEIGHT);
        weight = Helper.validateValue(input.weight, MIN_WEIGHT, MAX_WEIGHT);
        this.activityLevel = input.activityLevel;

    }


    public boolean setGender(Gender choice) {
        if ((choice == Gender.MALE) || (choice == Gender.FEMALE)) {
            gender = choice;
            return true;
        }

        return false;
    }

    public void setAge(int age) {
        if ((age >= MIN_AGE) && (age <= MAX_AGE)) {
            this.age = age;
        }
    }

    public void setHeight(double height) {
        if ((height >= MIN_HEIGHT) && (height <= MAX_HEIGHT)) {
            this.height = height;
        }
    }

    public void setWeight(double weight) {
        if ((weight >= MIN_WEIGHT) && (weight <= MAX_WEIGHT)) {
            this.weight = weight;
        }
    }

    public double getWeight() {
        return weight;
    }

    /*
    Calculo da Taxa Metabólica Basal
    Assumiu-se a formula 32 * kg
     */

    public int getBMR() {
        return 32 * (int) getWeight();
    }

    public int compareTo(Person person) {
        if (weight > person.weight) {
            return 1;
        } else if (weight == person.weight) {
            return 0;
        } else {
            return -1;
        }
    }

    // Implementação da interface PhysicallyActive

    public void setActivityLevel(ActivityLevel choice) {
        activityLevel = choice;
    }

    public ActivityLevel getActivityLevel() {
        return activityLevel;
    }

    public String toString() {
        return ".: PERSON :." +
                "\n>> Gender: " + gender +
                "\n>> Age: " + age + " years" +
                "\n>> Height: " + height + " cm" +
                "\n>> Weight: " + weight + " kg" +
                "\n>> ActivityLevel: " + activityLevel.toString();
    }
}

