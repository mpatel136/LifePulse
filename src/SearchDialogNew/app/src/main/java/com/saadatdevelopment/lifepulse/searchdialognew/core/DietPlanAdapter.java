package com.saadatdevelopment.lifepulse.searchdialognew.core;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lifepulselibrary.DietModel;
import com.saadatdevelopment.lifepulse.searchdialognew.R;

import java.util.List;


public class DietPlanAdapter extends RecyclerView.Adapter<DietPlanViewHolder> {

    private List<DietModel> list;
    private Context context;

    public DietPlanAdapter(List<DietModel> list, Context ctx){
        this.list = list;
        context = ctx;
    }
    @NonNull
    @Override
    public DietPlanViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.diet_plan_entry,viewGroup,false);
        return new DietPlanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DietPlanViewHolder dietPlanViewHolder, int i) {
        dietPlanViewHolder.setDisplay(list.get(i), i, context);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public Context getContext(){return context;}
}
