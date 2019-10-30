package com.saadatdevelopment.lifepulse.searchdialognew.foods;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.saadatdevelopment.lifepulse.searchdialognew.R;

/**
 * Fragment for filtering/ordering the food
 */
public class FilterFood extends DialogFragment{

    //Declare variables
    static final String BUTTON_STATE = "Button_State";
    static final String MYPREFERENCES = "MyPreferences";
    SharedPreferences radioButtonIndicator;
    Button btnOk;
    RadioButton selectedRadioButton;
    RadioButton radioButtonDefault;
    RadioGroup radioGroup;
    int selectedId;

    /**
     * Default constructor
     */
    public FilterFood()
    {

    }


    /**
     * Locates the views, sets the default checked button, and gets the shared preferences of the last button selected
     * @param inflater LayoutInflater to match corresponding XML
     * @param container Group of views
     * @param savedInstanceState Collection of key value pairs
     * @return The view of the fragment
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filter_food, container, false);

//        btnOk =(Button) view.findViewById(R.id.btnOk);
//        radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);
//        radioButtonDefault = (RadioButton) view.findViewById(R.id.alphaDescending);
//        radioButtonDefault.setChecked(true);
//        selectedId = radioGroup.getCheckedRadioButtonId();
//        selectedRadioButton = (RadioButton) view.findViewById(selectedId);
//
//        radioButtonIndicator = this.getActivity().getSharedPreferences(MYPREFERENCES, Context.MODE_PRIVATE);
//        Boolean lastButtonState = radioButtonIndicator.getBoolean(RADIO_BUTTON_CHECKED, false);
//
//        selectedRadioButton.setChecked(lastButtonState);
//
//        selectedRadioButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SharedPreferences.Editor editor = radioButtonIndicator.edit();
//                Boolean isChecked = selectedRadioButton.isChecked();
//                editor.putBoolean(RADIO_BUTTON_CHECKED, isChecked);
//                editor.apply();
//            }
//        });


        //Sets on click listener to the ok button
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Close the fragment on ok button press
                dismiss();
            }
        });

        //Return the view of the fragment
        return view;
    }

}
