package com.example.android.buddybuilding.Activities.Diary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.android.buddybuilding.Food.Food;
import com.example.android.buddybuilding.Helper;
import com.example.android.buddybuilding.R;
import com.example.android.buddybuilding.User.User;

public class AddFood extends AppCompatActivity {

    private User userData = User.getInstance();
    private Food foodToBeAdded = userData.getDiet().getFoodToBeAdded();
    private int nameOfMeal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Add Food");

        Intent intent = getIntent();

        nameOfMeal = intent.getIntExtra(SelectFood.EXTRA_NAME_MEAL, -1);

        updateNameOfFood();

        updateServingSize();

        updateMacros();
    }

    private void addFoodToMeal()
    {
        userData.addFood(nameOfMeal, foodToBeAdded);

        Helper.makeToast(foodToBeAdded.getName() + " successfully added to " + SelectFood.getNameOfMeal(nameOfMeal), this);

        startActivity(new Intent(this, Diary.class));
    }

    private void updateNameOfFood()
    {
        TextView name = (TextView) findViewById(R.id.nameOfFood);

        name.setText(foodToBeAdded.getName());
    }

    private void updateServingSize()
    {
        TextView servingSize = (TextView) findViewById(R.id.addFood_servingSize);

        servingSize.setText(String.format("%d", foodToBeAdded.getServingSize()));
    }

    private void updateMacros()
    {
        TextView carbs = (TextView) findViewById(R.id.addFood_carbs);
        TextView protein = (TextView) findViewById(R.id.addFood_protein);
        TextView fat = (TextView) findViewById(R.id.addFood_fat);
        TextView calories = (TextView) findViewById(R.id.addFood_calories);

        carbs.setText(String.format("%.1f", foodToBeAdded.getCarbs()));
        protein.setText(String.format("%.1f", foodToBeAdded.getProtein()));
        fat.setText(String.format("%.1f", foodToBeAdded.getTotalFat()));
        calories.setText(String.format("%d", foodToBeAdded.getCalories()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_food, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.finished) {
            addFoodToMeal();
        }

        return super.onOptionsItemSelected(item);
    }
}
