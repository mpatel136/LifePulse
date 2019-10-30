package com.saadatdevelopment.lifepulse.searchdialognew.goalsettings;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lifepulselibrary.Exercise;
import com.saadatdevelopment.lifepulse.searchdialognew.databasecontrollers.Exercises.ExerciseDBController;
import com.saadatdevelopment.lifepulse.searchdialognew.R;

import java.util.ArrayList;
import java.util.List;

public class ExercisePage extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener{

    private Spinner durationSpinner;

    private List<String> durationsName;

    private ArrayAdapter<String> durationArrayAdapter;

    private TextView title, txtDuration, txtReps, txtSets;
    private EditText etDuration, etReps, etSets;
    private Button btnSaveExercise;

    // variables for database entry
    private String strDurationName = "", exerciseName;

    private ExerciseDBController dbController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar bar1 = getSupportActionBar();

        bar1.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
        bar1.setTitle(Html.fromHtml("<font color='#7d2709'>Add Exercise</font>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_exercise_page);

        // instantiate the views
        etDuration = (EditText) findViewById(R.id.durationEditTxt);
        etReps = (EditText) findViewById(R.id.etReps);
        etSets = (EditText) findViewById(R.id.etSets);

        // gets the exercise title id
        title = (TextView) findViewById(R.id.exerciseTitle);
        txtDuration = (TextView) findViewById(R.id.txtDuration);
        txtReps = (TextView) findViewById(R.id.txtRepsFragment);
        txtSets = (TextView) findViewById(R.id.txtSetsFragment);


        // gets the intent from the other page.
        Intent getIntent = getIntent();
        exerciseName = getIntent.getStringExtra("Exercise");
        setExerice(exerciseName);

        // instantiate spinner
        durationSpinner = (Spinner) findViewById(R.id.durationSpinner);

        // sets the adapter to the spinner
        durationArray();
        durationArrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, durationsName);
        durationSpinner.setAdapter(durationArrayAdapter);

        // add click listener
        durationSpinner.setOnItemSelectedListener(this);

        // Instantiate the save exercise button and call the next activity
        btnSaveExercise = (Button) findViewById(R.id.btnSaveExerices);
        btnSaveExercise.setOnClickListener(this);

        // connects the db
        dbController = new ExerciseDBController(this);

    }



    private void setExerice(String name) {
        // gets the exercise heart_pulse_icon id
        ImageView icon = findViewById(R.id.exerciseIcon);


        title.setText(name);

        switch (name) {
            case "Push Ups":
                icon.setImageResource(R.drawable.push_ups);
                break;
            case "Pull Ups":
                icon.setImageResource(R.drawable.pull_ups);
                break;
            case "Running":
                icon.setImageResource(R.drawable.running);
                txtReps.setText("Distance:");
                txtSets.setText("Laps:");
                break;
            case "Weight Lifting":
                icon.setImageResource(R.drawable.weigh_lifting);
                break;
            case "Yoga":
                icon.setImageResource(R.drawable.yoga);
                txtReps.setText("");
                txtSets.setText("");
                etReps.setVisibility(View.INVISIBLE);
                etSets.setVisibility(View.INVISIBLE);
                break;
            case "Plank":
                icon.setImageResource(R.drawable.plank);
                txtReps.setText("");
                etReps.setVisibility(View.INVISIBLE);
                break;

        }

    }

    private void durationArray() {

        // adds the item to the list
        durationsName = new ArrayList<>();
        durationsName.add("Duration");
        durationsName.add("Minutes");
        durationsName.add("Hours");

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnSaveExerices) {

            String strDuration = etDuration.getText().toString();
            String strReps = etReps.getText().toString();
            String strSets = etSets.getText().toString();

            String strUsername = "Khalid Sadat";
            String strExerciseName = title.getText().toString();

            String durationName = durationSpinner.getSelectedItem().toString();


            if(strDuration.equals("") || durationName.equals("Duration")) {
                Toast.makeText(this, "Please fill all the required fields.", Toast.LENGTH_SHORT).show();
            }
            else {

                int duration = Integer.valueOf(strDuration);
                int reps = 0, sets = 0, distance = 0, laps = 0;


                if (exerciseName.equals("Push Ups") || exerciseName.equals("Weight Lifting") || exerciseName.equals("Pull Ups")) {

                    if(strReps.equals("") || strSets.equals("")) {
                        Toast.makeText(this, "Fill the required fields.", Toast.LENGTH_SHORT).show();
                    }

                        reps = Integer.valueOf(etReps.getText().toString());
                        sets = Integer.valueOf(etSets.getText().toString());


                }
                else if (exerciseName.equals("Plank")) {
                    if(strSets.equals("")) {
                        Toast.makeText(this, "Fill the required fields.", Toast.LENGTH_SHORT).show();
                    }

                    sets = Integer.valueOf(etSets.getText().toString());

                }
                else if (exerciseName.equals("Running")) {
                    if(strReps.equals("") || strSets.equals("")) {
                        Toast.makeText(this, "Fill the required fields.", Toast.LENGTH_SHORT).show();
                    }

                        laps = Integer.valueOf(etSets.getText().toString());
                        distance = Integer.valueOf(etReps.getText().toString());


                }
                else {

                }


                Exercise exercise = new Exercise(strUsername, exerciseName, duration, durationName, reps, sets, distance, laps);

                dbController.addExercise(exercise);

                Log.i("addExercise", "Exercise added successfully");
                Toast.makeText(this, "Exercise added successfully", Toast.LENGTH_SHORT).show();

                Intent exerciseIntent = new Intent(this, ListOfExercises.class);
                startActivity(exerciseIntent);

                // puts every field to their default values
                etDuration.setText("");
                durationSpinner.setSelection(0);
                etReps.setText("");
                etSets.setText("");

            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(view.getId() == R.id.durationSpinner) {
            switch (position) {
                case 0:
                    Toast.makeText(this, "Select a valid duration", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    strDurationName = "Minutes";
                    break;
                case 2:
                    strDurationName = "Hours";
                    break;
                default:
                    strDurationName = "Nothing selected";
                    break;
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
