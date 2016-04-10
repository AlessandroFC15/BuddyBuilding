package com.example.android.buddybuilding;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Alessandro on 09/04/2016.
 */
public class FoodData extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "FoodData";
    private static final int DATABASE_VERSION = 1;

    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " INTEGER";
    private static final String REAL_TYPE = " REAL";

    private static final String COMMA_SEP = ",";

    /* Table of Food
     * ID | Name | Carbs | Protein | Fat | Calories
     */

    public static final String TABLE_NAME = "foods";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_CARBS = "total_carbs";
    public static final String COLUMN_PROTEIN = "total_protein";
    public static final String COLUMN_FAT = "total_fat";
    public static final String COLUMN_SERVING_SIZE = "serving_size";
    public static final String COLUMN_CALORIES = "total_cal";
    private static final String CREATE_TABLE_FOOD =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + INT_TYPE + " PRIMARY KEY," +
                    COLUMN_NAME + TEXT_TYPE + COMMA_SEP +
                    COLUMN_CARBS + REAL_TYPE + COMMA_SEP +
                    COLUMN_PROTEIN + REAL_TYPE + COMMA_SEP +
                    COLUMN_FAT + REAL_TYPE + COMMA_SEP +
                    COLUMN_SERVING_SIZE + INT_TYPE + COMMA_SEP +
                    COLUMN_CALORIES + INT_TYPE + " )";

    private static final String DELETE_TABLE_FOOD =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    FoodData(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_FOOD);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(DELETE_TABLE_FOOD);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void addFood(String name) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_CARBS, 1);
        values.put(COLUMN_PROTEIN, 1);
        values.put(COLUMN_FAT, 1);
        values.put(COLUMN_SERVING_SIZE, 1);
        values.put(COLUMN_CALORIES, 100);

        // Insert the new row, returning the primary key value of the new row
        db.insert(TABLE_NAME, "null", values);
    }

    public void addFood(Food food) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, food.getName());
        values.put(COLUMN_CARBS, food.getCarbs());
        values.put(COLUMN_PROTEIN, food.getProtein());
        values.put(COLUMN_FAT, food.getTotalFat());
        values.put(COLUMN_SERVING_SIZE, food.getServingSize());
        values.put(COLUMN_CALORIES, food.getCalories());

        // Insert the new row, returning the primary key value of the new row
        db.insert(TABLE_NAME, "null", values);
    }
}
