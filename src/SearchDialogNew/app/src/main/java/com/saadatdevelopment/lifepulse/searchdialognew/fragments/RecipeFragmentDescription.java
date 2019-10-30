package com.saadatdevelopment.lifepulse.searchdialognew.fragments;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.saadatdevelopment.lifepulse.searchdialognew.R;
import com.example.lifepulselibrary.Recipe;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeFragmentDescription extends DialogFragment {


    Recipe recipe;
    public RecipeFragmentDescription() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe_fragment_description, container, false);

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        recipe = getArguments().getParcelable("recipe");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(recipe.getDescription()).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        return builder.create();
    }
}
