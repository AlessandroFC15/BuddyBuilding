package com.example.android.buddybuilding.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.buddybuilding.Databases.FoodData;
import com.example.android.buddybuilding.Food;
import com.example.android.buddybuilding.Helper;
import com.example.android.buddybuilding.R;

public class FoodActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String foodNameExtra = "FoodName";

    private FoodData foodData = new FoodData(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Add New Food");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        changeScreenToShowFood();

        printFoodsOnDatabase();
    }

    // Functions related to changing the content of the screen

    public void changeScreen(View view) {
        int id = view.getId();

        LinearLayout showFoodsLayout = (LinearLayout) findViewById(R.id.showFoods);
        LinearLayout addFoodLayout = (LinearLayout) findViewById(R.id.addFood);

        switch (id) {
            case (R.id.buttonShowFoods):
                if (showFoodsLayout.getVisibility() != View.VISIBLE) {
                    addFoodLayout.setVisibility(View.GONE);
                    showFoodsLayout.setVisibility(View.VISIBLE);

                    setIndicatorVisible(R.id.indicatorShowFoods);
                    changeButtonTextColor(R.id.buttonShowFoods);
                }
                break;
            case (R.id.buttonAddFood):
                if (addFoodLayout.getVisibility() != View.VISIBLE) {
                    showFoodsLayout.setVisibility(View.GONE);
                    addFoodLayout.setVisibility(View.VISIBLE);

                    setIndicatorVisible(R.id.indicatorAddFood);
                    changeButtonTextColor(R.id.buttonAddFood);
                }
        }
    }

    private void changeScreenToShowFood()
    {
        LinearLayout showFoodsLayout = (LinearLayout) findViewById(R.id.showFoods);
        LinearLayout addFoodLayout = (LinearLayout) findViewById(R.id.addFood);

        addFoodLayout.setVisibility(View.GONE);
        showFoodsLayout.setVisibility(View.VISIBLE);

        setIndicatorVisible(R.id.indicatorShowFoods);

        changeButtonTextColor(R.id.buttonShowFoods);
    }

    private void changeButtonTextColor(int id)
    {
        Button buttonShowFoods = (Button) findViewById(R.id.buttonShowFoods);
        Button buttonAddFood = (Button) findViewById(R.id.buttonAddFood);

        switch (id)
        {
            case (R.id.buttonShowFoods):
                buttonShowFoods.setTextColor(getResources().getColor(R.color.colorPrimary));
                buttonAddFood.setTextColor(getResources().getColor(R.color.black));
                break;
            case (R.id.buttonAddFood):
                buttonAddFood.setTextColor(getResources().getColor(R.color.colorPrimary));
                buttonShowFoods.setTextColor(getResources().getColor(R.color.black));
        }


    }

    private void setIndicatorVisible(int id)
    {
        View showFoodsIndicator = findViewById(R.id.indicatorShowFoods);
        View addFoodIndicator = findViewById(R.id.indicatorAddFood);

        if (id == R.id.indicatorAddFood)
        {
            showFoodsIndicator.setVisibility(View.INVISIBLE);
            addFoodIndicator.setVisibility(View.VISIBLE);
        } else if (id == R.id.indicatorShowFoods)
        {
            showFoodsIndicator.setVisibility(View.VISIBLE);
            addFoodIndicator.setVisibility(View.INVISIBLE);
        } else
        {
            Helper.makeToast("Error in setIndicatorVisible", this);
        }
    }

    // Functions related to showing foods registered

    private void printFoodsOnDatabase()
    {
        LinearLayout layout = (LinearLayout) findViewById(R.id.listOfFoods);

        Cursor cursor = foodData.getAllFoodsNames();

        try {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex(FoodData.COLUMN_NAME));

                printFood(name, layout);
            }
        } finally {
            cursor.close();
        }
    }

    private void printFood(String name, LinearLayout layout)
    {
        TextView textView = new TextView(this);

        textView.setText(name);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFoodNutritionFacts(v);
            }
        });

        int padding = Helper.convertDPToPixel(15);

        textView.setPadding(padding, padding, padding, padding);
        layout.addView(textView);

        View view = getSeparatorView();
        layout.addView(view);
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

    private void updateListOfFoods(Food food)
    {
        LinearLayout layout = (LinearLayout) findViewById(R.id.listOfFoods);

        printFood(food.getName(), layout);
    }

    public void showFoodNutritionFacts(View view)
    {
        Intent intent = new Intent(this, FoodNutritionFacts.class);

        intent.putExtra(foodNameExtra, ((TextView) view).getText().toString());

        startActivity(intent);
    }

    // Functions related to add foods

    public void addFoodToDatabase(View view) {
        String nameOfFood = getNameOfFood();
        if (nameOfFood.equals(""))
        {
            return;
        }

        int servingSize = getServingSize();
        if (servingSize <= 0) {
            return;
        }

        double totalCarbs = getDoubleValue(R.id.carbsInput, Food.MIN_SERVING_SIZE - 1,
                Food.MAX_SERVING_SIZE, "Carbs");
        if (totalCarbs < 0) {
            return;
        }

        double protein = getDoubleValue(R.id.proteinInput, Food.MIN_SERVING_SIZE - 1,
                Food.MAX_SERVING_SIZE, "Protein");
        if (protein < 0) {
            return;
        }

        double totalFat = getDoubleValue(R.id.fatInput, Food.MIN_SERVING_SIZE - 1,
                Food.MAX_SERVING_SIZE, "Fat");
        if (totalFat < 0) {
            return;
        }

        Food newFood = new Food(nameOfFood, servingSize, protein, totalCarbs, totalFat);

        if (!isFoodRegistered(newFood.getName()))
        {
            foodData.addFood(newFood);
            updateListOfFoods(newFood);
            clearAllInputFields();
            Helper.makeToast(nameOfFood + " successfully added!", this);
        } else
        {
            Helper.makeToast(nameOfFood + " is already registered!", this);
        }
    }

    private void clearAllInputFields()
    {
        cleanInputField(R.id.foodName);
        cleanInputField(R.id.servingSize);
        cleanInputField(R.id.carbsInput);
        cleanInputField(R.id.proteinInput);
        cleanInputField(R.id.fatInput);
    }

    private void cleanInputField(int id)
    {
        EditText input = (EditText) findViewById(id);
        input.setText("");
    }

    // Helper Functions



    private boolean isFoodRegistered(String nameOfFood)
    {
        return foodData.isFoodRegistered(nameOfFood);
    }

    private double getDoubleValue(int editTextID, int minValue, int maxValue, String name)
    {
        EditText value = (EditText) findViewById(editTextID);

        String input = value.getText().toString();

        try {
            double number = Double.parseDouble(input);

            if ((number >= minValue) && (number <= maxValue))
            {
                return number;
            } else
            {
                Helper.makeToast(name + " must be between " +
                        Integer.toString(minValue) + "g and " +
                        Integer.toString(maxValue) + "g!", this);
                return -1;
            }
        } catch (NumberFormatException e) {
            Helper.makeToast("Enter a valid " + name + " value!", this);
            return -1;
        }
    }

    private String getNameOfFood()
    {
        EditText input = (EditText) findViewById(R.id.foodName);
        String name = input.getText().toString();

        if (!TextUtils.isEmpty(name))
        {
            return name;
        } else {
            input.setError("Enter a valid name!");
            return "";
        }
    }

    private int getServingSize()
    {
        EditText servingSize = (EditText) findViewById(R.id.servingSize);

        String input = servingSize.getText().toString();

        try {
            int number = Integer.parseInt(input);

            if ((number >= Food.MIN_SERVING_SIZE) && (number <= Food.MAX_SERVING_SIZE))
            {
                return number;
            } else
            {
                Helper.makeToast("Serving size must be between " +
                        Integer.toString(Food.MIN_SERVING_SIZE) + "g and" +
                        Integer.toString(Food.MAX_SERVING_SIZE) + "g!", this);
                return -1;
            }
        } catch (NumberFormatException e) {
            Helper.makeToast("Enter a valid serving size!", this);
            return -1;
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
        getMenuInflater().inflate(R.menu.food, menu);
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


}
