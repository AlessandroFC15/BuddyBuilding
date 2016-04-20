package com.example.android.buddybuilding.Activities;

import android.content.Intent;
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

import com.example.android.buddybuilding.Diet.Diet;
import com.example.android.buddybuilding.Food.Food;
import com.example.android.buddybuilding.Helper;
import com.example.android.buddybuilding.Meals.Meal;
import com.example.android.buddybuilding.R;
import com.example.android.buddybuilding.User.User;

import java.util.HashMap;

public class Diary extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private User userData = User.getInstance();
    HashMap<Integer, Meal> meals = userData.getAllMeals();

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

        printAllMeals();
    }

    public void onResume()
    {
        super.onResume();

        // We will only update the meal if some food has been added
        if (userData.hasDietChanged())
        {
            int mealChanged = userData.getMealChanged();

            updateMeal(mealChanged);
        }
    }

    private void printAllMeals() {
        for (int nameOfMeal : meals.keySet()) {
            switch (nameOfMeal) {
                case (Meal.BREAKFAST):
                    printMeal(Meal.BREAKFAST);
                    break;
                case (Meal.LUNCH):
                    printMeal(Meal.LUNCH);
                    break;
                case (Meal.DINNER):
                    printMeal(Meal.DINNER);
                    break;
                case (Meal.SNACKS):
                    printMeal(Meal.SNACKS);
                    break;
                default:
                    Helper.makeToast("Error printAllMeals", this);
                    break;
            }
        }
    }

    private void printMeal(int nameOfMeal)
    {
        Meal meal = meals.get(nameOfMeal);

        // 2nd Step = Get the correct ids for the given meal

        int caloriesMealID = getCaloriesMealID(nameOfMeal);

        int mealLayoutID = getMealLayoutID(nameOfMeal);

        // 3rd Step = Find the view and the layout

        TextView mealCalories = (TextView) findViewById(caloriesMealID);

        LinearLayout layout = (LinearLayout) findViewById(mealLayoutID);

        mealCalories.setText(Integer.toString(meal.getCalories()));

        for (Food food : meal.getFoodsFromMeal())
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

    public void addFoodToMeal(View view) {
        // Creating just for test
        // This process will be replaced by a choice of food.
        // Food newFood = new Food("Pasta de Amendoim", 10, 3, 1.7, 5.5);

        // 1st Step = Find which meal we have to update
        int nameOfMeal = getCorrectMeal(view);

        // 2nd Step = Ask the user to select the food from the dabatase
        // Add food to the user data to be shown in the Home Page
        Intent intent = new Intent(this, SelectFood.class);

        intent.putExtra("MealName", nameOfMeal);

        startActivity(intent);

        // 3rd Step = Update the meal on resume
    }

    private void updateMeal(int nameOfMeal) {
        // 1st Step = Get the object meal

        Meal meal = meals.get(nameOfMeal);

        // 2nd Step = Get the correct ids for the given meal

        int caloriesMealID = getCaloriesMealID(nameOfMeal);

        int mealLayoutID = getMealLayoutID(nameOfMeal);

        // 3rd Step = Find the view and the layout

        TextView mealCalories = (TextView) findViewById(caloriesMealID);

        LinearLayout layout = (LinearLayout) findViewById(mealLayoutID);

        // 4th Step = Update the mealCalories and print the last food added.

        mealCalories.setText(Integer.toString(meal.getCalories()));

        printFood(Diet.lastFoodAdded, layout);

        Diet.lastFoodAdded = null;
    }

    private int getCaloriesMealID(int nameOfMeal) {
        switch (nameOfMeal) {
            case Meal.BREAKFAST:
                return R.id.caloriesMealBreakfast;
            case Meal.LUNCH:
                return R.id.caloriesMealLunch;
            case Meal.DINNER:
                return R.id.caloriesMealDinner;
            case Meal.SNACKS:
                return R.id.caloriesMealSnacks;
            default:
                Helper.makeToast("Error in getCaloriesMealID", this);
                return -1;
        }
    }

    private int getMealLayoutID(int nameOfMeal)
    {
        switch (nameOfMeal) {
            case Meal.BREAKFAST:
                return R.id.mealBreakfast;
            case Meal.LUNCH:
                return R.id.mealLunch;
            case Meal.DINNER:
                return R.id.mealDinner;
            case Meal.SNACKS:
                return R.id.mealSnacks;
            default:
                Helper.makeToast("Error in getMealLayoutID", this);
                return -1;
        }
    }

    private int getCorrectMeal(View view) {
        LinearLayout meal = (LinearLayout) view.getParent().getParent();

        int id = meal.getId();

        switch (id) {
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

    private void printFood(Food newFood, LinearLayout meal) {
        TextView food = new TextView(this);

        food.setText(newFood.getName() + " ("
                + Integer.toString(newFood.getServingSize()) + "g)");

        int padding = Helper.convertDPToPixel(10);

        food.setPadding(padding, padding, padding, padding);

        meal.addView(food, 2);
    }

    private void updateMealCalories(int nameOfMeal) {
        // 1st Step = Find the correct id based on the name of meal
        int caloriesMealID = getCaloriesMealID(nameOfMeal);

        // 2nd Step = Find the correct text view
        TextView calories = (TextView) findViewById(caloriesMealID);

        // 3rd Step = Change it according to the correct value
        calories.setText(Double.toString(userData.getCaloriesFromMeal(nameOfMeal)));
    }
}
