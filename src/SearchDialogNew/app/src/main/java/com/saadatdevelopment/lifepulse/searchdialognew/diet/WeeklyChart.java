package com.saadatdevelopment.lifepulse.searchdialognew.diet;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.saadatdevelopment.lifepulse.searchdialognew.R;

import java.util.ArrayList;
import java.util.List;

public class WeeklyChart extends AppCompatActivity {

    private Spinner filterWeeksSpinner;
    private Spinner filterMonthSpinner;
    private List<String> filteredNames;

    /**
     * https://www.youtube.com/watch?v=on_OrrX7Nw4  Tutorial on how to make a spinner adapter view
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar bar1 = getSupportActionBar();

        bar1.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
        bar1.setTitle(Html.fromHtml("<font color='#7d2709'>Weekly Report</font>"));
        setContentView(R.layout.activity_weekly_chart);

        filterWeeksSpinner = findViewById(R.id.spinWeeklyFilter);
        filterMonthSpinner = findViewById(R.id.spinMonthFilter);

        // adds the item to the list
        filteredNames = new ArrayList<>();
        filteredNames.add("Week 1");
        filteredNames.add("Week 2");
        filteredNames.add("Week 3");
        filteredNames.add("Week 4");

        // adds the list's item to the spinner
        ArrayAdapter<String> spinnerDurationAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, filteredNames);
        spinnerDurationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filterWeeksSpinner.setAdapter(spinnerDurationAdapter);

        filterWeeksSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String week = parent.getItemAtPosition(position).toString();
                //Get data for specified week index of month from databases

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                String week = parent.getItemAtPosition(0).toString();
                //Get data for specified week index of month from databases
            }
        });

        // adds the item to the list
        filteredNames = new ArrayList<>();
        filteredNames.add("January");
        filteredNames.add("February");
        filteredNames.add("March");
        filteredNames.add("April");
        filteredNames.add("May");
        filteredNames.add("June");
        filteredNames.add("July");
        filteredNames.add("August");
        filteredNames.add("September");
        filteredNames.add("October");
        filteredNames.add("November");
        filteredNames.add("December");

        // adds the list's item to the spinner
        ArrayAdapter<String> spinnerDurationAdapterMonthly = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, filteredNames);
        spinnerDurationAdapterMonthly.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filterMonthSpinner.setAdapter(spinnerDurationAdapterMonthly);

        filterMonthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String month = parent.getItemAtPosition(position).toString();

                //After this get databases data for specified month
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                    String month = parent.getItemAtPosition(0).toString();
                //After this get databases data for January
            }
        });

    }
}
