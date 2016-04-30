package com.example.android.buddybuilding.Activities.Diary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.android.buddybuilding.Food.Food;
import com.example.android.buddybuilding.Helper;
import com.example.android.buddybuilding.R;
import com.example.android.buddybuilding.User.User;

public class AddFood extends AppCompatActivity implements ServingSize.NoticeDialogListener {

    private User userData = User.getInstance();
    private Food foodToBeAdded = userData.getDiet().getFoodToBeAdded();
    private int nameOfMeal;
    public float numberOfServings = 1;
    public int servingSize = foodToBeAdded.getServingSize();

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

    private void addFoodToMeal() {
        userData.addFood(nameOfMeal, foodToBeAdded);

        Helper.makeToast(foodToBeAdded.getName() + " successfully added to " + SelectFood.getNameOfMeal(nameOfMeal), this);

        startActivity(new Intent(this, Diary.class));
    }

    private void updateNameOfFood() {
        TextView name = (TextView) findViewById(R.id.nameOfFood);

        name.setText(foodToBeAdded.getName());
    }

    private void updateServingSize() {
        TextView servingSize = (TextView) findViewById(R.id.addFood_servingSize);

        servingSize.setText(String.format("%d", foodToBeAdded.getServingSize()));
    }

    private void updateMacros() {
        TextView carbs = (TextView) findViewById(R.id.addFood_carbs);
        TextView protein = (TextView) findViewById(R.id.addFood_protein);
        TextView fat = (TextView) findViewById(R.id.addFood_fat);
        TextView calories = (TextView) findViewById(R.id.addFood_calories);

        carbs.setText(String.format("%.1f", foodToBeAdded.getCarbs()));
        protein.setText(String.format("%.1f", foodToBeAdded.getProtein()));
        fat.setText(String.format("%.1f", foodToBeAdded.getTotalFat()));
        calories.setText(String.format("%d", foodToBeAdded.getCalories()));
    }

    public void selectServingSize(View view) {
        DialogFragment newFragment = ServingSize.newInstance(servingSize, numberOfServings);

        newFragment.show(getSupportFragmentManager(), "Missiles");
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
            if (numberOfServings > 0) {
                if (servingSize >= Food.MIN_SERVING_SIZE && servingSize <= Food.MAX_SERVING_SIZE) {
                    addFoodToMeal();
                } else {
                    Helper.makeToast("Invalid serving size!", this);
                }
            } else {
                Helper.makeToast("Invalid number of servings!", this);
            }
        }

        return super.onOptionsItemSelected(item);
    }

    // The dialog fragment receives a reference to this Activity through the
    // Fragment.onAttach() callback, which it uses to call the following methods
    // defined by the NoticeDialogFragment.NoticeDialogListener interface
    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        numberOfServings = getNumberOfServings();
        updateNumberOfServings(numberOfServings);

        servingSize = getServingSize();
        updateServingSize(servingSize);

        if (numberOfServings > 0 && servingSize >= 1) {
            if (foodToBeAdded.changeServingSize((int) (numberOfServings * servingSize))) {
                updateMacros();
            }
        }
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }

    private void updateNumberOfServings(float numberOfServings) {
        TextView servings = (TextView) findViewById(R.id.addFood_numberOfServings);

        servings.setText(Float.toString(numberOfServings));
    }

    private void updateServingSize(int servingSize) {
        TextView text = (TextView) findViewById(R.id.addFood_servingSize);

        text.setText(Integer.toString(servingSize));
    }

    private float getNumberOfServings() {
        String input = ServingSize.numberOfServingsInput.getText().toString();

        try {
            return Float.parseFloat(input);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private int getServingSize() {
        String input = ServingSize.servingSizeInput.getText().toString();

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
