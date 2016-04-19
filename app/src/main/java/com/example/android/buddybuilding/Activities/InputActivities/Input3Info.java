package com.example.android.buddybuilding.Activities.InputActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.android.buddybuilding.Helper;
import com.example.android.buddybuilding.R;
import com.example.android.buddybuilding.User.User;

public class Input3Info extends AppCompatActivity {

    private InputData input = InputData.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input3_info);
        setTitle("Você");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void selectGender(View view)
    {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId())
        {
            case R.id.male:
                if (checked) {
                    input.gender = User.Gender.MALE;
                    break;
                }
            case R.id.female:
                if (checked)
                {
                    input.gender = User.Gender.FEMALE;
                    break;
                }
        }
    }

    private boolean isGenderSelected()
    {
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.choiceOfGender);

        return radioGroup.getCheckedRadioButtonId() != -1;
    }

    private int getAge()
    {
        EditText age = (EditText) findViewById(R.id.age);

        String input = age.getText().toString();

        try {
            int number = Integer.parseInt(input);

            if ((number >= User.MIN_AGE) && (number <= User.MAX_AGE))
            {
                return number;
            } else
            {
                Helper.makeToast("Age must be between 0 and 150!", this);
                return -1;
            }
        } catch (NumberFormatException e) {
            Helper.makeToast("Enter a valid age!", this);
            return -1;
        }
    }

    public void changeActivity(View view)
    {
        if (isGenderSelected())
        {
            int age = getAge();

            if (age != -1) {
                input.age = age;

                startActivity(new Intent(this, Input4Info.class));
            }
        } else
        {
            Helper.makeToast("Select a gender!", this);
        }
    }
}
