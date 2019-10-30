package com.saadatdevelopment.lifepulse.searchdialognew.core;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.saadatdevelopment.lifepulse.searchdialognew.R;
import com.example.lifepulselibrary.Recipe;

import java.util.List;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListViewHolder> {

    private List<Recipe> list;
    private Context context;

    public RecipeListAdapter(List<Recipe> list, Context context){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecipeListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        this.context = viewGroup.getContext();
        //Takes the layout for entries to activity
        View view = LayoutInflater.from(context).inflate(R.layout.layout_recipe, viewGroup, false);
        RecipeListViewHolder viewHolder = new RecipeListViewHolder(view, this, list);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeListViewHolder recipeListViewHolder, int i) {
        Recipe recipe = list.get(i);
        recipeListViewHolder.setRecipeDisplay(recipe);
    }


    //Item count of the list
    @Override
    public int getItemCount() {
        return list.size();
    }

    public Context getContext() {
        return this.context;
    }
}
