package com.saadatdevelopment.lifepulse.searchdialognew.fragments;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.saadatdevelopment.lifepulse.searchdialognew.R;
import com.example.lifepulselibrary.Recipe;

/**
 * A dialog fragment that displays a recipe.
 * A simple {@link Fragment} subclass.
 */
public class RecipeFragment extends DialogFragment {

    private Recipe recipe;
    private TextView title;
    private TextView ingredients;
    private TextView steps;
    private TextView description;
    private ImageView image;

    public RecipeFragment() {
        // Required empty public constructor
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomFoodDialog);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe, container, false);
        // Inflate the layout for this fragment
        recipe = getArguments().getParcelable("recipe");

        title = (TextView) view.findViewById(R.id.txt_recipe_title);
        ingredients = (TextView) view.findViewById(R.id.txt_list_ingredients);
        steps = (TextView) view.findViewById(R.id.txt_list_steps);
        image = (ImageView) view.findViewById(R.id.img_your_recipe);
        description = (TextView) view.findViewById(R.id.txt_description);


        try {
            image.setImageBitmap(toBitmap(recipe.getImage()));
        }
        catch (Exception e){
            e.printStackTrace();
            Log.wtf("RecipeListViewHolder","image does not exist.");
        }
        title.setText(recipe.getName());
        description.setText(description.getText() + "\n" + recipe.getDescription());

        setIngredients();
        setSteps();




        return view;
    }

    private static Bitmap toBitmap(byte[] image)
    {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }
    private void setIngredients(){
        String ingredients = "";
        for(int i = 0; i < recipe.getIngredients().size(); i++){
            ingredients += i+1 +"-" + recipe.getIngredients().get(i) +"\n";
        }
        this.ingredients.setText(ingredients);
    }

    private void setSteps(){
        String step = "";
        for(int i = 0; i < recipe.getSteps().size(); i++){
            step += i+1 +"-" + recipe.getSteps().get(i) +"\n";
        }

        steps.setText(step);
    }





}
