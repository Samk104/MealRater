package com.sam.mealrater;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MealRating#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MealRating extends Fragment {


    private static final int YES = 0;

    private static final int NO = 1;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String dishRating;

    private Button mButton;

    public MealRating() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MealRating.
     */
    // TODO: Rename and change types and number of parameters
    public static MealRating newInstance(String param1, String param2) {
        MealRating fragment = new MealRating();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView =
                inflater.inflate(R.layout.fragment_meal_rating, container, false);

        final Button mButton = rootView.findViewById(R.id.saveButton);
        final RatingBar ratingBar = rootView.findViewById(R.id.rating_bar);



        mButton.setOnClickListener(new View.OnClickListener()    {
        @Override
                public void onClick(View v){
                dishRating = String.valueOf(ratingBar.getRating());

            MainActivity mainActivity = (MainActivity) getActivity();
            if(mainActivity!=null){
                mainActivity.updateRating(dishRating);
            }
            // Get the FragmentManager.
            FragmentManager fragmentManager = getFragmentManager();

            if (fragmentManager != null) {
                // Create and commit the transaction to remove the fragment.
                        fragmentManager.beginTransaction().remove(MealRating.this).commit();
            }

        }
        });
        // Return the View for the fragment's UI.
        return rootView;
    }



    public static MealRating newInstance() {
        return new MealRating();
    }
}