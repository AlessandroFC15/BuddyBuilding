package com.example.android.buddybuilding;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

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
                    userData.setGoal(Diet.LOSE_WEIGHT);
                    break;
                }
            case R.id.maintainWeight:
                if (checked)
                {
                    userData.setGoal(Diet.MAINTAIN_WEIGHT);
                    break;
                }
            case R.id.gainWeight:
                if (checked)
                {
                    userData.setGoal(Diet.GAIN_WEIGHT);
                    break;
                }
        }

        startActivity(new Intent(this, Input2Activity.class));
    }
}
