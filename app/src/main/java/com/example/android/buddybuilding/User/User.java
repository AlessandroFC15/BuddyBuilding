package com.example.android.buddybuilding.User;

import com.example.android.buddybuilding.Diet.Diet;
import com.example.android.buddybuilding.Food;
import com.example.android.buddybuilding.Helper;
import com.example.android.buddybuilding.Meals.Meal;

import java.util.HashMap;

/**
 * Created by Alessandro on 03/04/2016.
 */
public class User extends Person{

    private static final User userData = new User();
    public static User getInstance() {return userData;}

    // Constants
    public static final int GAIN_250G = 0;
    public static final int GAIN_500G = 1;
    public static final int LOSE_250G = 2;
    public static final int LOSE_500G = 3;
    public static final int LOSE_750G = 4;
    public static final int LOSE_1KG = 5;

    private int weeklyGoal;
    private Diet diet = new Diet();

    // Construtores

    User()
    {
        weeklyGoal = GAIN_250G;

        setCaloriesTarget();
    }

    User(int weeklyGoal)
    {
        this.weeklyGoal = (int) Helper.validateValue(weeklyGoal, GAIN_250G, LOSE_1KG);

        setCaloriesTarget();
    }

    User(int gender, int age, double height, double weight, int activityLevel, int weeklyGoal)
    {
        super(gender, age, height, weight, activityLevel);

        this.weeklyGoal = (int) Helper.validateValue(weeklyGoal, GAIN_250G, LOSE_1KG);

        setCaloriesTarget();
    }

    User(final User user)
    {
        super(user);

        weeklyGoal = user.weeklyGoal;

        diet = user.diet;
    }

    // Fim dos Construtores

    public void setGoal(int choice)
    {
        diet.setDietGoal(choice);

        // If you changed your goal, we must update your calories target
        setCaloriesTarget();
    }

    public int getGoal()
    {
        return diet.getDietGoal();
    }

    public void setWeeklyGoal(int choice)
    {
        if ((choice >= GAIN_250G) && (choice <= LOSE_1KG))
        {
            weeklyGoal = choice;

            // If you change your weekly goal, you must change your calories target.
            setCaloriesTarget();
        }
    }

    public int getWeeklyGoal()
    {
        return weeklyGoal;
    }

    public void addFood(int nameOfMeal, Food food)
    {
        diet.addFoodToMeal(nameOfMeal, food);
    }

    public double getCaloriesFromMeal(int nameOfMeal)
    {
        return diet.getCaloriesFromMeal(nameOfMeal);
    }

    public void setCaloriesTarget()
    {
        // If the goal of the user is to maintain weight, then its calories goal is the
        // same as its BMR.
        if (diet.getDietGoal() == Diet.MAINTAIN_WEIGHT)
        {
            diet.setCaloriesTarget(getBMR());
        } else
        {
            diet.setCaloriesTarget(getBMR(), getWeeklyGoal());
        }
    }

    public double getCaloriesTarget()
    {
        return diet.getCaloriesTarget();
    }

    public double getCaloriesIntake()
    {
        return diet.getDietCaloriesIntake();
    }

    public HashMap<Integer, Meal> getAllMeals()
    {
        return diet.getAllMeals();
    }

    public boolean hasDietChanged()
    {
        return Diet.foodAdded;
    }

    public int getMealChanged()
    {
        return Diet.mealChanged;
    }
}
