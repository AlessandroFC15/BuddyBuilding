package com.example.android.buddybuilding;

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
import android.widget.EditText;
import android.widget.LinearLayout;

public class AddNewFood extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

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

    public void changeScreen(View view) {
        int id = view.getId();

        LinearLayout showFoodsLayout = (LinearLayout) findViewById(R.id.showFoods);
        LinearLayout addFoodLayout = (LinearLayout) findViewById(R.id.addFood);

        switch (id) {
            case (R.id.buttonShowFoods):
                if (showFoodsLayout.getVisibility() != View.VISIBLE) {
                    addFoodLayout.setVisibility(View.GONE);
                    showFoodsLayout.setVisibility(View.VISIBLE);
                }
                break;
            case (R.id.buttonAddFood):
                if (addFoodLayout.getVisibility() != View.VISIBLE) {
                    showFoodsLayout.setVisibility(View.GONE);
                    addFoodLayout.setVisibility(View.VISIBLE);
                }
        }
    }

    public void addFoodToDatabase(View view) {
        String nameOfFood = getNameOfFood();
        if (nameOfFood.equals("")) {
            return;
        }

        int servingSize = getServingSize();
        if (servingSize <= 0) {
            return;
        }

        double totalCarbs = getDoubleValue(R.id.carbsInput, Food.MIN_SERVING_SIZE,
                Food.MAX_SERVING_SIZE, "carbs");
        if (totalCarbs <= 0) {
            return;
        }

        double protein = getDoubleValue(R.id.proteinInput, Food.MIN_SERVING_SIZE,
                Food.MAX_SERVING_SIZE, "protein");
        if (protein <= 0) {
            return;
        }

        double totalFat = getDoubleValue(R.id.fatInput, Food.MIN_SERVING_SIZE,
                Food.MAX_SERVING_SIZE, "fat");
        if (totalFat <= 0) {
            return;
        }
        
        Helper.makeToast("OK", this);
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
}
