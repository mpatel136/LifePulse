package com.saadatdevelopment.lifepulse.searchdialognew.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.saadatdevelopment.lifepulse.searchdialognew.R;
import com.saadatdevelopment.lifepulse.searchdialognew.core.ICustomRecipeInputs;
import com.saadatdevelopment.lifepulse.searchdialognew.bmihealth.fragscustomerecipe.dynamicview.DynamicViews;

/**
 * It's called part2, but it's part1 in the creation process. I just had surgery shuush.
 * A simple {@link Fragment} subclass.
 */
public class CustomRecipePart2 extends DialogFragment implements View.OnClickListener {

    EditText txtFoodName;
    EditText txtDescription;
    Button next;
    Button add;

    public CustomRecipePart2() {
        // Required empty public constructor
    }

    /**
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomFoodDialog);

    }


    /**
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_custom_recpie_part2, container, false);

        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN |     WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //change the layout so that it gets a dialog
        txtFoodName = (EditText) view.findViewById(R.id.txt_inputFoodName);
        txtDescription = (EditText) view.findViewById(R.id.txt_inputDescription);

        add = (Button) view.findViewById(R.id.btn_addIngredients);
        next = (Button) view.findViewById(R.id.btn_next);

        next.setOnClickListener(this);
        add.setOnClickListener(this);

        addLayout = (LinearLayout) view.findViewById(R.id.linearLayout_add);
        dynamicViews = new DynamicViews(context);

        return view;
    }


    Bundle bundle;
    DynamicViews dynamicViews;
    Context context;
    private LinearLayout addLayout;
    /**
     * does an action depending on the view clicked
     * @param v
     */
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_next) {
            //send to part two of this
            bundle = new Bundle();
            bundle.putString("name", txtFoodName.getText().toString());
            bundle.putString("description", txtDescription.getText().toString());
            bundle.putStringArray("ingredients", getEditText());
            dialogPart2();
        }
        //generate edittext
        else if(v.getId() == R.id.btn_addIngredients){
            addLayout.addView(dynamicViews.ingredientEditText(getContext()));
            }

    }


    /**
     * Gets the text of the dynamically generated editText.
     * @return
     */
    private String[] getEditText(){

        String[] list = new String[dynamicViews.getSize()];
        EditText et;
        for(int i = 0; i<dynamicViews.getSize(); i++){
            et = (EditText) getView().findViewById(i);

            list[i] = String.valueOf(et.getText());
        }
        return list;
    }


    /**
     * Sends the relevant info to part2 of this.
     */
    private void dialogPart2(){
        ICustomRecipeInputs activity = (ICustomRecipeInputs) getActivity();
        activity.dialogPart2(bundle);
        dismiss();
    }
}
