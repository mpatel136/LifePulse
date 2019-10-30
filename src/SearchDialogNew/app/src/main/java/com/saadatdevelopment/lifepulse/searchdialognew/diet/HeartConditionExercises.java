package com.saadatdevelopment.lifepulse.searchdialognew.diet;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lifepulselibrary.HeartConditionExercise;
import com.saadatdevelopment.lifepulse.searchdialognew.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class HeartConditionExercises extends AppCompatActivity {

    Resources res;


    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar bar1 = getSupportActionBar();

        bar1.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
        bar1.setTitle(Html.fromHtml("<font color='#B72F82'>Heart Exercise</font>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_heart_condition_exercises);

        res = getResources();

        List<HeartConditionExercise> exerciseList = new ArrayList<>();
        exerciseList.add(new HeartConditionExercise("Walking","40 - 60 minutes","Medium", getIcon(R.drawable.treadmill_icon)));
        exerciseList.add(new HeartConditionExercise("Jogging","40 - 60 minutes","Medium/Hard",getIcon(R.drawable.treadmill_icon)));
        exerciseList.add(new HeartConditionExercise("Elliptical machine","40 - 60 minutes","Medium/Hard", getIcon(R.drawable.elliptical_icon)));
        exerciseList.add(new HeartConditionExercise("Bicycle","60 minutes","Medium/Hard", getIcon(R.drawable.bike_icon)));
        exerciseList.add(new HeartConditionExercise("Yoga","40 - 60 minutes","Medium/Hard", getIcon(R.drawable.stretching_icon)));

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.heart_condition_recyclerview);
        recyclerView.setAdapter(new HeartConditionAdapter(exerciseList,this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    //Converts drawable into a BLOB
    public byte[] getIcon(int drawableInt) {
        Drawable drawable = res.getDrawable(drawableInt);
        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] bitMapData = stream.toByteArray();
        return bitMapData;
    }
}
