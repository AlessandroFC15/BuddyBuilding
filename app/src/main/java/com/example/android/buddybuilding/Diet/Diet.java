package com.example.android.buddybuilding.Diet;

import com.example.android.buddybuilding.Activities.Nutrition;
import com.example.android.buddybuilding.CaloriesMeasurable;
import com.example.android.buddybuilding.Food.Food;
import com.example.android.buddybuilding.Helper;
import com.example.android.buddybuilding.Meals.Breakfast;
import com.example.android.buddybuilding.Meals.Dinner;
import com.example.android.buddybuilding.Meals.Lunch;
import com.example.android.buddybuilding.Meals.Meal;
import com.example.android.buddybuilding.Meals.Snacks;
import com.example.android.buddybuilding.User.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public abstract class Diet implements CaloriesMeasurable {

    private int totalCaloriesIntake;
    private double proteinIntake;
    private double carbsIntake;
    private double fatIntake;
    private HashMap<Integer, Meal> meals = new HashMap<>();

    /* Targets */
    protected int totalCaloriesTarget;
    protected double proteinTarget;
    protected double carbsTarget;
    protected double fatTarget;

    protected double proteinPercentage;
    protected double carbsPercentage;
    protected double fatPercentage;

    private ArrayList<Food> allFoodsAdded = new ArrayList<>();
    private int lastMealChanged;
    private boolean hasDietChanged;

    private Food foodToBeAdded = null;

    private static final int maxCaloriesTarget = 5000;
    protected static HashMap<String, Food> recommendedFoods;

    public Diet()
    {
        setAttributesToDefault();

        addDefaultMeals();
    }

    public Diet(int caloriesTarget)
    {
        setAttributesToDefault();

        addDefaultMeals();

        totalCaloriesTarget = (int) Helper.validateValue(caloriesTarget, 0, maxCaloriesTarget);
    }

    public Diet(final Diet diet)
    {
        totalCaloriesTarget = diet.totalCaloriesTarget;
        totalCaloriesIntake = diet.totalCaloriesIntake;
        proteinIntake = diet.proteinIntake;
        carbsIntake = diet.carbsIntake;
        fatIntake = diet.fatIntake;
        meals = diet.meals;
    }

    protected void setAttributesToDefault()
    {
        totalCaloriesTarget = 0;
        totalCaloriesIntake = 0;
        proteinIntake = 0;
        carbsIntake = 0;
        fatIntake = 0;
    }

    protected void addDefaultMeals()
    {
        meals.put(Meal.BREAKFAST, new Breakfast());
        meals.put(Meal.LUNCH, new Lunch());
        meals.put(Meal.DINNER, new Dinner());
        meals.put(Meal.SNACKS, new Snacks());

        calculateCalories();
    }

    public void addFoodToMeal(int nameOfmeal, Food food)
    {
        if (nameOfmeal >= Meal.BREAKFAST && nameOfmeal <= Meal.SNACKS)
        {
            Meal meal = meals.get(nameOfmeal);

            meal.addFood(food);

            totalCaloriesIntake += food.getCalories();
            proteinIntake += food.getProtein();
            carbsIntake += food.getCarbs();
            fatIntake += food.getTotalFat();

            lastMealChanged = nameOfmeal;
            hasDietChanged = true;

            if (! allFoodsAdded.contains(food))
            {
                allFoodsAdded.add(food);
            }
        }
    }

    public int getCaloriesFromMeal(int nameOfMeal)
    {
        return meals.get(nameOfMeal).getCalories();
    }

    public abstract void setCaloriesTarget(int bmr, User.WeeklyGoal weeklyGoal);

    public int getCaloriesTarget()
    {
        return totalCaloriesTarget;
    }

    public HashMap<Integer, Meal> getAllMeals()
    {
        return meals;
    }

    // Implementação da interface CaloriesMeasurable

    public void calculateCalories()
    {
        totalCaloriesIntake = 0;

        for (int nameOfMeal : meals.keySet())
        {
            totalCaloriesIntake += meals.get(nameOfMeal).getCalories();
        }
    }

    public int getCalories() { return totalCaloriesIntake;}

    public String toString()
    {
        String output;

        output = "\n>> Calories Target: " + totalCaloriesTarget +
                "\n>> Calories Intake: " + totalCaloriesIntake +
                "\n>> Protein Intake: " + proteinIntake +
                "\n>> Carbs Intake: " + carbsIntake +
                "\n>> Fat Intake: " + fatIntake +
                "\n>> Number of Meals: " + meals.size();

        return output;
    }

    public int getLastMealChanged()
    {
        return lastMealChanged;
    }

    public void setFoodToBeAdded(Food food)
    {
        foodToBeAdded = new Food(food);
    }

    public Food getFoodToBeAdded()
    {
        return foodToBeAdded;
    }

    public ArrayList<Food> getHighestFoodCalories()
    {
        Collections.sort(allFoodsAdded, Collections.reverseOrder());

        if (allFoodsAdded.size() >= 0 && allFoodsAdded.size() <= 3)
        {
            return allFoodsAdded;
        } else
        {
            ArrayList<Food> highest = new ArrayList<>();

            Food first = allFoodsAdded.get(0);
            Food second = allFoodsAdded.get(1);
            Food third = allFoodsAdded.get(2);

            highest.add(first);
            highest.add(second);
            highest.add(third);

            return highest;
        }
    }

    public static HashMap<String, Food> getRecommendedFoods() {return recommendedFoods; }

    public boolean hasDietChanged () {return hasDietChanged; }

    public void setDietChanged(boolean value)
    {
        hasDietChanged = value;
    }

    public double getProteinIntake()
    {
        return proteinIntake;
    }

    public double getProteinTarget()
    {
        return proteinTarget;
    }

    public double getCarbsIntake()
    {
        return carbsIntake;
    }

    public double getCarbsTarget()
    {
        return carbsTarget;
    }

    public double getFatIntake()
    {
        return fatIntake;
    }

    public double getFatTarget()
    {
        return fatTarget;
    }

    public int getTotalCaloriesIntake() { return totalCaloriesIntake; }

    public double getProteinPercentageIntake()
    {
        if (totalCaloriesIntake > 0)
        {
            return (((proteinIntake * 4)/totalCaloriesIntake) * 100);
        }

        return 0;
    }

    public double getCarbsPercentageIntake()
    {
        if (totalCaloriesIntake > 0) {
            return (((carbsIntake * 4) / totalCaloriesIntake) * 100);
        }

        return 0;
    }

    public double getFatPercentageIntake()
    {
        if (totalCaloriesIntake > 0) {
            return (((fatIntake * 9) / totalCaloriesIntake) * 100);
        }

        return 0;
    }

    public double getProteinPercentageGoal()
    {
        return proteinPercentage;
    }

    public double getCarbsPercentageGoal()
    {
        return carbsPercentage;
    }

    public double getFatPercentageGoal()
    {
        return fatPercentage;
    }

    public void setMacrosTarget()
    {
        double carbsCalories = (totalCaloriesTarget * carbsPercentage) / 100.0;
        double proteinCalories = (totalCaloriesTarget * proteinPercentage) / 100.0;
        double fatCalories = (totalCaloriesTarget * fatPercentage) / 100.0;

        carbsTarget = carbsCalories / 4;
        proteinTarget = proteinCalories / 4;
        fatTarget = fatCalories / 9;
    }

    public ArrayList<Food> getHighestFood(int parameter)
    {
        switch (parameter)
        {
            case (Nutrition.CALORIES_PARAM):
                Collections.sort(allFoodsAdded, Collections.reverseOrder());
                break;
            case (Nutrition.PROTEIN_PARAM):
                Collections.sort(allFoodsAdded, new Food.ProteinComparator());
                Collections.reverse(allFoodsAdded);
                break;
            case (Nutrition.CARBS_PARAM):
                Collections.sort(allFoodsAdded, new Food.CarbsComparator());
                Collections.reverse(allFoodsAdded);
                break;
            case (Nutrition.FAT_PARAM):
                Collections.sort(allFoodsAdded, new Food.FatComparator());
                Collections.reverse(allFoodsAdded);
                break;
            default:
                break;
        }

        if (allFoodsAdded.size() >= 0 && allFoodsAdded.size() <= 3)
        {
            return allFoodsAdded;
        } else
        {
            ArrayList<Food> highest = new ArrayList<>();

            Food first = allFoodsAdded.get(0);
            Food second = allFoodsAdded.get(1);
            Food third = allFoodsAdded.get(2);

            highest.add(first);
            highest.add(second);
            highest.add(third);

            return highest;
        }
    }
}
