package com.example.android.buddybuilding;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Alessandro on 03/04/2016.
 */
public class Helper {

    public static void makeToast(String text, Context context)
    {
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        toast.show();
    }
}
