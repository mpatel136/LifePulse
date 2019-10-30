package com.saadatdevelopment.lifepulse.searchdialognew.core;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.saadatdevelopment.lifepulse.searchdialognew.R;

public class ApiFoodViewHolder extends RecyclerView.ViewHolder {

    public TextView foodName;
    public TextView protein;
    public TextView sugar;
    public TextView fat;
    public TextView cholesterol;
    public ImageView image;
    public LinearLayout linearLayout;

    public ApiFoodViewHolder(View itemView) {
        super(itemView);

        foodName = (TextView)  itemView.findViewById(R.id.txtFoodName);
        protein = (TextView)  itemView.findViewById(R.id.txtProtein);
        sugar = (TextView)  itemView.findViewById(R.id.txtSugar);
        fat = (TextView)  itemView.findViewById(R.id.txtFat);
        cholesterol = (TextView)  itemView.findViewById(R.id.txtCholesterol);
        image = (ImageView) itemView.findViewById(R.id.imgFoodRecyclerItem);
        linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayoutFavFood);
    }
}
