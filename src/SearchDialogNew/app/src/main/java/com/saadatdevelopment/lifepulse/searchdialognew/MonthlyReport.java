package com.saadatdevelopment.lifepulse.searchdialognew;

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

public class MonthlyReport extends AppCompatActivity {

    private Spinner spinnerMonthly;
    private List<String> monthList;

    /**
     * https://www.youtube.com/watch?v=on_OrrX7Nw4  Tutorial on how to make a spinner adapter view
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar bar1 = getSupportActionBar();

        bar1.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
        bar1.setTitle(Html.fromHtml("<font color='#7d2709'>Monthly Report</font>"));

        setContentView(R.layout.activity_monthly_report);

        spinnerMonth();
    }

    // Spinner
    private void spinnerMonth() {
        // sets the spinner
        spinnerMonthly = findViewById(R.id.spinnerMonth);

        // adds the year to the list
        monthList = new ArrayList<>();
        monthList.add("Jan");
        monthList.add("Feb");
        monthList.add("Mar");
        monthList.add("Apr");
        monthList.add("May");
        monthList.add("Jun");
        monthList.add("Jul");
        monthList.add("Aug");
        monthList.add("Sep");
        monthList.add("Oct");
        monthList.add("Nov");
        monthList.add("Dec");


        // adds the list's item to the spinner
        ArrayAdapter<String> spinnerDurationAdapterMonthly = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, monthList);
        spinnerDurationAdapterMonthly.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMonthly.setAdapter(spinnerDurationAdapterMonthly);

        spinnerMonthly.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
