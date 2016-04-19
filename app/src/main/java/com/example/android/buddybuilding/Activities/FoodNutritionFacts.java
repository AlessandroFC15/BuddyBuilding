package com.example.android.buddybuilding.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.android.buddybuilding.Databases.FoodData;
import com.example.android.buddybuilding.Helper;
import com.example.android.buddybuilding.R;

public class FoodNutritionFacts extends AppCompatActivity {

    private FoodData foodDB = new FoodData(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_nutrition_facts);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Nutrition Facts");

        Intent intent = getIntent();

        String foodName = intent.getStringExtra(FoodActivity.foodNameExtra);

        updateNameOfFood(foodName);

        printNutritionFacts(foodName);
    }

    private void updateNameOfFood(String name)
    {
        TextView foodName = (TextView) findViewById(R.id.foodName);

        foodName.setText(name);
    }

    private void printNutritionFacts(String foodName)
    {
        Cursor cursor = foodDB.getFoodData(foodName);

        if (cursor != null)
        {
            try {
                while (cursor.moveToNext()) {
                    int servingSize = cursor.getInt(cursor.getColumnIndex(FoodData.COLUMN_SERVING_SIZE));
                    int calories = cursor.getInt(cursor.getColumnIndex(FoodData.COLUMN_CALORIES));
                    double carbs = cursor.getDouble(cursor.getColumnIndex(FoodData.COLUMN_CARBS));
                    double protein = cursor.getDouble(cursor.getColumnIndex(FoodData.COLUMN_PROTEIN));
                    double fat = cursor.getDouble(cursor.getColumnIndex(FoodData.COLUMN_FAT));

                    updateAllFields(servingSize, calories, carbs, protein, fat);
                }
            } finally {
                cursor.close();
            }
        } else
        {
            Helper.makeToast("Error reading food data!", this);
        }
    }

    private void updateAllFields(int servingSize, int calories, double carbs, double protein, double fat)
    {
        updateValue(R.id.servingSize, servingSize);
        updateValue(R.id.calories, calories);
        updateValue(R.id.carbs, carbs);
        updateValue(R.id.protein, protein);
        updateValue(R.id.fat, fat);
    }

    private void updateValue(int id, Double value)
    {
        TextView textView = (TextView) findViewById(id);

        textView.setText(Double.toString(value));
    }

    private void updateValue(int id, int value)
    {
        TextView textView = (TextView) findViewById(id);

        textView.setText(Integer.toString(value));
    }

}
