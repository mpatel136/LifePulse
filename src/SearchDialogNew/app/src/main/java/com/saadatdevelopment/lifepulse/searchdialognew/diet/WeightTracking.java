package com.saadatdevelopment.lifepulse.searchdialognew.diet;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;

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
import java.util.Random;

public class WeightTracking extends AppCompatActivity {

    BarChart barGraph;
    TextView tips;

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
        bar1.setTitle(Html.fromHtml("<font color='#66B033'>Weight Tracking</font>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_weight_tracking);

        barGraph = (BarChart) findViewById(R.id.yearBarGraph);
        tips = (TextView) findViewById(R.id.daily_tips);


        //Testing purposes
        String dayOfMonth = String.valueOf(Calendar.DAY_OF_MONTH); //gets day of month
        int monthIndex = Calendar.MONTH;    //gets month number

        //Simple list of all months in a year
        //Used for testing
        ArrayList<String> monthsList = new ArrayList<>();
        monthsList.add("January");
        monthsList.add("February");
        monthsList.add("March");
        monthsList.add("April");
        monthsList.add("May");
        monthsList.add("June");
        monthsList.add("July");
        monthsList.add("August");
        monthsList.add("September");
        monthsList.add("October");
        monthsList.add("November");
        monthsList.add("December");


        //Creates a list of the y axis values
        ArrayList<BarEntry> yAxisEntries = new ArrayList<>();
        yAxisEntries.add(new BarEntry(0f, 220f));
        yAxisEntries.add(new BarEntry(1f, 200f));
        yAxisEntries.add(new BarEntry(2f, 250f));
        yAxisEntries.add(new BarEntry(3f, 260f));
        yAxisEntries.add(new BarEntry(4f, 280f));

        //Converts the list into BarDataSet so they can be used in the graph
        BarDataSet barDataSet = new BarDataSet(yAxisEntries, "Weight (lbs)");
        barDataSet.setColor(Color.BLUE);
        barDataSet.setValueTextColor(R.color.black);

        //List of x axis values
        final ArrayList<String> xAxisEntries = new ArrayList<>();
        xAxisEntries.add("Week 1");
        xAxisEntries.add("Week 2");
        xAxisEntries.add("Week 3");
        xAxisEntries.add("Week 4");
        xAxisEntries.add("Week 5");

        //Format the X axis to use Strings instead of int
        XAxis xAxis = barGraph.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);
        xAxis.setTextSize(10f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                if (value < xAxisEntries.size()) {
                    return xAxisEntries.get((int) value);
                } else return "";
            }
        });

        //Adds a red line to show a warning for obesity on the y axis
        //depending on user's height?
        YAxis yAxis = barGraph.getAxisLeft();
        LimitLine weightLimit = new LimitLine(270f);
        weightLimit.setLineColor(Color.RED);
        yAxis.addLimitLine(weightLimit);

        //Testing purposes
        xAxisEntries.add(monthsList.get(monthIndex) + " " + dayOfMonth);

        //Finish bar graph
        BarData weightData = new BarData(barDataSet);
        weightData.setBarWidth(0.9f);
        barGraph.setData(weightData);
        barGraph.setFitBars(true);
        barGraph.getDescription().setEnabled(false);
        barGraph.invalidate();

        displayDailyTips();

    }

    /**
     * Displays daily tips on how to lose weight
     * @link https://www.healthline.com/nutrition/11-ways-to-lose-weight-without-diet-or-exercise#section9  Used to provide daily tips to lose weight
     */
    public void displayDailyTips () {

        Random random = new Random();
        ArrayList<String> dailyTips = new ArrayList<>();
        dailyTips.add("Chew Thoroughly and Slow Down: Chewing your food thoroughly makes you eat more slowly, which is associated with decreased food intake, increased fullness and smaller portion sizes.");
        dailyTips.add("Use Smaller Plates for Unhealthy Foods");
        dailyTips.add("Eat Plenty of Protein: Protein has powerful effects on appetite. It can increase feelings of fullness, reduce hunger and help you eat fewer calories.");
        dailyTips.add("Store Unhealthy Foods out of Sight: Storing unhealthy foods where you can see them may increase hunger and cravings, causing you to eat more.");
        dailyTips.add("Eat Fiber-Rich Foods: Eating fiber-rich foods may increase satiety, helping you feel fuller for longer.");
        dailyTips.add("Drink Water Regularly: Drinking water can help you eat less and lose weight, especially if you drink it before a meal.");
        dailyTips.add("Serve Yourself Smaller Portions: Serving yourself just a little less might help you eat significantly fewer calories. And you probably won't even notice the difference.");
        dailyTips.add("Eat Without Electronic Distractions: If you regularly consume meals while watching TV or using electronic devices, you could be inadvertently eating more.");
        dailyTips.add("Sleep Well and Avoid Stress: Poor sleep and excess stress may imbalance several important appetite-regulating hormones, causing you to eat more.");
        dailyTips.add("Eliminate Sugary Drinks: Staying away from these beverages entirely can provide enormous long-term health benefits.");
        dailyTips.add("Healthy beverages to drink: Water, coffee and green tea.");

        int index = random.nextInt(11);
        tips.setText(dailyTips.get(index));
    }
}
