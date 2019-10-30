package com.saadatdevelopment.lifepulse.searchdialognew.core;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lifepulselibrary.FoodModel;
import com.saadatdevelopment.lifepulse.searchdialognew.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ApiFoodAdapter extends RecyclerView.Adapter<ApiFoodViewHolder>
{
    private List<FoodModel> foodModels;
    private Context context;

    public ApiFoodAdapter(List<FoodModel> foodModels, Context context) {
        this.foodModels = foodModels;
        this.context = context;
    }

    @Override
    public ApiFoodViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_food_structure, viewGroup, false);

        return new ApiFoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ApiFoodViewHolder apiFoodViewHolder, int i) {
        final FoodModel foodModel = foodModels.get(i);

        apiFoodViewHolder.foodName.setText(foodModel.getName());
        apiFoodViewHolder.protein.setText("Protein: " + foodModel.getProtein());
        apiFoodViewHolder.fat.setText("Fat: " + foodModel.getFat());
        apiFoodViewHolder.sugar.setText("Sugar: " + foodModel.getSugar());
        apiFoodViewHolder.cholesterol.setText("Cholesterol: " + foodModel.getCholesterol());

        Picasso.with(context)
                .load(foodModel.getImageUrl())
                .into(apiFoodViewHolder.image);

        apiFoodViewHolder.linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(context, "Pressed on " + foodModel.getName(), Toast.LENGTH_LONG).show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodModels.size();
    }

    public List<FoodModel> getList(){
        return foodModels;
    }
}
