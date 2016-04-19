package com.example.android.buddybuilding.User;

import com.example.android.buddybuilding.Diet.Diet;
import com.example.android.buddybuilding.Diet.DietToGain;
import com.example.android.buddybuilding.Diet.DietToLose;
import com.example.android.buddybuilding.Diet.DietToMaintain;
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

    protected int goal;
    private int weeklyGoal;
    private Diet diet;

    // Construtores

    User()
    {
        weeklyGoal = GAIN_250G;

        setCaloriesTarget();
    }

    User(int goal)
    {

    }

    User(int goal, int weeklyGoal)
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

    public Diet getDiet()
    {
        return diet;
    }

    public void setGoal(int choice)
    {
        diet = getCorrectDiet(choice);

        // If you changed your goal, we must update your calories target
        setCaloriesTarget();
    }

    private Diet getCorrectDiet(int goal)
    {
        switch (goal)
        {
            case (Diet.LOSE_WEIGHT):
                return new DietToLose();
            case (Diet.MAINTAIN_WEIGHT):
                return new DietToMaintain();
            case (Diet.GAIN_WEIGHT):
                return new DietToGain();
            default:
                System.out.println("Error in getCorrectDiet | User");
                return null;
        }
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
        if (getGoal() == Diet.MAINTAIN_WEIGHT)
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
        return Diet.lastFoodAdded != null;
    }

    public int getMealChanged()
    {
        return Diet.mealChanged;
    }

    public void setDietGoal(int choice)
    {
        if ((choice >= Diet.LOSE_WEIGHT) && (choice <= Diet.GAIN_WEIGHT))
        {
            goal = choice;
        }
    }

    public int getGoal()
    {
        return goal;
    }
}
