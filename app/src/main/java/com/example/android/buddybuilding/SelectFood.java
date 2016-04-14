package com.example.android.buddybuilding;

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

public class SelectFood extends AppCompatActivity {

    private FoodData foodData = new FoodData(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_food);

        Intent intent = getIntent();

        int meal = intent.getIntExtra("MealName", -1);

        setTitle(getNameOfMeal(meal));

        printAllFoodsRegistered();
    }

    private void printAllFoodsRegistered()
    {
        LinearLayout layout = (LinearLayout) findViewById(R.id.foodsRegistered);

        Cursor cursor = foodData.getAllFoodData();

        try {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex(FoodData.COLUMN_NAME));

                int servingSize = cursor.getInt(cursor.getColumnIndex(FoodData.COLUMN_SERVING_SIZE));

                int calories = cursor.getInt(cursor.getColumnIndex(FoodData.COLUMN_CALORIES));

                printFood(name, servingSize, calories, layout);
            }
        } finally {
            cursor.close();
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

        /* textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFoodNutritionFacts(v);
            }
        }); */

        TextView foodInfo = new TextView(this);
        foodInfo.setText(Integer.toString(servingSize) + "g, " + Integer.toString(calories) + " cal");
        foodInfo.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        foodInfo.setPadding(leftPadding, 0, 0, Helper.convertDPToPixel(10));

        View separator = getSeparatorView();

        layout.addView(nameOfFood);
        layout.addView(foodInfo);
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
}
