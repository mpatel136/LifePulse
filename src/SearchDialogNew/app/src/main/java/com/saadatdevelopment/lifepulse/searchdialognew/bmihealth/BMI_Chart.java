package com.saadatdevelopment.lifepulse.searchdialognew.bmihealth;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.saadatdevelopment.lifepulse.searchdialognew.R;

import java.util.ArrayList;
//To implement in a fragment.
//https://www.youtube.com/watch?v=imC5hg1P7wQ
/**
 * A simple {@link Fragment} subclass.
 */
public class BMI_Chart extends DialogFragment {

    BarChart barChart;

    public BMI_Chart() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bmi__chart, container, false);
                BarChart barChart = (BarChart) view.findViewById(R.id.bar_graph_bmi);

        ArrayList<BarEntry> test = new ArrayList<>();
        test.add(new BarEntry(20f,0));
        test.add(new BarEntry(22f,1));
        test.add(new BarEntry(24f,2));
        test.add(new BarEntry(25f,3));
        test.add(new BarEntry(22f,4));

        BarDataSet dataSet = new BarDataSet(test,"Cells");

        ArrayList<String> ss = new ArrayList<>();
        ss.add("Week 1");
        ss.add("Week 2");
        ss.add("Week 3");
        ss.add("Week 4");
        ss.add("Week 5");

        //BarData data = new BarData(ss, dataSet);

        //barChart.setData(data);

        //barChart.setDescription("Test for BMI_CHART");

        barChart.animateY(5000);
        return view;

    }

}
