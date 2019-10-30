package com.saadatdevelopment.lifepulse.searchdialognew.core;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.saadatdevelopment.lifepulse.searchdialognew.R;

import com.example.lifepulselibrary.Recipe;

import java.util.List;

public class RecipeListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    private List<Recipe> list;
    RecipeListAdapter adapter;
    private TextView title;
    private TextView description;
    private ImageView image;
    private View view;
    private CardView entry;
    private ImageView delete;

    public RecipeListViewHolder(View itemView, RecipeListAdapter recipeListAdapter, List<Recipe> list) {
        super(itemView);
        view = itemView;
        this.list = list;
        adapter = recipeListAdapter;
        title = (TextView) view.findViewById(R.id.txt_frontFoodName);
        description = (TextView) view.findViewById(R.id.txt_to_foodDescription);
        image = (ImageView) view.findViewById(R.id.img_recipe);

        entry = (CardView) view.findViewById(R.id.card_view_custom_recipe_entry);
        //delete = (ImageView) view.findViewById(R.id.img_delete);

        entry.setOnClickListener(this);
        //delete.setOnClickListener(this);

        description.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Log.i("onClick","V:" + getAdapterPosition());
        Log.i("onClick", "v:" + v.getId() + ":==:" + this.getItemId());
        IMakeRecipeFragment activity = (IMakeRecipeFragment) view.getContext();
        if(v.getId() == this.description.getId()){
            //Display description
            Log.i("onClick","txt_foodDescription");
            activity.seeDescription(this.list.get(getAdapterPosition()));
        }
        if(v.getId() == entry.getId()){
            Log.i("onClick","R.id.card_view_custom_recipe_entry");
            //Display the recipe

            activity.seeRecipe(this.list.get(getAdapterPosition()));
            Toast.makeText(adapter.getContext(), "im here", Toast.LENGTH_LONG).show();
        }
//        if(v.getId() == this.delete.getId()){
//            ICustomRecipeInputs activity2 = (ICustomRecipeInputs) adapter.getContext();
//            activity2.delete(list.get(getAdapterPosition()));
//        }
    }

    public void setRecipeDisplay(Recipe recipe){
        //
        description.setText(recipe.getDescription());
        title.setText(recipe.getName());
        //
        //
        try {
            image.setImageBitmap(toBitmap(recipe.getImage()));
        }
        catch (Exception e){
            e.printStackTrace();
            Log.wtf("setRecipeDisplay","Image does not exist");
        }


    }

    private static Bitmap toBitmap(byte[] image)
    {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }
}
