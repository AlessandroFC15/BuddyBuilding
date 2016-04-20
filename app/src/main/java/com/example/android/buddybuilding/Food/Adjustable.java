package com.example.android.buddybuilding.Food;

/**
 * Created by Alessandro on 19/04/2016.
 */
public interface Adjustable {

    public void changeProtein(double newValue);

    public void changeCarbs(double newValue);

    public void changeFat(double newValue);

    public void changeServingSize(int newValue);

    public void changeName(final String newValue);

}
