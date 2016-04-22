package com.example.android.buddybuilding.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.buddybuilding.Databases.FoodData;
import com.example.android.buddybuilding.Food.Food;
import com.example.android.buddybuilding.Helper;
import com.example.android.buddybuilding.Meals.Breakfast;
import com.example.android.buddybuilding.Meals.Dinner;
import com.example.android.buddybuilding.Meals.Lunch;
import com.example.android.buddybuilding.Meals.Meal;
import com.example.android.buddybuilding.Meals.Snacks;
import com.example.android.buddybuilding.R;
import com.example.android.buddybuilding.User.User;

import java.util.HashMap;

import static android.widget.LinearLayout.OnClickListener;
import static android.widget.LinearLayout.VERTICAL;

public class SelectFood extends AppCompatActivity {

    private FoodData foodDatabase = new FoodData(this);
    private Cursor allFoodData;
    private User userData = User.getInstance();
    private int nameOfMeal;
    private Meal meal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_food);

        Intent intent = getIntent();

        nameOfMeal = intent.getIntExtra("MealName", -1);

        setTitle(getNameOfMeal(nameOfMeal));

        meal = userData.getAllMeals().get(nameOfMeal);

        if (userData.isDietToLose())
        {
            // setScreenToDietToLose();
            // Helper.makeToast("Not surprised motherfuckers!", this);
        } else
        {
            // Helper.makeToast("EIREEE!", this);
        }

        printAllFoodsRegistered();

        printCommonFoods();
    }

    private void printCommonFoods()
    {
        LinearLayout layout = (LinearLayout) findViewById(R.id.commonFoods);

        HashMap<String, Food> commonFoods = new HashMap<>();

        if (meal instanceof Breakfast)
        {
            commonFoods = ((Breakfast) meal).getCommonBreakfastFoods();
        } else if (meal instanceof Lunch)
        {
            commonFoods = ((Lunch) meal).getCommonLunchFoods();
        } else if (meal instanceof Dinner)
        {
            commonFoods = ((Dinner) meal).getCommonDinnerFoods();
        } else if (meal instanceof Snacks)
        {
            commonFoods = ((Snacks) meal).getCommonSnacksFoods();
        }

        OnClickListener onClick = new OnClickListener() {
            @Override
            public void onClick(View v) {
                addFoodFromCommonFoods(v);
            }
        };

        for (String nameOfFood : commonFoods.keySet())
        {
            Food food = commonFoods.get(nameOfFood);

            printFood(nameOfFood, food.getServingSize(), food.getCalories(), layout,
                    onClick);
        }
    }

    private void addFoodFromCommonFoods(View view)
    {
        // 1st Step | Get the name of the food clicked

        String nameOfFood = getNameOfFood(view);

        // 2nd Step | Make a Food object with that name

        Food food = getFoodFromCommonFoods(nameOfFood);

        if (food != null)
        {
            // 3rd Step | Add food to the user diary
            userData.addFood(nameOfMeal, food);

            Helper.makeToast(nameOfFood + " successfully added to " + getNameOfMeal(nameOfMeal), this);

            finish();
        }
    }

    private Food getFoodFromCommonFoods(String nameOfFood)
    {
        if (meal instanceof Breakfast)
        {
            return Breakfast.getCommonBreakfastFoods().get(nameOfFood);
        } else if (meal instanceof Lunch)
        {
            return Lunch.getCommonLunchFoods().get(nameOfFood);
        } else if (meal instanceof Dinner)
        {
            return Dinner.getCommonDinnerFoods().get(nameOfFood);
        } else if (meal instanceof Snacks)
        {
            return Snacks.getCommonSnacksFoods().get(nameOfFood);
        } else {
            Helper.makeToast("Sorry", this);
            return null;
        }
    }

    private void printAllFoodsRegistered() {
        getAllFoodData();

        LinearLayout layout = (LinearLayout) findViewById(R.id.foodsRegistered);

        while (allFoodData.moveToNext()) {
            String name = allFoodData.getString(allFoodData.getColumnIndex(FoodData.COLUMN_NAME));

            int servingSize = allFoodData.getInt(allFoodData.getColumnIndex(FoodData.COLUMN_SERVING_SIZE));

            int calories = allFoodData.getInt(allFoodData.getColumnIndex(FoodData.COLUMN_CALORIES));

            OnClickListener onClick = new OnClickListener() {
                @Override
                public void onClick(View v) {
                    addFoodFromDatabase(v);
                }
            };

            printFood(name, servingSize, calories, layout, onClick);
        }
    }

    private void printFood(String name, int servingSize, int calories, LinearLayout layout,
                           OnClickListener onClickListener)
    {
        TextView nameOfFood = new TextView(this);
        nameOfFood.setText(name);
        nameOfFood.setTextColor(Color.BLACK);

        int leftPadding = Helper.convertDPToPixel(15);
        int topPadding = Helper.convertDPToPixel(10);
        int bottomPadding = 0;
        int rightPadding = 0;

        nameOfFood.setPadding(leftPadding, topPadding, rightPadding, bottomPadding);

        TextView foodInfo = new TextView(this);
        foodInfo.setText(String.format("%dg, %dcal", servingSize, calories));
        foodInfo.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        foodInfo.setPadding(leftPadding, 0, 0, Helper.convertDPToPixel(10));

        View separator = getSeparatorView();

        // Create the LinearLayout container to hold all these views
        final LinearLayout container = new LinearLayout(this);
        container.setOnClickListener(onClickListener);

        container.setOrientation(VERTICAL);

        container.addView(nameOfFood);
        container.addView(foodInfo);

        layout.addView(container);
        layout.addView(separator);
    }

    private String getNameOfMeal(int meal)
    {
        switch (meal)
        {
            case Meal.BREAKFAST:
                return "Breakfast";
            case Meal.LUNCH:
                return "Lunch";
            case Meal.DINNER:
                return "Dinner";
            case Meal.SNACKS:
                return "Snacks";
            default:
                return "Error";
        }
    }

    private View getSeparatorView()
    {
        View view = new View(this);

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
        view.setLayoutParams(params);

        ColorDrawable color = new ColorDrawable(getResources().getColor(R.color.gray));
        view.setBackground(color);

        return view;
    }

    public void addFoodFromDatabase(View view)
    {
        // 1st Step | Get the name of the food clicked

        String nameOfFood = getNameOfFood(view);

        // 2nd Step | Make a Food object with that name

        Food food = getFoodFromDatabase(nameOfFood);

        // 3rd Step | Add food to the user diary
        userData.addFood(nameOfMeal, food);

        Helper.makeToast(nameOfFood + " successfully added to " + getNameOfMeal(nameOfMeal), this);

        finish();
    }

    private Food getFoodFromDatabase(String nameOfFood)
    {
        allFoodData.moveToPosition(-1);

        while (allFoodData.moveToNext()) {
            String name = allFoodData.getString(allFoodData.getColumnIndex(FoodData.COLUMN_NAME));

            if (name.equals(nameOfFood))
            {
                int servingSize = allFoodData.getInt(allFoodData.getColumnIndex(FoodData.COLUMN_SERVING_SIZE));

                double protein = allFoodData.getDouble(allFoodData.getColumnIndex(FoodData.COLUMN_PROTEIN));

                double carbs = allFoodData.getDouble(allFoodData.getColumnIndex(FoodData.COLUMN_CARBS));

                double fat = allFoodData.getDouble(allFoodData.getColumnIndex(FoodData.COLUMN_FAT));

                return new Food(nameOfFood, servingSize, protein, carbs, fat);
            }
            // printFood(name, servingSize, calories, layout);
        }

        return null;

    }

    private String getNameOfFood(View view)
    {
        View name = ((LinearLayout) view).getChildAt(0);

        return ((TextView) name).getText().toString();
    }

    private void getAllFoodData()
    {
        allFoodData = foodDatabase.getAllFoodData();
    }

    public void changeScreen(View view) {
        int id = view.getId();

        LinearLayout foodsRegisteredLayout = (LinearLayout) findViewById(R.id.foodsRegistered);
        LinearLayout commonFoodsLayout = (LinearLayout) findViewById(R.id.commonFoods);

        switch (id) {
            case (R.id.buttonFoodsRegistered):
                if (foodsRegisteredLayout.getVisibility() != View.VISIBLE) {
                    commonFoodsLayout.setVisibility(View.GONE);
                    foodsRegisteredLayout.setVisibility(View.VISIBLE);

                    setIndicatorVisible(R.id.indicatorFoodsRegistered);
                    changeButtonTextColor(R.id.buttonFoodsRegistered);
                }
                break;
            case (R.id.buttonCommonFoods):
                if (commonFoodsLayout.getVisibility() != View.VISIBLE) {
                    foodsRegisteredLayout.setVisibility(View.GONE);
                    commonFoodsLayout.setVisibility(View.VISIBLE);

                    setIndicatorVisible(R.id.indicatorCommonFoods);
                    changeButtonTextColor(R.id.buttonCommonFoods);
                }
        }
    }

    private void setIndicatorVisible(int id)
    {
        View foodRegisteredIndicator = findViewById(R.id.indicatorFoodsRegistered);
        View commonFoodsIndicator = findViewById(R.id.indicatorCommonFoods);

        if (id == R.id.indicatorFoodsRegistered)
        {
            commonFoodsIndicator.setVisibility(View.INVISIBLE);
            foodRegisteredIndicator.setVisibility(View.VISIBLE);
        } else if (id == R.id.indicatorCommonFoods)
        {
            commonFoodsIndicator.setVisibility(View.VISIBLE);
            foodRegisteredIndicator.setVisibility(View.INVISIBLE);
        } else
        {
            Helper.makeToast("Error in setIndicatorVisible", this);
        }
    }

    private void changeButtonTextColor(int id)
    {
        Button buttonFoodsRegistered = (Button) findViewById(R.id.buttonFoodsRegistered);
        Button buttonCommonFoods = (Button) findViewById(R.id.buttonCommonFoods);

        switch (id)
        {
            case (R.id.buttonFoodsRegistered):
                buttonFoodsRegistered.setTextColor(getResources().getColor(R.color.colorPrimary));
                buttonCommonFoods.setTextColor(getResources().getColor(R.color.black));
                break;
            case (R.id.buttonCommonFoods):
                buttonCommonFoods.setTextColor(getResources().getColor(R.color.colorPrimary));
                buttonFoodsRegistered.setTextColor(getResources().getColor(R.color.black));
        }


    }
}
