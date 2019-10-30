package com.saadatdevelopment.lifepulse.searchdialognew.diet;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;

import com.saadatdevelopment.lifepulse.searchdialognew.R;

public class DailyChart extends AppCompatActivity {

    TextView totalHeartRateMinutes;
    TextView averageHrtRate;
    TextView highestHrtRate;

    TextView totalHeartRateMinutesFitness;
    TextView caloriesBurned;
    TextView highestHrtRateFitness;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar bar1 = getSupportActionBar();

        bar1.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
        bar1.setTitle(Html.fromHtml("<font color='#7d2709'>Daily Dashboard</font>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_chart_daily);

        totalHeartRateMinutes = (TextView) findViewById(R.id.txtTotalMinutesHeart);
        averageHrtRate = (TextView) findViewById(R.id.txtAvgHeartRateDisplay);
        highestHrtRate = (TextView) findViewById(R.id.txtHighestHeartRate);
        totalHeartRateMinutesFitness = (TextView) findViewById(R.id.txtTotalMinutesFitness);
        caloriesBurned = (TextView) findViewById(R.id.txtCaloriesBurned);
        highestHrtRateFitness = (TextView) findViewById(R.id.txtAverageHeartRateFitness);
    }
}
