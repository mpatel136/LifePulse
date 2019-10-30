package com.saadatdevelopment.lifepulse.searchdialognew.goalsettings;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Toast;

import com.saadatdevelopment.lifepulse.searchdialognew.R;

import java.util.ArrayList;

import ir.mirrajabi.searchdialog.SimpleSearchDialogCompat;
import ir.mirrajabi.searchdialog.core.BaseSearchDialogCompat;
import ir.mirrajabi.searchdialog.core.SearchResultListener;
import ir.mirrajabi.searchdialog.core.Searchable;

public class SearchExercise extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar bar = getSupportActionBar();

        bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
        bar.setTitle(Html.fromHtml("<font color='#7d2709'>Exercises</font>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_search_exercise);

        // starts the intent and sends the key to the exerciselist page.
        final Intent sendIntent = new Intent(this, ExercisePage.class);

        findViewById(R.id.btnSearch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SimpleSearchDialogCompat(SearchExercise.this, "Search...", "Search for exercises...", null,
                        initData(), new SearchResultListener<Searchable>() {
                    @Override
                    public void onSelected(BaseSearchDialogCompat baseSearchDialogCompat, Searchable searchable, int i) {

                        sendIntent.putExtra("Exercise", searchable.getTitle());
                        startActivity(sendIntent);
                        baseSearchDialogCompat.dismiss();
                    }
                }).show();
            }
        });


    }

    private ArrayList<SearchModel> initData() {
        ArrayList<SearchModel> exerciseList = new ArrayList<>();
        // items in the arraylist
        exerciseList.add(new SearchModel("Push Ups"));
        exerciseList.add(new SearchModel("Pull Ups"));
        exerciseList.add(new SearchModel("Yoga"));
        exerciseList.add(new SearchModel("Plank"));
        exerciseList.add(new SearchModel("Weight Lifting"));
        exerciseList.add(new SearchModel("Running"));

        return exerciseList;
    }

    public void selectExercise(View view) {

        // creats an intent to send image id to exercise list activity
        Intent sendIntent = new Intent(this, ExercisePage.class);

        String exercise = "Exercise";

        // Sends image id to exercise list activity based on their id
        if(view.getId() == R.id.pushUps) {
            //Toast.makeText(this, "Pushup is selected", Toast.LENGTH_SHORT).show();
            sendIntent.putExtra(exercise, "Push Ups");
        }
        else if(view.getId() == R.id.running) {
            //Toast.makeText(this, "Running is selected", Toast.LENGTH_SHORT).show();
            sendIntent.putExtra(exercise, "Running");
        }
        else if(view.getId() == R.id.weight_lifting) {
            //Toast.makeText(this, "Running is selected", Toast.LENGTH_SHORT).show();
            sendIntent.putExtra(exercise, "Weight Lifting");
        }else if(view.getId() == R.id.plank) {
            //Toast.makeText(this, "Running is selected", Toast.LENGTH_SHORT).show();
            sendIntent.putExtra(exercise, "Plank");
        }else if(view.getId() == R.id.yoga) {
            //Toast.makeText(this, "Running is selected", Toast.LENGTH_SHORT).show();
            sendIntent.putExtra(exercise, "Yoga");
        }else if(view.getId() == R.id.pullUps) {
            //Toast.makeText(this, "Running is selected", Toast.LENGTH_SHORT).show();
            sendIntent.putExtra(exercise, "Pull Ups");
        }

        startActivity(sendIntent);
    }



}
