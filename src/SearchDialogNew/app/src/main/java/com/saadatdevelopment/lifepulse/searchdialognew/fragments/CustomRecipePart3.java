package com.saadatdevelopment.lifepulse.searchdialognew.fragments;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.saadatdevelopment.lifepulse.searchdialognew.R;
import com.saadatdevelopment.lifepulse.searchdialognew.core.ICustomRecipeInputs;
import com.example.lifepulselibrary.Recipe;
import com.saadatdevelopment.lifepulse.searchdialognew.bmihealth.fragscustomerecipe.dynamicview.DynamicViews;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.ArrayList;

/**
 * Creates a recipe object.
 * A simple {@link Fragment} subclass.
 */
public class CustomRecipePart3 extends DialogFragment implements View.OnClickListener {

    private DynamicViews dynamicViews;
    private Button finish;
    private Button add;
    private Button snip;
    private Boolean hasImage = false;
    private byte[] image;

    public CustomRecipePart3() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomFoodDialog);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_custom_recipe_part3, container, false);

        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN |     WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        ll = (LinearLayout) view.findViewById(R.id.linearLayout_add_step);
        dynamicViews = new DynamicViews(context);

        finish = (Button) view.findViewById(R.id.btn_donerecipe);
        //add steps
        add = (Button) view.findViewById(R.id.btn_add_step);
        snip = (Button) view.findViewById(R.id.btn_capture);

        finish.setOnClickListener(this);
        add.setOnClickListener(this);
        snip.setOnClickListener(this);


        return view;
    }


    Context context;
    private LinearLayout ll;

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.btn_add_step)
        {
            ll.addView(dynamicViews.ingredientEditText(getContext()));
        }
        else if(v.getId() == R.id.btn_donerecipe){
            //DONE
            //Recipe name
            String name = getArguments().getString("name");
            //Description
            String description = getArguments().getString("description");
            //Filter
            String filter = getArguments().getString("filter");
            //Ingredients
            String[] ingredients = getArguments().getStringArray("ingredients");

            //steps
            String[] steps = getEditText();

            //Sends recipe to recipefragment
            Recipe recipe = createRecipe(name, description, filter, ingredients, steps);

            if(hasImage = true){
                recipe.setImage(image);
            }

            ICustomRecipeInputs activity = (ICustomRecipeInputs) this.getActivity();
            activity.onFinishRecipe(recipe);
            dismiss();
        }
//        Take image
        else if(v.getId() == R.id.btn_capture){
            Intent capture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(capture, 1);
        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap image = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        image.recycle();

        this.image = byteArray;
        hasImage = true;
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        dismiss();
    }

    //Returns a recipe.
    private Recipe createRecipe(String name, String description, String filter, String[] ingredients, String[] steps){
        Recipe recipe = new Recipe(name, new ArrayList<String>(Arrays.asList(ingredients)), filter, description, new ArrayList<String>(Arrays.asList(steps)));
        return recipe;
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
            Log.i("testing", String.valueOf(et.getText()));
            list[i] = String.valueOf(et.getText());
        }
        return list;
    }
}
