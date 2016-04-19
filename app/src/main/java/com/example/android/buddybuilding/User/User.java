package com.example.android.buddybuilding.User;

import com.example.android.buddybuilding.Activities.InputActivities.InputData;
import com.example.android.buddybuilding.Diet.Diet;
import com.example.android.buddybuilding.Diet.DietToGain;
import com.example.android.buddybuilding.Diet.DietToLose;
import com.example.android.buddybuilding.Diet.DietToMaintain;
import com.example.android.buddybuilding.Food;
import com.example.android.buddybuilding.Meals.Meal;

import java.util.HashMap;

/**
 * Created by Alessandro on 03/04/2016.
 */
public class User extends Person{

    private static User userData = null;
    public static User getInstance() {return userData;}

    public static void createNewUser(final InputData input)
    {
        userData = new User(input);
    }

    public static void createNewUser()
    {
        userData = new User();
    }

    private User(final InputData input)
    {
        super(input);

        goal = input.goal;
        weeklyGoal = input.weeklyGoal;
        diet = getCorrectDiet(goal);
    }

    public enum WeeklyGoal{
        GAIN_250G(500), GAIN_500G(750), LOSE_250G(-200),
        LOSE_500G(-300), LOSE_750G(-400), LOSE_1KG(-500);

        private final int calories;

        WeeklyGoal(int calories)
        {
            this.calories = calories;
        }

        public int getCalories()
        {
            return calories;
        }
    }

    public enum Goal {
        LOSE_WEIGHT, MAINTAIN_WEIGHT, GAIN_WEIGHT
    }

    protected Goal goal;
    private WeeklyGoal weeklyGoal;
    private Diet diet;

    // Construtores

    User()
    {
        goal = Goal.GAIN_WEIGHT;
        weeklyGoal = WeeklyGoal.GAIN_250G;
        diet = getCorrectDiet(goal);
    }

    User(WeeklyGoal weeklyGoal)
    {
        this.weeklyGoal = weeklyGoal;

        setCaloriesTarget();
    }

    User(Gender gender, int age, double height, double weight, int activityLevel, WeeklyGoal weeklyGoal)
    {
        super(gender, age, height, weight, activityLevel);

        this.weeklyGoal = weeklyGoal;

        setCaloriesTarget();
    }

    User(final User user)
    {
        super(user);

        goal = user.goal;
        weeklyGoal = user.weeklyGoal;
        diet = user.diet;
    }

    // Fim dos Construtores

    public Diet getDiet()
    {
        return diet;
    }

    public void setGoal(Goal choice)
    {
        diet = getCorrectDiet(choice);

        // If you changed your goal, we must update your calories target
        setCaloriesTarget();
    }

    private Diet getCorrectDiet(Goal goal)
    {
        switch (goal)
        {
            case LOSE_WEIGHT:
                return new DietToLose(getBMR(), weeklyGoal);
            case MAINTAIN_WEIGHT:
                return new DietToMaintain(getBMR());
            case GAIN_WEIGHT:
                return new DietToGain(getBMR(), weeklyGoal);
            default:
                System.out.println("Error in getCorrectDiet | User");
                return null;
        }
    }

    public void setWeeklyGoal(WeeklyGoal choice)
    {
        weeklyGoal = choice;

        // If you change your weekly goal, you must change your calories target.
        setCaloriesTarget();
    }


    public WeeklyGoal getWeeklyGoal()
    {
        if (goal == Goal.MAINTAIN_WEIGHT)
        {
            return null;
        } else
        {
            return weeklyGoal;
        }
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
        if (getGoal() == Goal.MAINTAIN_WEIGHT)
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

    public void setDietGoal(Goal choice)
    {
        goal = choice;

    }

    public Goal getGoal()
    {
        return goal;
    }
}
