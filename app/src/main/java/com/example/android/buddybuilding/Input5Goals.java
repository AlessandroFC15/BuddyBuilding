package com.example.android.buddybuilding;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
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
        if (userData.getGoal() == userData.LOSE_WEIGHT)
        {
            Helper.makeToast("Lose Weight", this);
            RadioGroup radioGroup = (RadioGroup) findViewById(R.id.weeklyGoalLoss);
            radioGroup.setVisibility(View.VISIBLE);
        } else if (userData.getGoal() == userData.GAIN_WEIGHT)
        {
            Helper.makeToast("Gain Weight", this);
            RadioGroup radioGroup = (RadioGroup) findViewById(R.id.weeklyGoalGain);
            radioGroup.setVisibility(View.VISIBLE);
        } else
        {
            Helper.makeToast("Error configuring Radio Group", this);
        }
    }
}
