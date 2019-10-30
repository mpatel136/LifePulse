package com.saadatdevelopment.lifepulse.searchdialognew.bmihealth;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;

import com.example.lifepulselibrary.Diary;
import com.example.lifepulselibrary.Exercise;
import com.saadatdevelopment.lifepulse.searchdialognew.R;
import com.saadatdevelopment.lifepulse.searchdialognew.core.DiaryAdapter;
import com.saadatdevelopment.lifepulse.searchdialognew.core.DiaryViewHolder;
import com.saadatdevelopment.lifepulse.searchdialognew.core.ExerciseListAdapter;
import com.saadatdevelopment.lifepulse.searchdialognew.core.ExerciseListViewHolder;
import com.saadatdevelopment.lifepulse.searchdialognew.databasecontrollers.DiaryDBController;
import com.saadatdevelopment.lifepulse.searchdialognew.databasecontrollers.Exercises.ExerciseDBController;
import com.saadatdevelopment.lifepulse.searchdialognew.menu.DefaultMenu;

import java.util.List;

public class FoodDiary extends DefaultMenu implements View.OnClickListener {

    private ImageButton imgBtnCreateDiary;

    // RecyclerView user interface component
    private RecyclerView recyclerView;

    // Adapter for the RecyclerView
    private RecyclerView.Adapter<DiaryViewHolder> adapter;

    // List of diaries
    private List<Diary> diaries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar bar1 = getSupportActionBar();

        bar1.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
        bar1.setTitle(Html.fromHtml("<font color='#7d2709'>Food Dairy</font>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_food_log);

        // find the UI component
        this.recyclerView = (RecyclerView) findViewById(R.id.foodLogRecyclerView);

        // arrange the item linearly
        //this.layoutManager = new LinearLayoutManager(this);

        // Gets the exercises
        diaries = getDiaries();

        // create exercise list adapter
        this.adapter = new DiaryAdapter(diaries, this);

        // sets the layout mananger
        //this.recyclerView.setLayoutManager(layoutManager);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        this.recyclerView.setLayoutManager(layoutManager);

        // sets the adapter
        this.recyclerView.setAdapter(adapter);


        imgBtnCreateDiary = (ImageButton) findViewById(R.id.imgBtnAddDiary);
        imgBtnCreateDiary.setOnClickListener(this);


    }

    private List<Diary> getDiaries() {

        // Create a List of birds
        List<Diary> diaries;

        //Create a bird database controller to read from the database
        DiaryDBController diaryDBController = new DiaryDBController(this);

        diaries = diaryDBController.getAllDiaries();

        return diaries;

    }


    @Override
    public void onClick(View v) {

        Intent foodDiaryIntent = new Intent(this, CreateFoodDiary.class);
        startActivity(foodDiaryIntent);

    }
}
