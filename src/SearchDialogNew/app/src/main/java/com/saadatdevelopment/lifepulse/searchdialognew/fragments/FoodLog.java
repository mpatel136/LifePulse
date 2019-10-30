package com.saadatdevelopment.lifepulse.searchdialognew.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.saadatdevelopment.lifepulse.searchdialognew.R;

/**
 * A simple {@link Fragment} subclass.
 */

//TO IMPLEMENT, UI DONE-ISH
public class FoodLog extends Fragment {


    public FoodLog() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_food_log, container, false);
    }

}
