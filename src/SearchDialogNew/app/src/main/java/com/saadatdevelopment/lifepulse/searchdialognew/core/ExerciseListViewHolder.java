package com.saadatdevelopment.lifepulse.searchdialognew.core;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.lifepulselibrary.Exercise;
import com.saadatdevelopment.lifepulse.searchdialognew.databasecontrollers.Exercises.ExerciseDBController;
import com.saadatdevelopment.lifepulse.searchdialognew.R;

import java.util.ArrayList;
import java.util.List;

public class ExerciseListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener, AdapterView.OnItemSelectedListener {

    private ExerciseListAdapter exerciseListAdapter;

    // UI components
    protected TextView txtExerciseName, txtDuration, txtRepsTitle, txtReps, txtSetsTitle, txtSets;

    protected TextView txtExerciseSetsTitle, txtExerciseRepsTitle;

    protected ImageView imgExerciseIcon;

    // list of exercises
    private List<Exercise> exercises;

    // recycelview item
    CardView exercisesCardView;

    // view
    private View holderView;

    // current row exercise object used
    private Exercise exercise;

    // Main context
    private Context contex;

    // position of the exercise
    private int exercisePosition;

    // snackbar
    private Snackbar snackbar;

    //
    private ExerciseDBController dbController;

    // dialog
    private Dialog dialog;
    private Spinner durationSpinner;
    private List<String> durationsName;
    private ArrayAdapter<String> durationArrayAdapter;
    private String strDurationName = "";
    private String exerciseName, duration, durationName, sets, reps, laps, distance;

    private String setsOrLaps, repsOrDistance;

    private EditText etExerciseDuration, etReps, etSets;
    private TextView txtExerciseNameUpdate;
    private ImageView imgClose;
    private Button btnUpdate;


    public ExerciseListViewHolder(View lstItemRowView, ExerciseListAdapter exerciseListAdapter, List<Exercise> iExercise, Context context) {
        super(lstItemRowView);

        holderView = lstItemRowView;

        this.exercises = iExercise;
        this.exerciseListAdapter = exerciseListAdapter;
        this.contex = context;


        // Find the user interface components
        this.imgExerciseIcon = (ImageView) lstItemRowView.findViewById(R.id.imgExerciseList);
        this.txtExerciseName = (TextView) lstItemRowView.findViewById(R.id.txtExerciseListTitle);
        this.txtDuration = (TextView) lstItemRowView.findViewById(R.id.txtDurationExerciseList);
        this.txtRepsTitle = (TextView) lstItemRowView.findViewById(R.id.txtRepsTitleExerciseList);
        this.txtReps = (TextView) lstItemRowView.findViewById(R.id.txtRepsExerciseList);
        this.txtSetsTitle = (TextView) lstItemRowView.findViewById(R.id.txtSetsTitleExerciseList);
        this.txtSets = (TextView) lstItemRowView.findViewById(R.id.txtSetsExerciseList);

        //Gets the whole item
        exercisesCardView = (CardView) lstItemRowView.findViewById(R.id.exercise_list_card_view);

        //Set onClick listener for item
        exercisesCardView.setOnCreateContextMenuListener(this);


        //
        dbController = new ExerciseDBController(this.contex);


    }


    public ExerciseListViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.imgCloseUpdateDialog) {
            dialog.dismiss();
        }
        else if(v.getId() == R.id.btnUpdateExercise) {

            // receives the intent request
            //Intent getIntent = ((Activity) contex).getIntent();
            //getIntent.getExtras();

            //Toast.makeText(contex, "clicked", Toast.LENGTH_SHORT).show();

            String newDuration, newLength, newReps, newSets;

            newDuration = etExerciseDuration.getText().toString();
            newLength = durationSpinner.getSelectedItem().toString();
            newReps = etReps.getText().toString();
            newSets = etSets.getText().toString();

            // toast message
            String toastMessage = "";


            // update the db
            if(exerciseName.equals("Push Ups") || exerciseName.equals("Weight Lifting") || exerciseName.equals("Pull Ups")) { // pullups, weight lifting, pushups
                Exercise exercise = exercises.get(exercisePosition);
                dbController.updatePWPExercises(exercise, newDuration, newLength, newSets, newReps);
                toastMessage = "PWP Update successfully";

            }
            else if(exerciseName.equals("Plank")) { // planks
                Exercise exercise = exercises.get(exercisePosition);
                dbController.updatePlankExercise(exercise, newDuration, newLength, newSets);
                toastMessage = "Plank Update successfully";
            }
            else if(exerciseName.equals("Running")) { // running
                Exercise exercise = exercises.get(exercisePosition);
                dbController.updateRunningExercise(exercise, newDuration, newLength, newReps, newSets);
                toastMessage = "Running Update successfully";
            }
            else if(exerciseName.equals("Yoga")) {
                Exercise exercise = exercises.get(exercisePosition);
                dbController.updateYogaExercise(exercise, newDuration, newLength);
                toastMessage = "Yoga Update successfully";
            }
            else {
                toastMessage = "Could not update";
            }

            this.exerciseListAdapter.notifyDataSetChanged();

            String message = exerciseName + " updated successfully";

            IExerciseChangeListener updateExerciseListener = (IExerciseChangeListener) contex;
            updateExerciseListener.onExerciseUpdateListener(message);

            dialog.dismiss();


            //Toast.makeText(contex, toastMessage, Toast.LENGTH_SHORT).show();

        }

    }

    public void deleteAnExercise(int position) {

        Exercise exercise = exercises.get(position);

        this.exercises.remove(position);
        this.exerciseListAdapter.notifyItemRemoved(position);
        this.exerciseListAdapter.notifyItemRangeChanged(position, this.exercises.size());

        dbController.deleteExercise(exercise);
    }




    /**
     * Creates a context menu for the cardview in the recycle view
     * @param menu  Context menu to be displayed
     * @param v     Layout for context menu
     * @param menuInfo  Menu info
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        exercisePosition = getAdapterPosition();

        //Get current bird
        exercise = exercises.get(getAdapterPosition());
        Log.i("MyViewHolder", "current exercise: "+ exercise.getExerciseName());

        //Set up options for the context menu
        menu.setHeaderTitle("Select an option:");

        menu.add(getAdapterPosition(), 100, 0, "Edit").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                showUpdateDialog();
                return true;
            }
        });

        menu.add(getAdapterPosition(), 101, 1, "Remove").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                removeExerciseAlertDialog();
                return  true;
            }
        });
    }

    /**
     * Shows the remove exercise alert dialog box
     */
    private void removeExerciseAlertDialog() {

        final String exerciseName = exercises.get(getAdapterPosition()).getExerciseName();

        AlertDialog alertDialog = new AlertDialog.Builder(contex).create();
        alertDialog.setTitle("Remove exercise");
        alertDialog.setMessage("Are you sure you want to remove " + exerciseName + "?");
        alertDialog.setCancelable(false);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteAnExercise(exercisePosition); // deletes the exercise
                        //Toast.makeText(contex, "Exercise deleted successfully!", Toast.LENGTH_SHORT).show();
                        String message = exerciseName + " deleted successfully";
                        IExerciseChangeListener changeListener = (IExerciseChangeListener) contex;
                        changeListener.onExerciseDeleteListener(message);

                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    /**
     * Shows the update dialog box
     */
    public void showUpdateDialog() {
        dialog = new Dialog(contex);

        dialog.setContentView(R.layout.activity_update_exercise);

        // title

        /// instantiate values
        // instantiate spinner
        durationSpinner = (Spinner) dialog.findViewById(R.id.spinnerDurationUpdate);

        // sets the adapter to the spinner
        durationArray();
        durationArrayAdapter = new ArrayAdapter<String>(contex, R.layout.support_simple_spinner_dropdown_item, durationsName);
        durationSpinner.setAdapter(durationArrayAdapter);

        // add click listener
        durationSpinner.setOnItemSelectedListener(this);

        // instantiate values
        txtExerciseNameUpdate = (TextView) dialog.findViewById(R.id.txtExerciseNameUpdate);
        txtExerciseSetsTitle = (TextView) dialog.findViewById(R.id.txtUpdateExerciseSetsTitle);
        txtExerciseRepsTitle = (TextView) dialog.findViewById(R.id.txtUpdateExerciseRepsTitle);

        etExerciseDuration = (EditText) dialog.findViewById(R.id.etUpdateExerciseDuration);
        etReps = (EditText) dialog.findViewById(R.id.etUpdateExerciseReps);
        etSets = (EditText) dialog.findViewById(R.id.etUpdateExerciseSets);

        imgClose = (ImageView) dialog.findViewById(R.id.imgCloseUpdateDialog);
        imgClose.setOnClickListener(this);

        btnUpdate = (Button) dialog.findViewById(R.id.btnUpdateExercise);
        btnUpdate.setOnClickListener(this);

        // Sets the values to the edit texts + textviews + spinners
        exerciseName = String.valueOf(exercises.get(getAdapterPosition()).getExerciseName());
        duration = String.valueOf(exercises.get(getAdapterPosition()).getDuration());
        durationName = String.valueOf(exercises.get(getAdapterPosition()).getDurationName());


        // shows the values
        txtExerciseNameUpdate.setText(exerciseName);

        etExerciseDuration.setText(duration);
        durationSpinner.setSelection(((ArrayAdapter<String>) durationSpinner.getAdapter()).getPosition(durationName));
        etSets.setText(showSetsOrLaps());
        etReps.setText(showRepsOrDistance());

        // update the dialog texts
        if(exerciseName.equals("Running")) {
            txtExerciseSetsTitle.setText("Laps:");
            txtExerciseRepsTitle.setText("Distance:");
        }
        else if(exerciseName.equals("Yoga")) {
            txtExerciseRepsTitle.setVisibility(View.INVISIBLE);
            txtExerciseSetsTitle.setVisibility(View.INVISIBLE);
            etReps.setVisibility(View.INVISIBLE);
            etSets.setVisibility(View.INVISIBLE);
        }
        else if(exerciseName.equals("Plank")) {
            txtExerciseRepsTitle.setVisibility(View.INVISIBLE);
            etReps.setVisibility(View.INVISIBLE);
        }

        this.exerciseListAdapter.notifyItemChanged(exercisePosition);


        // show the dialog
        dialog.show();
    }

    private void durationArray() {

        // adds the item to the list
        durationsName = new ArrayList<>();
        durationsName.add("Duration");
        durationsName.add("Minutes");
        durationsName.add("Hours");
    }

    private String showRepsOrDistance() {

        reps = String.valueOf(exercises.get(getAdapterPosition()).getReps());
        distance = String.valueOf(exercises.get(getAdapterPosition()).getDistance());

        String result = "";

        if(exerciseName.equals("Push Ups") || exerciseName.equals("Weight Lifting") || exerciseName.equals("Pull Ups")) {
            result = reps;
            repsOrDistance = "Reps";
        }
        else if(exerciseName.equals("Running")) {
            result = distance;
            repsOrDistance = "Distance";
        }

        return result;
    }

    private String showSetsOrLaps() {

        sets = String.valueOf(exercises.get(getAdapterPosition()).getSets());
        laps = String.valueOf(exercises.get(getAdapterPosition()).getLaps());

        String result = "";

        if(exerciseName.equals("Push Ups") || exerciseName.equals("Weight Lifting") || exerciseName.equals("Pull Ups")) {
            result = sets;
            setsOrLaps = "Sets";
        }
        else if(exerciseName.equals("Plank")) {
            result = sets;
            setsOrLaps = "Sets";
        }
        else if(exerciseName.equals("Running")) {
            result = laps;
            setsOrLaps = "Laps";
        }

        return result;

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(view.getId() == R.id.durationSpinner) {
            switch (position) {
                case 0:
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
