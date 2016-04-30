package com.example.android.buddybuilding.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.android.buddybuilding.Activities.Diary.Diary;
import com.example.android.buddybuilding.Helper;
import com.example.android.buddybuilding.R;
import com.example.android.buddybuilding.User.Person;
import com.example.android.buddybuilding.User.User;

import java.util.Calendar;

public class Goals extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, CurrentWeight.OnFragmentInteractionListener {

    private User userData = User.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        checkGoalInMenu();

        updateValues();
    }

    public void onResume()
    {
        super.onResume();

        checkGoalInMenu();

        updateValues();
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
        getMenuInflater().inflate(R.menu.goals, menu);
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
        } else if (id == R.id.nav_nutrition) {
            startActivity(new Intent(this, Nutrition.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void checkGoalInMenu()
    {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_goals);
    }

    private void updateValues()
    {
        updateStartingWeight();
        updateCurrentWeight();
        updateGoalWeight();
        updateWeeklyGoal();
        updateActivityLevel();

        updateNutritionGoals();
    }

    private void updateStartingWeight()
    {
        TextView textView = (TextView) findViewById(R.id.startingWeight);

        textView.setText(String.format("%.1f kg on %s", userData.getWeight(), getCurrentDate()));
    }

    private String getCurrentDate()
    {
        Calendar calendar = Calendar.getInstance();
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        String strMonth = getMonthString(calendar.get(Calendar.MONTH));
        int year = calendar.get(Calendar.YEAR);

        return strMonth + " " + dayOfMonth + ", " + year;
    }

    private String getMonthString(int month)
    {
        switch (month) {
            case Calendar.JANUARY:
                return "Jan";
            case Calendar.FEBRUARY:
                return "Feb";
            case Calendar.MARCH:
                return "Mar";
            case Calendar.APRIL:
                return "Apr";
            case Calendar.MAY:
                return "May";
            case Calendar.JUNE:
                return "Jun";
            case Calendar.JULY:
                return "Jul";
            case Calendar.AUGUST:
                return "Aug";
            case Calendar.SEPTEMBER:
                return "Sep";
            case Calendar.OCTOBER:
                return "Oct";
            case Calendar.NOVEMBER:
                return "Nov";
            case Calendar.DECEMBER:
                return "Dec";
            default:
                return "XXX";

        }
    }

    private void updateCurrentWeight()
    {
        TextView textView = (TextView) findViewById(R.id.currentWeight);

        textView.setText(String.format("%.1f kg", userData.getWeight()));
    }

    private void updateGoalWeight()
    {
        TextView textView = (TextView) findViewById(R.id.goals_goalWeight);

        textView.setText(String.format("%.1f kg", userData.getGoalWeight()));
    }

    private void updateWeeklyGoal()
    {
        TextView textView = (TextView) findViewById(R.id.goals_weeklyGoal);

        textView.setText(userData.getWeeklyGoal().toString());
    }

    private void updateActivityLevel()
    {
        TextView textView = (TextView) findViewById(R.id.goals_activityLevel);

        textView.setText(userData.getActivityLevel().toString());
    }

    private void updateNutritionGoals()
    {
        updateCalories();
        updateCarbs();
        updateProtein();
        updateFat();
    }

    private void updateCalories()
    {
        TextView textView = (TextView) findViewById(R.id.goals_calories);

        textView.setText(Integer.toString(userData.getCaloriesTarget()));
    }

    private void updateCarbs()
    {
        updatePercentage(R.id.goals_carbsPercentage, (int) userData.getDiet().getCarbsPercentageGoal());
        updateValue(R.id.goals_carbsValue, (int) userData.getDiet().getCarbsTarget());
    }

    private void updateProtein()
    {
        updatePercentage(R.id.goals_proteinPercentage, (int) userData.getDiet().getProteinPercentageGoal());
        updateValue(R.id.goals_proteinValue, (int) userData.getDiet().getProteinTarget());
    }

    private void updateFat()
    {
        updatePercentage(R.id.goals_fatPercentage, (int) userData.getDiet().getFatPercentageGoal());
        updateValue(R.id.goals_fatValue, (int) userData.getDiet().getFatTarget());
    }

    private void updateValue(int id, int value)
    {
        TextView textView = (TextView) findViewById(id);

        textView.setText(String.format("%dg", value));
    }

    private void updatePercentage(int id, int value)
    {
        TextView textView = (TextView) findViewById(id);

        textView.setText(String.format("%d%%", value));
    }

    public void changeCurrentWeight(View view)
    {
        DialogFragment newFragment = CurrentWeight.newInstance();

        newFragment.show(getSupportFragmentManager(), "Missiles");
    }

    public void onDialogPositiveClick(DialogFragment dialog) {
        double weight = getNewWeight();

        if (weight >= Person.MIN_WEIGHT && weight <= Person.MAX_WEIGHT) {
            userData.setWeight(weight);
            updateCurrentWeight();
            updateNutritionGoals();

            Helper.makeToast("Weight updated!", this);
        } else
        {
            Helper.makeToast("Invalid weight entered. Try again!", this);
        }
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }

    private double getNewWeight()
    {
        String input = CurrentWeight.weightInput.getText().toString();

        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
