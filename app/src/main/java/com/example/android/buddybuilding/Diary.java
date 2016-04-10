package com.example.android.buddybuilding;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class Diary extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private User userData = User.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        updateAllMeals();
    }

    private void updateAllMeals()
    {
        HashMap<Integer, Meal> meals = userData.getAllMeals();

        for (int nameOfMeal : meals.keySet())
        {
            Meal meal = meals.get(nameOfMeal);

            switch (nameOfMeal)
            {
                case (Meal.BREAKFAST):
                    // updateBreakfast(meal);
                    updateMeal(meal, R.id.caloriesMealBreakfast, R.id.mealBreakfast);
                    break;
                case (Meal.LUNCH):
                    updateMeal(meal, R.id.caloriesMealLunch, R.id.mealLunch);
                    break;
                case (Meal.DINNER):
                    updateMeal(meal, R.id.caloriesMealDinner, R.id.mealDinner);
                    break;
                case (Meal.SNACKS):
                    updateMeal(meal, R.id.caloriesMealSnacks, R.id.mealSnacks);
                    break;
                default:
                    Helper.makeToast("Error updateAllMeals", this);
                    break;
            }
        }
    }

    private void updateMeal(Meal meal, int caloriesMealID, int mealLayoutID)
    {
        TextView mealCalories = (TextView) findViewById(caloriesMealID);

        mealCalories.setText(Double.toString(meal.getMealTotalCalories()));

        LinearLayout layout = (LinearLayout) findViewById(mealLayoutID);

        ArrayList<Food> foodsFromMeal = meal.getFoodsFromMeal();

        for (Food food : foodsFromMeal)
        {
            printFood(food, layout);
        }
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
        getMenuInflater().inflate(R.menu.home, menu);
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

        if (id == R.id.nav_camara) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void addFoodToMeal(View view)
    {
        // Creating just for test
        // This process will be replaced by a choice of food.
        Food newFood = new Food("Pasta de Amendoim", 10, 3, 1.7, 5.5);

        // 1st Step = Find which meal we have to update
        int nameOfMeal = getCorrectMeal(view);

        // 2nd Step = Add food to the user data to be shown in the Home Page
        userData.addFood(nameOfMeal, newFood);

        // 3rd Step = Find the layout that contains the meal
        LinearLayout layout = (LinearLayout) view.getParent().getParent();

        printFood(newFood, layout);

        updateMealCalories(nameOfMeal);
    }

    private int getCorrectMeal(View view)
    {
        LinearLayout meal = (LinearLayout) view.getParent().getParent();

        int id = meal.getId();

        switch (id)
        {
            case R.id.mealBreakfast:
                return Meal.BREAKFAST;
            case R.id.mealLunch:
                return Meal.LUNCH;
            case R.id.mealDinner:
                return Meal.DINNER;
            case R.id.mealSnacks:
                return Meal.SNACKS;
            default:
                Helper.makeToast("Error in getting correct meal", this);
                return -1;
        }
    }

    private void printFood(Food newFood, LinearLayout meal)
    {
        TextView food = new TextView(this);

        food.setText(newFood.getName());

        food.setTextColor(getResources().getColor(R.color.colorAccent));

        meal.addView(food, 2);
    }

    private void updateMealCalories(int nameOfMeal)
    {
        // 1st Step = Find the correct id based on the name of meal
        int caloriesMealID;

        switch (nameOfMeal)
        {
            case Meal.BREAKFAST:
                caloriesMealID = R.id.caloriesMealBreakfast;
                break;
            case Meal.LUNCH:
                caloriesMealID = R.id.caloriesMealLunch;
                break;
            case Meal.DINNER:
                caloriesMealID = R.id.caloriesMealDinner;
                break;
            case Meal.SNACKS:
                caloriesMealID = R.id.caloriesMealSnacks;
                break;
            default:
                Helper.makeToast("Error in updating meal calories", this);
                return;
        }

        // 2nd Step = Find the correct text view
        TextView calories = (TextView) findViewById(caloriesMealID);

        // 3rd Step = Change it according to the correct value
        calories.setText(Double.toString(userData.getCaloriesFromMeal(nameOfMeal)));
    }
}
