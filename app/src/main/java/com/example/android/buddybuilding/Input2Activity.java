package com.example.android.buddybuilding;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

public class Input2Activity extends AppCompatActivity {

    private User userData = User.getInstance();

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
                    userData.setActivityLevel(User.NOT_VERY_ACTIVE);
                    break;
                }
            case R.id.lightlyActive:
                if (checked)
                {
                    userData.setActivityLevel(User.LIGHTLY_ACTIVE);
                    break;
                }
            case R.id.active:
                if (checked)
                {
                    userData.setActivityLevel(User.ACTIVE);
                    break;
                }
            case R.id.veryActive:
                if (checked)
                {
                    userData.setActivityLevel(User.VERY_ACTIVE);
                    break;
                }
        }

        startActivity(new Intent(this, Input3Info.class));
    }
}
