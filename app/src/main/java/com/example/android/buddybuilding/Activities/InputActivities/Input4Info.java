package com.example.android.buddybuilding.Activities.InputActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.example.android.buddybuilding.Activities.Home;
import com.example.android.buddybuilding.Helper;
import com.example.android.buddybuilding.R;
import com.example.android.buddybuilding.User.User;

public class Input4Info extends AppCompatActivity {

    private InputData input = InputData.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input4_info);
        setTitle("Você");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private double getHeight()
    {
        EditText input = (EditText) findViewById(R.id.height);

        String height = input.getText().toString();

        try {
            double number = Double.parseDouble(height);

            if ((number >= User.MIN_HEIGHT) && (number <= User.MAX_HEIGHT))
            {
                return number;
            } else
            {
                Helper.makeToast("Height must be between " + User.MIN_HEIGHT
                        + " and " + User.MAX_HEIGHT + "!", this);
                return -1;
            }
        } catch (NumberFormatException e)
        {
            Helper.makeToast("Enter a valid height!", this);
            return -1;
        }
    }

    private double getWeight()
    {
        EditText input = (EditText) findViewById(R.id.weight);

        String height = input.getText().toString();

        try {
            double number = Double.parseDouble(height);

            if ((number >= User.MIN_WEIGHT) && (number <= User.MAX_WEIGHT))
            {
                return number;
            } else
            {
                Helper.makeToast("Weight must be between " + User.MIN_WEIGHT
                        + " and " + User.MAX_WEIGHT + "!", this);
                return -1;
            }
        } catch (NumberFormatException e)
        {
            Helper.makeToast("Enter a valid weight!", this);
            return -1;
        }
    }

    public void changeActivity(View view)
    {
        double height = getHeight();

        if (height != -1)
        {
            double weight = getWeight();

            if (weight != -1)
            {
                input.height = height;
                input.weight = weight;

                if (input.goal == User.Goal.MAINTAIN_WEIGHT)
                {
                    input.weeklyGoal = null;
                    startActivity(new Intent(this, Home.class));
                } else
                {
                    startActivity(new Intent(this, Input5Goals.class));
                }

            }
        }
    }

}
