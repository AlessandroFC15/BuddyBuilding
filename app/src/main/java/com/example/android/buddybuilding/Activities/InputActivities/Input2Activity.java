package com.example.android.buddybuilding.Activities.InputActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

import com.example.android.buddybuilding.R;
import com.example.android.buddybuilding.User.User;

public class Input2Activity extends AppCompatActivity {

    private InputData input = InputData.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input2);
    }

    public void selectActivityLevel(View view)
    {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId())
        {
            case R.id.notVeryActive:
                if (checked) {
                    input.activityLevel = User.NOT_VERY_ACTIVE;
                    break;
                }
            case R.id.lightlyActive:
                if (checked)
                {
                    input.activityLevel = User.LIGHTLY_ACTIVE;
                    break;
                }
            case R.id.active:
                if (checked)
                {
                    input.activityLevel = User.ACTIVE;
                    break;
                }
            case R.id.veryActive:
                if (checked)
                {
                    input.activityLevel = User.VERY_ACTIVE;
                    break;
                }
        }

        startActivity(new Intent(this, Input3Info.class));
    }
}
