package com.saadatdevelopment.lifepulse.searchdialognew.foods;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.saadatdevelopment.lifepulse.searchdialognew.R;


/**
 * Fragment for checking heart rate
 */
public class fragment_heart_rate_reminder extends DialogFragment implements View.OnClickListener
{
    //Declare variables
    Button btnSubmit;
    Button btnCancel;

    /**
     * Default constructor
     */
    public fragment_heart_rate_reminder()
    {

    }

    /**
     * Set the width and height of the dialog
     */
    @Override
    public void onStart()
    {
        //Get the dialog and sets the width and height to match the constraints
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null)
        {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    /**
     * Locates the views and sets onclick listeners
     * @param inflater LayoutInflater to match corresponding XML
     * @param container Group of views
     * @param savedInstanceState Collection of key value pairs
     * @return The view of the fragment
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_heart_rate_reminder, container);

        btnCancel = (Button)view.findViewById(R.id.btnCancel);
        btnSubmit = (Button)view.findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        return view;
    }

    //On button press, close the fragment
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnSubmit)
        {
            dismiss();
        }
        else
        {
            dismiss();
        }
    }
}
