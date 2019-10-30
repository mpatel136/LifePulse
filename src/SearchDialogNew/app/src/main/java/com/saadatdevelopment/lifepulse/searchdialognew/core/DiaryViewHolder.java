package com.saadatdevelopment.lifepulse.searchdialognew.core;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lifepulselibrary.Diary;
import com.example.lifepulselibrary.Exercise;
import com.saadatdevelopment.lifepulse.searchdialognew.R;
import com.saadatdevelopment.lifepulse.searchdialognew.databasecontrollers.DiaryDBController;

import java.util.List;

public class DiaryViewHolder extends RecyclerView.ViewHolder{

    private DiaryAdapter diaryAdapter;

    // UI content
    protected TextView txtName, txtGuiltLevel, txtDate, txtDescription;
    protected ImageView imgFoodDiary;

    private List<Diary> diaries;

    private CardView diaryCardView;

    private Diary diary;

    private Context context;

    private int diaryPosition;

    private DiaryDBController dbController;


    public DiaryViewHolder(View itemView, DiaryAdapter diaryAdapter, List<Diary> iDiary, Context context) {
        super(itemView);

        this.diaries = iDiary;
        this.diaryAdapter = diaryAdapter;
        this.context = context;

        this.imgFoodDiary = (ImageView) itemView.findViewById(R.id.imgDiary);
        this.txtName = (TextView) itemView.findViewById(R.id.txtFoodDiaryName);
        this.txtGuiltLevel = (TextView) itemView.findViewById(R.id.txtFoodDiaryGuiltLevel);
        this.txtDate = (TextView) itemView.findViewById(R.id.txtFoodDiaryDate);
        this.txtDescription = (TextView) itemView.findViewById(R.id.txtFoodDiaryDescription);

        diaryCardView = (CardView) itemView.findViewById(R.id.food_log_card_view);

        dbController = new DiaryDBController(this.context);


    }

    public DiaryViewHolder(View view) {
        super(view);
    }


}
