package com.saadatdevelopment.lifepulse.searchdialognew;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.saadatdevelopment.lifepulse.searchdialognew.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AnnualReport extends AppCompatActivity {
    private Spinner yearSpinner;
    private List<Integer> yearList;
    private BarChart yearBarGraph;

    private ProgressBar heartProgressBar;

    /**
     * @link https://www.youtube.com/watch?v=pi1tq-bp7uA Tutorial used to create bar graph
     * @link https://github.com/PhilJay/MPAndroidChart/wiki Link of the bar chart Git library and documentation
     * @link https://stackoverflow.com/questions/27566916/how-to-remove-description-from-chart-in-mpandroidchart    Shows how to remove description label from bar graph
     * @link https://stackoverflow.com/questions/47637653/how-to-set-x-axis-labels-in-mp-android-chart-bar-graph    Shows how to format the x axis and use string values instead of int
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar bar1 = getSupportActionBar();
        bar1.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
        bar1.setTitle(Html.fromHtml("<font color='#7d2709'>Annual Report</font>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_annual_report);

        spinnerYear();

        yearBarGraph = (BarChart) findViewById(R.id.yearBarGraph);


        //Testing purposes
        String dayOfMonth = String.valueOf(Calendar.DAY_OF_MONTH); //gets day of month
        int monthIndex = Calendar.MONTH;    //gets month number


        //Creates a list of the y axis values
        ArrayList<BarEntry> yAxisEntries = new ArrayList<>();
        yAxisEntries.add(new BarEntry(0f, 100f));
        yAxisEntries.add(new BarEntry(1f, 40f));
        yAxisEntries.add(new BarEntry(2f, 60f));
        yAxisEntries.add(new BarEntry(3f, 55f));
        yAxisEntries.add(new BarEntry(4f, 50f));
        yAxisEntries.add(new BarEntry(5f, 60f));
        yAxisEntries.add(new BarEntry(6f, 120f));
        yAxisEntries.add(new BarEntry(7f, 80f));
        yAxisEntries.add(new BarEntry(8f, 40f));
        yAxisEntries.add(new BarEntry(9f, 100f));
        yAxisEntries.add(new BarEntry(10f, 110f));
        yAxisEntries.add(new BarEntry(11f, 140f));

        //Converts the list into BarDataSet so they can be used in the graph
        BarDataSet barDataSet = new BarDataSet(yAxisEntries, "Heart Rate (BPM)");

        barDataSet.setColor(Color.rgb(255,0,0));


        barDataSet.setValueTextColor(Color.BLACK);

        //List of x axis values
        final ArrayList<String> months = new ArrayList<>();
        months.add("Jan");
        months.add("Feb");
        months.add("Mar");
        months.add("Apr");
        months.add("May");
        months.add("Jun");
        months.add("Jul");
        months.add("Aug");
        months.add("Sep");
        months.add("Oct");
        months.add("Nov");
        months.add("Dec");

        //Format the X axis to use Strings instead of int
        XAxis xAxis = yearBarGraph.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);
        xAxis.setTextSize(10f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                if (value < months.size()) {
                    return months.get((int) value);
                } else return "";
            }
        });

        //Adds a red line to show a warning for obesity on the y axis
        //depending on user's height?
        YAxis yAxis = yearBarGraph.getAxisLeft();
        LimitLine weightLimit = new LimitLine(125f);
        weightLimit.setLineColor(Color.RED);
        yAxis.addLimitLine(weightLimit);

        //Testing purposes
        //months.add(monthsList.get(monthIndex) + " " + dayOfMonth);

        //Finish bar graph
        BarData weightData = new BarData(barDataSet);
        weightData.setBarWidth(0.9f);
        yearBarGraph.setData(weightData);
        yearBarGraph.setFitBars(false);
        yearBarGraph.getDescription().setEnabled(false);
        yearBarGraph.invalidate();

    }

    // Spinner
    private void spinnerYear() {
        // sets the spinner
        yearSpinner = findViewById(R.id.spinnerYear);

        // adds the year to the list
        yearList = new ArrayList<>();
        yearList.add(2018);
        yearList.add(2017);
        yearList.add(2016);
        yearList.add(2015);
        yearList.add(2014);


        // adds the list's item to the spinner
        ArrayAdapter<Integer> spinnerDurationAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, yearList);
        spinnerDurationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(spinnerDurationAdapter);

        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}
