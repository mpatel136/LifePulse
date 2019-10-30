package com.saadatdevelopment.lifepulse.searchdialognew.goalsettings;

import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.lifepulselibrary.Exercise;
import com.saadatdevelopment.lifepulse.searchdialognew.core.ExerciseListAdapter;
import com.saadatdevelopment.lifepulse.searchdialognew.core.ExerciseListViewHolder;
import com.saadatdevelopment.lifepulse.searchdialognew.core.IExerciseChangeListener;
import com.saadatdevelopment.lifepulse.searchdialognew.databasecontrollers.Exercises.ExerciseDBController;
import com.saadatdevelopment.lifepulse.searchdialognew.R;

import java.util.List;

public class ListOfExercises extends AppCompatActivity implements View.OnClickListener, IExerciseChangeListener {

    // RecyclerView user interface component
    private RecyclerView recyclerView;

    // Adapter for the RecyclerView
    private RecyclerView.Adapter<ExerciseListViewHolder> adapter;

    // LayoutManager for the RecyclerView
    //private RecyclerView.LayoutManager layoutManager;

    // swipe layout
    private SwipeRefreshLayout swipeRefreshLayout;

    private List<Exercise> exercises;

    // snackbar
    private Snackbar snackbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar bar1 = getSupportActionBar();

        bar1.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
        bar1.setTitle(Html.fromHtml("<font color='#7d2709'>Exercise Lists</font>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_list_of_exercises);

        // find the UI component
        this.recyclerView = (RecyclerView) findViewById(R.id.exerciseListRecycleView);

        // arrange the item linearly
        //this.layoutManager = new LinearLayoutManager(this);

        // Gets the exercises
        exercises = getExercises();

        // create exercise list adapter
        this.adapter = new ExerciseListAdapter(exercises, this);

        // sets the layout mananger
        //this.recyclerView.setLayoutManager(layoutManager);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        this.recyclerView.setLayoutManager(layoutManager);

        // sets the adapter
        this.recyclerView.setAdapter(adapter);


        // for context menu
        registerForContextMenu(recyclerView);

        // swipe layout
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshExercises);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent, R.color.colorPrimaryDark, R.color.colorPrimaryDailyChart);


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                if(swipeRefreshLayout.isRefreshing()) {
                    Toast.makeText(ListOfExercises.this, "Refreshing", Toast.LENGTH_SHORT).show();
                    Log.i("SwipeLayout", "Refreshing");

                    adapter.notifyDataSetChanged();
                }

                swipeRefreshLayout.setRefreshing(false);

                if(!swipeRefreshLayout.isRefreshing()) {
                    Toast.makeText(ListOfExercises.this, "Refresh Done", Toast.LENGTH_SHORT).show();
                    Log.i("SwipeLayout", "Done Refreshing");
                }


            }
        });


    }

    private List<Exercise> getExercises() {

        // Create a List of birds
        List<Exercise> exercises;

        //Create a bird database controller to read from the database
        ExerciseDBController exerciseDBController = new ExerciseDBController(this);

        exercises = exerciseDBController.getAllExercises();

        return exercises;

    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void onExerciseUpdateListener(String message) {
        snackbar = Snackbar.make(findViewById(R.id.exerciseListCoordinatorLayout), message, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    @Override
    public void onExerciseDeleteListener(String message) {
        //Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        snackbar = Snackbar.make(findViewById(R.id.exerciseListCoordinatorLayout), message, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }
}
