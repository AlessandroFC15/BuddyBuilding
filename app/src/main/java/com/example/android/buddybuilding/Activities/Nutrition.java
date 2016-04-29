package com.example.android.buddybuilding.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.buddybuilding.Activities.Diary.Diary;
import com.example.android.buddybuilding.Food.Food;
import com.example.android.buddybuilding.Helper;
import com.example.android.buddybuilding.R;
import com.example.android.buddybuilding.User.User;

import java.util.ArrayList;

public class Nutrition extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private User userData = User.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Nutrition");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        checkNutritionInMenu();

        updateCaloriesScreen();

        changeScreen(findViewById(R.id.buttonCalories));
    }

    public void onResume() {
        super.onResume();

        checkNutritionInMenu();

        updateCaloriesScreen();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nutrition, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            startActivity(new Intent(this, Home.class));
        } else if (id == R.id.nav_diary) {
            startActivity(new Intent(this, Diary.class));
        } else if (id == R.id.nav_foods) {
            startActivity(new Intent(this, FoodActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void checkNutritionInMenu() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_nutrition);
    }

    // Functions related to changing the content of the screen

    public void changeScreen(View view) {
        int id = view.getId();

        setAllButtonsToDefault();
        setButtonToChosen((Button) view);

        setAllLayoutsToGone();
        LinearLayout layoutToShow = getLayoutOfButton(id);

        if (layoutToShow != null) {
            layoutToShow.setVisibility(View.VISIBLE);
        } else {
            Helper.makeToast("Error in changing screen", this);
        }
    }

    /*
     Method to get the corresponding layout to each button in the activity
     */

    private LinearLayout getLayoutOfButton(int id) {
        switch (id) {
            case (R.id.buttonCalories):
                return (LinearLayout) findViewById(R.id.caloriesScreen);
            case (R.id.buttonNutrients):
                return (LinearLayout) findViewById(R.id.nutrientsScreen);
            case (R.id.buttonMacros):
                return (LinearLayout) findViewById(R.id.macrosScreen);
            default:
                return null;
        }
    }

    private void setAllLayoutsToGone() {
        LinearLayout caloriesLayout = (LinearLayout) findViewById(R.id.caloriesScreen);
        LinearLayout nutrientsLayout = (LinearLayout) findViewById(R.id.nutrientsScreen);
        LinearLayout macrosLayout = (LinearLayout) findViewById(R.id.macrosScreen);

        caloriesLayout.setVisibility(View.GONE);
        nutrientsLayout.setVisibility(View.GONE);
        macrosLayout.setVisibility(View.GONE);
    }

    private void setAllButtonsToDefault() {
        Button caloriesButton = (Button) findViewById(R.id.buttonCalories);
        Button nutrientsButton = (Button) findViewById(R.id.buttonNutrients);
        Button macrosButton = (Button) findViewById(R.id.buttonMacros);

        caloriesButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        nutrientsButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        macrosButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);

        caloriesButton.setTypeface(null, Typeface.NORMAL);
        nutrientsButton.setTypeface(null, Typeface.NORMAL);
        macrosButton.setTypeface(null, Typeface.NORMAL);
    }

    private void setButtonToChosen(Button button) {
        button.setTypeface(null, Typeface.BOLD);
        button.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
    }

    private void updateCaloriesScreen()
    {
        int totalCalories = userData.getDiet().getCalories();
        int caloriesGoal = userData.getDiet().getCaloriesTarget();
        int caloriesLeft = caloriesGoal - totalCalories;

        updateValue(R.id.totalCalories, totalCalories);
        updateValue(R.id.caloriesGoal, caloriesGoal);
        updateValue(R.id.caloriesLeft, caloriesLeft);

        if (caloriesLeft < 0)
        {
            TextView text = (TextView) findViewById(R.id.caloriesLeft);
            text.setTextColor(Color.RED);
        }

        printFoodsHighestInCalories();
    }

    private void updateValue(int id, int value)
    {
        TextView text = (TextView) findViewById(id);
        text.setText(String.format("%d", value));
    }

    private void printFoodsHighestInCalories()
    {
        ArrayList<Food> highestFoodCalories = userData.getDiet().getHighestFoodCalories();

        LinearLayout layout = (LinearLayout) findViewById(R.id.foodsHighestCalories);

        layout.removeAllViews();

        if (highestFoodCalories.isEmpty())
        {
            TextView warning = new TextView(this);

            warning.setText("No foods have been added yet!");

            int padding = Helper.convertDPToPixel(15);

            warning.setPadding(padding, padding, padding, padding);

            warning.setGravity(Gravity.CENTER_HORIZONTAL);

            layout.addView(warning, 0);
        } else
        {
            for (int i = 0; i < highestFoodCalories.size(); i++)
            {
                printFood(i + 1, highestFoodCalories.get(i), layout);
            }
        }
    }


    private void printFood(int position, Food newFood, LinearLayout layout) {
        RelativeLayout container = new RelativeLayout(this);

        TextView food = new TextView(this);
        food.setText(String.format("%d. %s", position, newFood.getName()));
        int padding = Helper.convertDPToPixel(15);
        food.setPadding(padding, padding, padding, padding);
        food.setTextColor(Color.BLACK);

        TextView calories = new TextView(this);
        calories.setText(String.format("%d kcal", newFood.getCalories()));
        calories.setPadding(padding, padding, padding, padding);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        calories.setLayoutParams(params);

        container.addView(food);
        container.addView(calories);

        layout.addView(container);
    }

}

