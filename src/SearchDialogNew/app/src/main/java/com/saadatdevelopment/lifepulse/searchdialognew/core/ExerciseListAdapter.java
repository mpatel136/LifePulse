package com.saadatdevelopment.lifepulse.searchdialognew.core;

import android.app.Dialog;
import android.content.Context;
import android.os.CountDownTimer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lifepulselibrary.Exercise;
import com.saadatdevelopment.lifepulse.searchdialognew.databasecontrollers.Exercises.ExerciseDBController;
import com.saadatdevelopment.lifepulse.searchdialognew.R;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import pl.droidsonroids.gif.GifImageView;

public class ExerciseListAdapter extends RecyclerView.Adapter<ExerciseListViewHolder> implements View.OnClickListener {

    // list for exercise list adapter
    private List<Exercise> exercises;

    // db controller for exericse
    private ExerciseDBController exerciseDBController;

    // main activity context
    private Context context;

    // components for the dialog
    private TextView txtExerciseName, txtSetsTitle, txtSets, txtRepsTitle, txtReps, txtDurationTimer, txtDurationName, txtTimerCountDown;
    private ImageView btnImageCancel;
    private GifImageView imgExercise;
    private Button btnStartExercise;

    protected ExerciseListViewHolder eViewHolder;

    // timers components
    private long startTimerInMillis;
    private long timeLeftInMillis;
    private long endTime;
    private CountDownTimer countDownTimer;
    private Boolean isTimerRunning = false;

    // variables for timer - initial
    private String input;
    private long millisInput;

    // dialog
    private Dialog dialog;

    //
    private int position;

    //Exericse view holder - copy
    private ExerciseListViewHolder vHolder;


    /**
     * Constructor that sets the exercise list and the context
     * @param exercises lis of exercises
     * @param ctx main activity context
     */
    public ExerciseListAdapter(List<Exercise> exercises, Context ctx) {
        this.exercises = exercises;
        this.context = ctx;
    }

    public ExerciseListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // Inflate the layout item
        View view = LayoutInflater.from(context).inflate(R.layout.layout_exericse_list_structure, parent, false);

        // creates the view holder
        final ExerciseListViewHolder exerciseListViewHolder = new ExerciseListViewHolder(view, this, exercises, context);

        // create the dialog box
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.fragment_exercise_structure);

        // create view objects for exercise fragment
        imgExercise = (GifImageView) dialog.findViewById(R.id.gifExerciseFragment);
        txtExerciseName = (TextView) dialog.findViewById(R.id.txtExerciseNameFragment);
        txtSetsTitle = (TextView) dialog.findViewById(R.id.txtSetsTitleFragment);
        txtSets = (TextView) dialog.findViewById(R.id.txtSetsFragment);
        txtRepsTitle = (TextView) dialog.findViewById(R.id.txtRepsTitleFragment);
        txtReps = (TextView) dialog.findViewById(R.id.txtRepsFragment);
        txtDurationTimer = (TextView) dialog.findViewById(R.id.txtDurationTimeFragment);
        txtDurationName = (TextView) dialog.findViewById(R.id.txtDurationNameFragment);
        txtTimerCountDown = (TextView) dialog.findViewById(R.id.txtTimerFragment);

        btnImageCancel = (ImageView) dialog.findViewById(R.id.imgCancleExerciseDialog);
        btnImageCancel.setOnClickListener(this);

        btnStartExercise = (Button) dialog.findViewById(R.id.btnStartExerciseFragment);
        btnStartExercise.setOnClickListener(this);

        //Adds onClick listener to every RecyclerView item
        exerciseListViewHolder.exercisesCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Sets the content of dialog view
                String exerciseName = exercises.get(exerciseListViewHolder.getAdapterPosition()).getExerciseName();
                txtExerciseName.setText(exerciseName);

                // sets the viewholder for every class - protected
                eViewHolder = exerciseListViewHolder;

                // Sets the exercise image in the dialog fragment
                setExerciseImageFragment(exerciseName);

                String setsOrLapsTitle = "", repsOrDistanceTitle = "", setsOrLapsValue = "", repsOrDistanceValue = "";

                if(exerciseName.equals("Push Ups") || exerciseName.equals("Weight Lifting") || exerciseName.equals("Pull Ups")) {
                    setsOrLapsTitle = "Sets";
                    setsOrLapsValue = String.valueOf(exercises.get(exerciseListViewHolder.getAdapterPosition()).getSets());

                    repsOrDistanceTitle = "Reps";
                    repsOrDistanceValue = String.valueOf(exercises.get(exerciseListViewHolder.getAdapterPosition()).getReps());

                    final ViewGroup.LayoutParams layoutParams = (LinearLayout.LayoutParams) txtReps.getLayoutParams();
                    ((LinearLayout.LayoutParams) layoutParams).weight = (float) 1;

                }
                else if(exerciseName.equals("Plank")) {
                    setsOrLapsTitle = "Sets";
                    setsOrLapsValue = String.valueOf(exercises.get(exerciseListViewHolder.getAdapterPosition()).getSets());

                    repsOrDistanceTitle = "";
                    repsOrDistanceValue = "";
                }
                else if(exerciseName.equals("Running")) {
                    setsOrLapsTitle = "Laps";
                    setsOrLapsValue = String.valueOf(exercises.get(exerciseListViewHolder.getAdapterPosition()).getLaps());

                    repsOrDistanceTitle = "Distance";
                    repsOrDistanceValue = String.valueOf(exercises.get(exerciseListViewHolder.getAdapterPosition()).getDistance() + " KM");

                    final ViewGroup.LayoutParams layoutParams = (LinearLayout.LayoutParams) txtReps.getLayoutParams();
                    ((LinearLayout.LayoutParams) layoutParams).weight = (float) 1.25;

                }
                else {
                    setsOrLapsTitle = "";
                    setsOrLapsValue = "";

                    repsOrDistanceTitle = "";
                    repsOrDistanceValue = "";
                }


                // converts duration string rep to an int value
                String duration = String.valueOf(exercises.get(exerciseListViewHolder.getAdapterPosition()).getDuration());
                String durationName = String.valueOf(exercises.get(exerciseListViewHolder.getAdapterPosition()).getDurationName());

                // timer values
                startTimerInMillis = TimeUnit.MINUTES.toMillis(Integer.parseInt(duration));
                timeLeftInMillis = startTimerInMillis;


                txtSetsTitle.setText(setsOrLapsTitle);
                txtSets.setText(setsOrLapsValue);

                txtRepsTitle.setText(repsOrDistanceTitle);
                txtReps.setText(repsOrDistanceValue);

                txtDurationTimer.setText(duration);

                //
                int hour;
                // change "Hours" to "Hour" if the duration is 1
                if(duration.equals("1") && durationName.equals("Hours")) {
                    txtDurationName.setText("Hour");
                    hour = Integer.parseInt(duration);
                }
                else {
                    txtDurationName.setText(durationName);
                    hour = Integer.parseInt(duration) * 60;
                }

                // show the dialog
                dialog.show();

            }
        });

        updateCountdownText();

        return exerciseListViewHolder;
    }



    /**
     * Sets the exericse images
     * @param name name of the exercise
     */
    private void setExerciseImageFragment(String name) {
        // gets the exercise heart_pulse_icon id
        ImageView icon = dialog.findViewById(R.id.gifExerciseFragment);

        switch (name) {
            case "Push Ups":
                icon.setImageResource(R.drawable.push_ups_gif);
                break;
            case "Pull Ups":
                icon.setImageResource(R.drawable.pull_ups_gif);
                break;
            case "Running":
                icon.setImageResource(R.drawable.running_gif);
                break;
            case "Weight Lifting":
                icon.setImageResource(R.drawable.weight_lifting_gif);
                break;
            case "Yoga":
                icon.setImageResource(R.drawable.yoga_gif);
                break;
            case "Plank":
                icon.setImageResource(R.drawable.plank_gif);
                break;
        }
    }

    /**
     * This method is called by LayoutManager to bind the ViewHolder with required data.
     * @param holder    BirdViewHolder for each RecyclerView item
     * @param position  Position of the item in the adapter
     */
    @Override
    public void onBindViewHolder(final ExerciseListViewHolder holder, int position) {

        // Gets the exercise object at the given position
        Exercise exercise = exercises.get(position);

        // sets the copy vholder to holder
        vHolder = holder;

        // Sets the exercise icon
        setExerciseListImage(exercise.getExerciseName());

        // Sets the value from the database to each exercises
        holder.txtExerciseName.setText(exercise.getExerciseName());

        holder.txtDuration.setText(exercise.getDuration() + " " + exercise.getDurationName());

        String setsOrLapsTitle = "";
        String repsOrDistanceTitle = "";

        String setsOrlapsValue = "";
        String repsOrDistanceValue = "";

        final ViewGroup.LayoutParams repsLayout = (LinearLayout.LayoutParams) holder.txtRepsTitle.getLayoutParams();
        ((LinearLayout.LayoutParams) repsLayout).weight = (float) 0.148;

        if(exercise.getExerciseName().equals("Push Ups") || exercise.getExerciseName().equals("Weight Lifting") || exercise.getExerciseName().equals("Pull Ups")) {

            setsOrLapsTitle = "Sets:";
            setsOrlapsValue = String.valueOf(exercise.getSets());

            repsOrDistanceTitle = "Reps:";
            repsOrDistanceValue = String.valueOf(exercise.getReps());
        }
        else if(exercise.getExerciseName().equals("Plank")) {
            setsOrLapsTitle = "Sets:";
            setsOrlapsValue = String.valueOf(exercise.getSets());

            repsOrDistanceTitle = "";
            repsOrDistanceValue = "";
        }
        else if(exercise.getExerciseName().equals("Running")) {
            setsOrLapsTitle = "Laps:";
            setsOrlapsValue = String.valueOf(exercise.getLaps());

            repsOrDistanceTitle = "Distance:";
            repsOrDistanceValue = String.valueOf(exercise.getDistance()) + " KM";

            final ViewGroup.LayoutParams distanceLayout = (LinearLayout.LayoutParams) holder.txtRepsTitle.getLayoutParams();
            ((LinearLayout.LayoutParams) distanceLayout).weight = (float) 0.04;
        }
        else {
            setsOrLapsTitle = "";
            setsOrlapsValue = "";

            repsOrDistanceTitle = "";
            repsOrDistanceValue = "";
        }


        holder.txtSetsTitle.setText(setsOrLapsTitle);
        holder.txtSets.setText(setsOrlapsValue);

        holder.txtRepsTitle.setText(repsOrDistanceTitle);
        holder.txtReps.setText(repsOrDistanceValue);

        // context menu
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setPosition(holder.getAdapterPosition());
                return false;
            }
        });

    }

    /**
     * Sets the exercise lists icons
     * @param name name of the exercise
     */
    private void setExerciseListImage(String name) {
        // gets the exercise heart_pulse_icon id
        switch (name) {
            case "Push Ups":
                vHolder.imgExerciseIcon.setImageResource(R.drawable.push_ups);
                break;
            case "Pull Ups":
                vHolder.imgExerciseIcon.setImageResource(R.drawable.pull_ups);
                break;
            case "Running":
                vHolder.imgExerciseIcon.setImageResource(R.drawable.running);
                break;
            case "Weight Lifting":
                vHolder.imgExerciseIcon.setImageResource(R.drawable.weigh_lifting);
                break;
            case "Yoga":
                vHolder.imgExerciseIcon.setImageResource(R.drawable.yoga);
                break;
            case "Plank":
                vHolder.imgExerciseIcon.setImageResource(R.drawable.plank);
                break;
        }

    }


    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public void removeExercise(int position) {
        exerciseDBController.deleteExercise(exercises.get(position));
    }


    public int getPosition() {
        return this.position;
    }

    public void setPosition(int pos) {
        this.position = pos;
    }





    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnStartExerciseFragment) {
            Toast.makeText(context, "Started", Toast.LENGTH_SHORT).show();
            txtTimerCountDown.setVisibility(View.VISIBLE);

            if(isTimerRunning) {
                pauseTimer();
            }
            else {
                startTimer();
            }

        }
        else if(v.getId() == R.id.imgCancleExerciseDialog) {
            dialog.dismiss();
        }

    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountdownText();
            }

            @Override
            public void onFinish() {
                isTimerRunning = false;
                btnStartExercise.setText("Start");
                Toast.makeText(context, "Congratulations, you've finished your exercise!", Toast.LENGTH_SHORT).show();
            }
        }.start();

        isTimerRunning = true;
        btnStartExercise.setText("Pause");

    }

    private void pauseTimer() {
        countDownTimer.cancel();
        isTimerRunning = false;
        btnStartExercise.setText("Start");
    }

    private void updateCountdownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        txtTimerCountDown.setText(timeFormatted);
    }


}
