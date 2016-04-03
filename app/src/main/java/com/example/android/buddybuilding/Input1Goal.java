package com.example.android.buddybuilding;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

public class Input1Goal extends AppCompatActivity {

    private User userData = User.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input1_goal);
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
                    userData.setGoal(User.LOSE_WEIGHT);
                    Helper.makeToast("Losing", getApplicationContext());
                    break;
                }
            case R.id.maintainWeight:
                if (checked)
                {
                    userData.setGoal(User.MAINTAIN_WEIGHT);
                    Helper.makeToast("Maintaing", getApplicationContext());
                    break;
                }
            case R.id.gainWeight:
                if (checked)
                {
                    userData.setGoal(User.GAIN_WEIGHT);
                    Helper.makeToast("Gaining", getApplicationContext());
                    break;
                }
        }

        startActivity(new Intent(this, Input2Activity.class));
    }

    private void makeToast(String text) {
        Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
        toast.show();
    }
}
