package com.example.android.buddybuilding;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private User userData = User.getInstance();

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

        calculateBMR();

        updateGoal();
    }

    /*
    Calculo da Taxa Metabólica Basal
    Assumiu-se a formula 32 * kg
     */

    private int calculateGoal()
    {
        switch (userData.getWeeklyGoal())
        {
            case (User.GAIN_250G):
                return 500;
            case (User.GAIN_500G):
                return 750;
            case (User.LOSE_250G):
                return -200;
            case (User.LOSE_500G):
                return -300;
            case (User.LOSE_750G):
                return -400;
            case (User.LOSE_1KG):
                return -500;
            default:
                Helper.makeToast("Error calculating goal",this);
                return -1;
        }
    }

    private void calculateBMR()
    {
        userData.setBMR();
    }

    private void updateGoal()
    {
        TextView caloriesGoal = (TextView) findViewById(R.id.caloriesGoal);

        caloriesGoal.setText(Integer.toString(userData.getBMR() + calculateGoal()));

        updateCaloriesRemaining();
    }

    private int getCaloriesGoal()
    {
        int bmr = userData.getBMR();

        int calories = calculateGoal();

        if (userData.getGoal() == User.GAIN_WEIGHT)
        {
            return bmr + calories;
        } else if (userData.getGoal() == User.LOSE_WEIGHT)
        {
            return bmr - calories;
        } else if (userData.getGoal() == User.MAINTAIN_WEIGHT)
        {
            return bmr;
        } else
        {
            Helper.makeToast("Erro em getCaloriesGoal", this);
            return -1;
        }
    }

    private void updateCaloriesRemaining()
    {
        TextView textCaloriesGoal = (TextView) findViewById(R.id.caloriesGoal);
        TextView textFoodIntake = (TextView) findViewById(R.id.foodIntake);

        int caloriesGoal = Integer.parseInt(textCaloriesGoal.getText().toString());
        int foodIntake = Integer.parseInt(textFoodIntake.getText().toString());

        TextView textRemainingCalories = (TextView) findViewById(R.id.remainingCalories);

        int remainingCalories = caloriesGoal - foodIntake;

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
