package com.saadatdevelopment.lifepulse.searchdialognew.diet.DietPlanRevamp;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.lifepulselibrary.DietModel;
import com.saadatdevelopment.lifepulse.searchdialognew.R;
import com.saadatdevelopment.lifepulse.searchdialognew.core.DietPlanAdapter;
import com.saadatdevelopment.lifepulse.searchdialognew.databasecontrollers.othercontroller.DietController;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DietPlanRevamp extends AppCompatActivity implements View.OnClickListener, TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener, DialogInterface.OnClickListener, IDateTimePickerResult, IControllerInteraction {

    DietController controller;
    DietPlanAdapter adapter;
    RecyclerView listOfNoms;
    FloatingActionButton addDiet;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_plan_revamp);

        addDiet = (FloatingActionButton) findViewById(R.id.btn_addDiet);



        controller = new DietController(this);

        loadRecyclerView();


        addDiet.setOnClickListener(this);

        ActionBar bar = getSupportActionBar();

        bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
        bar.setTitle(Html.fromHtml("<font color='#7d2709'>Diet Plan</font>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void loadRecyclerView(){
        listOfNoms = (RecyclerView) findViewById(R.id.list_notifications);

        layoutManager = new LinearLayoutManager(this);

        adapter = new DietPlanAdapter(controller.getDietList(),this);

        listOfNoms.setLayoutManager(layoutManager);
        listOfNoms.setAdapter(adapter);

    }

    Calendar cal = Calendar.getInstance();
    boolean specifiedDay = false;

    int day;
    int hours;
    int minute;
    String description = "";


    @Override
    public void onClick(View v) {
        //Legit copying from my usb ez pz, except u cant test from a usb
        int hour = cal.get(Calendar.HOUR);
        int min = cal.get(Calendar.MINUTE);

        specifiedDay = false;

        TimePickerDialog timePickerDialog;
        timePickerDialog = new TimePickerDialog(DietPlanRevamp.this, this,hour,min,true);
        timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        timePickerDialog.show();

    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Log.d("MainActivity","onDateSet: date: " + year + "/" + month + "/" + dayOfMonth );
        specifiedDay = true;

        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
        //Date date = new Date(year,month,dayOfMonth-1);
        //String dayOfWeek = simpleDateFormat.format(date);

        day = dayOfMonth;

        makeDietPlan();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        AlertDialog alertDialog = new AlertDialog.Builder(DietPlanRevamp.this).create();
        alertDialog.setTitle("Set A Weekly Basis?");
        alertDialog.setMessage("Set A Day Of The Week?");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Set A Date", this);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Nah", this);
        alertDialog.show();

        hours = hourOfDay;
        this.minute = minute;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (which == AlertDialog.BUTTON_NEUTRAL) {
//            int year = cal.get(Calendar.YEAR);
//            int month = cal.get(Calendar.MONTH);
//            int day = cal.get(Calendar.DAY_OF_MONTH);
//            DatePickerDialog datePickerDialog = new DatePickerDialog(DietPlanRevamp.this, android.R.style.Theme_Holo_Dialog, this, year, month, day);
//            datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//            //datePickerDialog.getButton(DatePickerDialog.BU)
//            datePickerDialog.getDatePicker().findViewById(getResources().getIdentifier("year","id","android")).setVisibility(View.GONE);
//            datePickerDialog.getDatePicker().findViewById(getResources().getIdentifier("month","id","android")).setVisibility(View.GONE);
//            datePickerDialog.getDatePicker().findViewById(getResources().getIdentifier("month","id","android")).setContentDescription("hello");
//
//
//            datePickerDialog.show();
            FragmentManager fm = getSupportFragmentManager();
            DayOfTheWeekPicker dayOfTheWeekPicker = new DayOfTheWeekPicker();
            dayOfTheWeekPicker.show(fm,"input");
        }
        if(which == AlertDialog.BUTTON_POSITIVE){
            makeDietPlan();
        }
    }



    private void makeDietPlan(){
        Log.i("makeDietPlan","I'm Here");
        final String[] text = {""};
        AlertDialog.Builder  builder = new AlertDialog.Builder(this);
        builder.setTitle("Description");

        final EditText input = new EditText(this);

        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                text[0] = String.valueOf(input.getText());
                description = text[0];
                makeDiet();

            }
        });


        builder.show();


    }

    private void makeDiet(){
        if(!specifiedDay){
            DietModel dietModel = new DietModel(hours, minute, description, true);
            controller.addDiet(dietModel);
            loadRecyclerView();
        }
        else {
            DietModel dietModel = new DietModel(hours, minute, day, description, true);
            controller.addDiet(dietModel);
            loadRecyclerView();
        }
    }

    @Override
    public void onGetDay(int day) {
        this.day = day + 1;
        specifiedDay = true;
        makeDietPlan();
    }

    @Override
    public void reloadRecycler() {
        loadRecyclerView();
    }
}
