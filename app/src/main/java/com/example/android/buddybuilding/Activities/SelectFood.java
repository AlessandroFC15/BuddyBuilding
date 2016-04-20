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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.buddybuilding.Databases.FoodData;
import com.example.android.buddybuilding.Food.Food;
import com.example.android.buddybuilding.Helper;
import com.example.android.buddybuilding.Meals.Meal;
import com.example.android.buddybuilding.R;
import com.example.android.buddybuilding.User.User;

import static android.widget.LinearLayout.*;

public class SelectFood extends AppCompatActivity {

    private FoodData foodDatabase = new FoodData(this);
    private Cursor allFoodData;
    private User userData = User.getInstance();
    private int nameOfMeal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_food);

        Intent intent = getIntent();

        nameOfMeal = intent.getIntExtra("MealName", -1);

        setTitle(getNameOfMeal(nameOfMeal));

        printAllFoodsRegistered();
    }

    private void printAllFoodsRegistered() {
        getAllFoodData();

        LinearLayout layout = (LinearLayout) findViewById(R.id.foodsRegistered);

        while (allFoodData.moveToNext()) {
            String name = allFoodData.getString(allFoodData.getColumnIndex(FoodData.COLUMN_NAME));

            int servingSize = allFoodData.getInt(allFoodData.getColumnIndex(FoodData.COLUMN_SERVING_SIZE));

            int calories = allFoodData.getInt(allFoodData.getColumnIndex(FoodData.COLUMN_CALORIES));

            printFood(name, servingSize, calories, layout);
        }
    }

    private void printFood(String name, int servingSize, int calories, LinearLayout layout)
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
        container.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                selectServingSize(v);
            }
        });
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

    public void selectServingSize(View view)
    {
        // 1st Step | Get the name of the food clicked

        String nameOfFood = getNameOfFood(view);

        // 2nd Step | Make a Food object with that name

        Food food = getFood(nameOfFood);

        // 3rd Step | Add food to the user diary
        userData.addFood(nameOfMeal, food);

        Helper.makeToast(nameOfFood + " successfully added to " + getNameOfMeal(nameOfMeal), this);

        finish();
    }

    private Food getFood(String nameOfFood)
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
}
