package com.sam.mealrater;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBMealRaterHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MealRater.db";
    private static final int DATABASE_VERSION = 1;

    // Define table and column names
    public static final String TABLE_RATINGS = "food_info";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_RESTAURANT = "restaurant";
    public static final String COLUMN_MEAL = "meal";
    public static final String COLUMN_RATING = "rating";

    // Create the table SQL statement
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_RATINGS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_RESTAURANT + " TEXT, " +
                    COLUMN_MEAL + " TEXT, " +
                    COLUMN_RATING + " TEXT" +
                    ");";

    public DBMealRaterHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RATINGS);
        onCreate(db);
    }
}
