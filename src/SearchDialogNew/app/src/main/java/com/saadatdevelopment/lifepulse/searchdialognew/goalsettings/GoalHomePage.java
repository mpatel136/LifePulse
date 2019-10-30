package com.saadatdevelopment.lifepulse.searchdialognew.goalsettings;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;

import com.saadatdevelopment.lifepulse.searchdialognew.R;

public class GoalHomePage extends AppCompatActivity implements View.OnClickListener {
    private ImageButton btnAddExericse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar bar1 = getSupportActionBar();

        bar1.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
        bar1.setTitle(Html.fromHtml("<font color='#7d2709'>Goal Setting</font>"));

        setContentView(R.layout.activity_goal_home_page);

        btnAddExericse = (ImageButton) findViewById(R.id.btnAddExercise);
        btnAddExericse.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent addIntent = null;
        if(v.getId() == R.id.btnAddExercise) {
            addIntent = new Intent(this, SearchExercise.class);
        }

        startActivity(addIntent);
    }
}
