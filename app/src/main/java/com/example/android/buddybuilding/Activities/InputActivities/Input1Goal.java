package com.example.android.buddybuilding.Activities.InputActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

import com.example.android.buddybuilding.R;
import com.example.android.buddybuilding.User.User;

public class Input1Goal extends AppCompatActivity {

    private InputData input = InputData.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input1_goal);
        setTitle("Objetivo");
    }

    public void selectGoal(View view)
    {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId())
        {
            case R.id.loseWeight:
                if (checked) {
                    input.goal = User.Goal.LOSE_WEIGHT;
                    break;
                }
            case R.id.maintainWeight:
                if (checked)
                {
                    input.goal = User.Goal.MAINTAIN_WEIGHT;
                    break;
                }
            case R.id.gainWeight:
                if (checked)
                {
                    input.goal = User.Goal.GAIN_WEIGHT;
                    break;
                }
        }

        startActivity(new Intent(this, Input2Activity.class));
    }
}
