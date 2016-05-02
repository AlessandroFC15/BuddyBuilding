package com.example.android.buddybuilding.User;

import com.example.android.buddybuilding.Activities.InputActivities.InputData;
import com.example.android.buddybuilding.Diet.Diet;
import com.example.android.buddybuilding.Diet.DietLowCalorie;
import com.example.android.buddybuilding.Diet.DietLowCarbs;
import com.example.android.buddybuilding.Diet.DietLowFat;
import com.example.android.buddybuilding.Diet.DietToGain;
import com.example.android.buddybuilding.Diet.DietToLose;
import com.example.android.buddybuilding.Diet.DietToMaintain;
import com.example.android.buddybuilding.Food.Food;
import com.example.android.buddybuilding.Meals.Meal;

import java.util.HashMap;

public class User extends Person {
    protected Goal goal;
    private WeeklyGoal weeklyGoal;
    private Diet diet;
    private double goalWeight;
    private static int numberOfUsers = 0;

    // Construtores
    User() {
        goal = Goal.LOSE_WEIGHT;
        weeklyGoal = WeeklyGoal.LOSE_250G;
        diet = getCorrectDiet(goal);
        goalWeight = weight - 5;

        numberOfUsers++;
    }

    User(Gender gender, int age, double height, double weight, ActivityLevel activityLevel,
         WeeklyGoal weeklyGoal, Goal goal) {
        super(gender, age, height, weight, activityLevel);
        this.weeklyGoal = weeklyGoal;
        this.goal = goal;
        diet = getCorrectDiet(goal);
        numberOfUsers++;
    }

    User(final User user) {
        super(user);
        goal = user.goal;
        weeklyGoal = user.weeklyGoal;
        diet = user.diet;
        goalWeight = user.goalWeight;
        numberOfUsers++;
    }

    User(final InputData input) {
        super(input);

        goal = input.goal;
        weeklyGoal = input.weeklyGoal;
        diet = getCorrectDiet(goal);
        goalWeight = input.goalWeight;

        numberOfUsers++;
    }

    // Fim dos Construtores

    public Diet getDiet() {
        return diet;
    }

    public void setGoal(Goal choice) {
        diet = getCorrectDiet(choice);

        // If you changed your goal, we must update your calories target
        setCaloriesTarget();
    }

    private Diet getCorrectDiet(Goal goal) {
        switch (goal) {
            case LOSE_WEIGHT:
                return correctDietToLose(getBMR(), weeklyGoal);
            case MAINTAIN_WEIGHT:
                return new DietToMaintain(getBMR());
            case GAIN_WEIGHT:
                return new DietToGain(getBMR(), weeklyGoal);
            default:
                System.out.println("Error in getCorrectDiet | User");
                return null;
        }
    }

    private DietToLose correctDietToLose(int bmr, WeeklyGoal weeklyGoal) {
        switch (weeklyGoal) {
            case LOSE_250G:
                return new DietLowFat(bmr, weeklyGoal);
            case LOSE_500G:
                return new DietLowCarbs(bmr, weeklyGoal);
            case LOSE_750G:
                return new DietLowCarbs(bmr, weeklyGoal);
            case LOSE_1KG:
                return new DietLowCalorie(bmr, weeklyGoal);
            default:
                return null;
        }
    }
    

    public void setWeeklyGoal(WeeklyGoal choice) {
        weeklyGoal = choice;

        // If you change your weekly goal, you must change your calories target.
        setCaloriesTarget();
    }


    public WeeklyGoal getWeeklyGoal() {
        if (goal == Goal.MAINTAIN_WEIGHT) {
            return null;
        } else {
            return weeklyGoal;
        }
    }

    public void addFood(int nameOfMeal, Food food) {
        diet.addFoodToMeal(nameOfMeal, food);
    }

    public double getCaloriesFromMeal(int nameOfMeal) {
        return diet.getCaloriesFromMeal(nameOfMeal);
    }

    public void setCaloriesTarget() {
        diet.setCaloriesTarget(getBMR(), getWeeklyGoal());
    }

    public int getCaloriesTarget() {
        return diet.getCaloriesTarget();
    }

    public int getCaloriesIntake() {
        return diet.getCalories();
    }

    public HashMap<Integer, Meal> getAllMeals() {
        return diet.getAllMeals();
    }

    public boolean hasDietChanged() {
        return diet.hasDietChanged();
    }

    public int getLastMealChanged() {
        return diet.getLastMealChanged();
    }

    public void setDietGoal(Goal choice) {
        goal = choice;

    }

    public Goal getGoal() {
        return goal;
    }

    public static void createNewUser(final InputData input) {
        userData = new User(input);
    }

    public static void createNewUser() {
        userData = new User();
    }

    public enum WeeklyGoal {
        GAIN_250G(500, "Gain 250g per week"), GAIN_500G(750, "Gain 500g per week"),
        LOSE_250G(-200, "Lose 250g per week"), LOSE_500G(-300, "Lose 500g per week"),
        LOSE_750G(-400, "Lose 750g per week"), LOSE_1KG(-500, "Lose 1kg per week"),
        MAINTAIN(0, "Maintain Weight");

        private final int calories;
        private final String description;

        WeeklyGoal(int calories, String description) {
            this.calories = calories;
            this.description = description;
        }

        public boolean isGoalToLose()
        {
            return this == LOSE_250G || this == LOSE_500G 
            || this == LOSE_750G || this == LOSE_1KG;
        }

        public int getCalories() {
            return calories;
        }

        public String toString() {
            return description;
        }
    }

    public enum Goal {
        LOSE_WEIGHT("Lose Weight"),
        MAINTAIN_WEIGHT("Maintain Weight"),
        GAIN_WEIGHT("Gain Weight");

        private final String description;

        Goal(String description) {
            this.description = description;
        }

        public String toString() {
            return description;
        }
    }
    
    /*
    Calculo da Taxa Metabólica Basal
    Assumiu-se a formula 32 * kg
     */

    public int getBMR() {
        if (gender == Gender.MALE){
            return 66.5 + (13.75 * getWeight()) 
            + (5.003 * getHeight()) – (6.755 * getAge());
        } else if (gender == Gender.FEMALE) {
            return 655.1 + ( 9.563 * getWeight()) 
            + ( 1.850 * getHeight()) – ( 4.676 * getAge());
        }
    }

    public String toString() {
        return super.toString() +
                "\nWeekly Goal: " + weeklyGoal.toString() +
                "\nGoal: " + goal.toString() +
                "\nDiet (Calories Target): " + diet.getCaloriesTarget() + " kcal";
    }

    public static int getNumberOfUsers() {
        return numberOfUsers;
    }

    public static void resetNumberOfUsers() {
        numberOfUsers = 0;
    }

    public boolean isDietToLose() {
        return (diet instanceof DietToLose);
    }

    

    public double getGoalWeight() { return goalWeight; }

    public void setWeight(double newWeight)
    {
        super.setWeight(newWeight);

        diet.setCaloriesTarget(getBMR(), weeklyGoal);

        diet.setMacrosTarget();
    }
    
    private static User userData = null;

    public static User getInstance() {
        return userData;
    }

}
