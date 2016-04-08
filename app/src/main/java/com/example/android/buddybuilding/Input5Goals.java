package com.example.android.buddybuilding;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Input5Goals extends AppCompatActivity {

    private User userData = User.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input5_goals);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        configureRadioGroup();
    }

    private void configureRadioGroup()
    {
        if (userData.getGoal() == Diet.LOSE_WEIGHT)
        {
            RadioGroup radioGroup = (RadioGroup) findViewById(R.id.weeklyGoalLoss);
            radioGroup.setVisibility(View.VISIBLE);
        } else if (userData.getGoal() == Diet.GAIN_WEIGHT)
        {
            RadioGroup radioGroup = (RadioGroup) findViewById(R.id.weeklyGoalGain);
            radioGroup.setVisibility(View.VISIBLE);
        } else
        {
            Helper.makeToast("Error configuring Radio Group", this);
        }
    }

    public void selectWeeklyGoal(View view)
    {
        if (userData.getGoal() == Diet.LOSE_WEIGHT)
        {
            selectLossGoal(view);
        } else if (userData.getGoal() == Diet.GAIN_WEIGHT)
        {
            selectGainGoal(view);
        } else
        {
            Helper.makeToast("Error selecting weekly goal", this);
        }
    }

    private void selectGainGoal(View view)
    {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId())
        {
            case R.id.gain250g:
                if (checked) {
                    userData.setWeeklyGoal(User.GAIN_250G);
                    break;
                }
            case R.id.gain500g:
                if (checked)
                {
                    userData.setWeeklyGoal(User.GAIN_500G);
                    break;
                }
        }
    }

    private void selectLossGoal(View view)
    {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId())
        {
            case R.id.lose250g:
                if (checked) {
                    userData.setWeeklyGoal(User.LOSE_250G);
                    break;
                }
            case R.id.lose500g:
                if (checked)
                {
                    userData.setWeeklyGoal(User.LOSE_500G);
                    break;
                }
            case R.id.lose750g:
                if (checked)
                {
                    userData.setWeeklyGoal(User.LOSE_750G);
                    break;
                }
            case R.id.lose1kg:
                if (checked)
                {
                    userData.setWeeklyGoal(User.LOSE_1KG);
                    break;
                }
        }
    }

    private double getWeightGoal()
    {
        EditText input = (EditText) findViewById(R.id.goalWeight);

        String height = input.getText().toString();

        try {
            double number = Double.parseDouble(height);

            if (isWeightGoalValid(number))
            {
                return number;
            }

            return -1;

        } catch (NumberFormatException e)
        {
            Helper.makeToast("Enter a valid weight!", this);
            return -1;
        }
    }

    private boolean isWeightGoalValid(double number)
    {
        if ((number >= User.MIN_WEIGHT) && (number <= User.MAX_WEIGHT))
        {
            if (userData.getGoal() == Diet.GAIN_WEIGHT)
            {
                if (number > userData.getWeight())
                {
                    return true;
                } else
                {
                    Helper.makeToast("O peso desejado deve ser maior que o seu peso atual", this);
                    return false;
                }
            } else
            {
                if (number < userData.getWeight())
                {
                    return true;
                } else
                {
                    Helper.makeToast("O peso desejado deve ser menor que o seu peso atual", this);
                    return false;
                }
            }

            // return number;
        } else
        {
            Helper.makeToast("Goal weight must be between " + User.MIN_WEIGHT
                    + " and " + User.MAX_WEIGHT + "!", this);
            return false;
        }
    }

    private boolean isWeeklyGoalSelected()
    {
        RadioGroup radioGroup1 = (RadioGroup) findViewById(R.id.weeklyGoalGain);
        RadioGroup radioGroup2 = (RadioGroup) findViewById(R.id.weeklyGoalLoss);

        return ((radioGroup1.getCheckedRadioButtonId() != -1)
                || radioGroup2.getCheckedRadioButtonId() != -1);
    }

    public void changeActivity(View view)
    {
        double goalWeight = getWeightGoal();

        if (goalWeight != -1)
        {
            if (isWeeklyGoalSelected())
            {
                startActivity(new Intent(this, Home.class));
            } else
            {
                Helper.makeToast("Select a weekly goal!", this);
            }
        }
    }
}
