package com.example.android.buddybuilding.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.android.buddybuilding.Activities.InputActivities.InputData;
import com.example.android.buddybuilding.R;
import com.example.android.buddybuilding.User.User;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private InputData input = InputData.getInstance();

    private User userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Creates the new user with the data from the input and sets the shared variable
        // to the newly created object

        // User.createNewUser(input);

        User.createNewUser();
        userData = User.getInstance();

        updateCaloriesTarget();

        updateFoodCalories();

        updateCaloriesRemaining();
    }

    public void onResume()
    {
        super.onResume();

        updateCaloriesTarget();

        updateFoodCalories();

        updateCaloriesRemaining();
    }

    /*
    Calculo da Taxa Metabólica Basal
    Assumiu-se a formula 32 * kg
     */

    private void updateCaloriesTarget()
    {
        TextView caloriesGoal = (TextView) findViewById(R.id.caloriesGoal);

        caloriesGoal.setText(Integer.toString((int) userData.getCaloriesTarget()));
    }

    private void updateFoodCalories()
    {
        TextView foodCalories = (TextView) findViewById(R.id.foodIntake);

        foodCalories.setText(Integer.toString((int) userData.getCaloriesIntake()));
    }

    private void updateCaloriesRemaining()
    {
        TextView textCaloriesGoal = (TextView) findViewById(R.id.caloriesGoal);
        TextView textFoodIntake = (TextView) findViewById(R.id.foodIntake);
        TextView textRemainingCalories = (TextView) findViewById(R.id.remainingCalories);

        int remainingCalories = (int) userData.getCaloriesTarget() - (int) userData.getCaloriesIntake();

        textRemainingCalories.setText(Integer.toString(remainingCalories));
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

        if (id == R.id.nav_diary) {
            startActivity(new Intent(this, Diary.class));
        } else if (id == R.id.nav_foods) {
            startActivity(new Intent(this, FoodActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
