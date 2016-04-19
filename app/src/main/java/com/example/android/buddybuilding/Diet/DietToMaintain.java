package com.example.android.buddybuilding.Diet;

/**
 * Created by Alessandro on 19/04/2016.
 */
public class DietToMaintain extends Diet {
    public DietToMaintain()
    {
    }

    public DietToMaintain(int bmr)
    {
        totalCaloriesTarget = bmr;
    }

    public DietToMaintain(final DietToMaintain diet)
    {
        super(diet);

    }
}
