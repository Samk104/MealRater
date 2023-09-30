package com.sam.mealrater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView user_rating;
    private Button mButton;
    private Button saveButton;

    private EditText restaurant;
    private EditText dish;

    private boolean isFragmentDisplayed = false;

    static final String STATE_FRAGMENT = "state_of_fragment";

    MealRaterDataSource mDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize database
        mDB = new MealRaterDataSource(this);
        mDB.open();

        saveButton = findViewById(R.id.saveDB);
        restaurant = findViewById(R.id.resName);
        dish = findViewById(R.id.dishName);

        mButton = findViewById(R.id.rateMeal);
        user_rating = findViewById(R.id.givenRating);

        // Set the click listener for the button.
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isFragmentDisplayed) {
                    displayFragment();
                } else {
                   isFragmentDisplayed = false;
                }
            }
        });


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the restaurant name, meal, and rating
                String restaurantName = restaurant.getText().toString();
                String mealName = dish.getText().toString();

                // Insert the data into the database
                long insertedRowId = mDB.insertRating(restaurantName, mealName, user_rating.getText().toString());

                if (insertedRowId != -1) {
                    // Data inserted successfully
                    Toast.makeText(MainActivity.this, "Data saved successfully", Toast.LENGTH_SHORT).show();
                } else {
                    // data insertion failed
                    Toast.makeText(MainActivity.this, "Error Encountered!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        if (savedInstanceState != null) {
            isFragmentDisplayed =
                    savedInstanceState.getBoolean(STATE_FRAGMENT);
        }
    }
    public void displayFragment() {
        MealRating ratingFragment = MealRating.newInstance();

        // Get the FragmentManager and start a transaction.
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();

        // Add the SimpleFragment.
        fragmentTransaction.add(R.id.fragment_container,
                ratingFragment).addToBackStack(null).commit();

        // Set boolean flag to indicate fragment is open.
        isFragmentDisplayed = true;
    }

    public void updateRating(String rating){
        if(user_rating!=null){
            user_rating.setText(rating + " Stars");
        }
        user_rating.setVisibility(View.VISIBLE);
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the state of the fragment (true=open, false=closed).
        savedInstanceState.putBoolean(STATE_FRAGMENT, isFragmentDisplayed);
        super.onSaveInstanceState(savedInstanceState);
    }


}