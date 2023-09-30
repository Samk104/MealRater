package com.sam.mealrater;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class MealRaterDataSource {
    private SQLiteDatabase database;
    private DBMealRaterHelper dbHelper;

    public MealRaterDataSource(Context context) {
        dbHelper = new DBMealRaterHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long insertRating(String restaurant, String meal, String rating) {
        ContentValues values = new ContentValues();
        values.put(DBMealRaterHelper.COLUMN_RESTAURANT, restaurant);
        values.put(DBMealRaterHelper.COLUMN_MEAL, meal);
        values.put(DBMealRaterHelper.COLUMN_RATING, rating);

        return database.insert(DBMealRaterHelper.TABLE_RATINGS, null, values);
    }
}
