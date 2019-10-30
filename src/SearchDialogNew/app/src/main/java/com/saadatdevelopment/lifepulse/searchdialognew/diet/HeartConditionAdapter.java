package com.saadatdevelopment.lifepulse.searchdialognew.diet;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.CountDownTimer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lifepulselibrary.HeartConditionExercise;
import com.saadatdevelopment.lifepulse.searchdialognew.R;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class HeartConditionAdapter extends RecyclerView.Adapter<HeartConditionViewHolder> implements View.OnClickListener {

    // List of exercises for this adapter
    private List<HeartConditionExercise> exerciseList;

    //HomePage context
    private Context myContext;

    //Dialog box UI components
    private TextView exerciseTitle;
    private TextView exerciseIntensity;
    private TextView exerciseDuration;
    private ImageView exerciseImage;

    private Spinner durationSpinner;
    private long startTime = TimeUnit.MINUTES.toMillis(40);
    private long timeRemaining;
    private long endTime;
    private Boolean isTimerRunning = false;
    private CountDownTimer countDownTimer;
    private Button startExerciseCounter;
    private TextView timerDisplay;

    //Dialog box
    private Dialog myDialog;

    /**
     * Constructor that sets the bird list and the context
     *
     * @param exerciseList  List of exercises
     * @param myContext HomePage context
     */
    public HeartConditionAdapter(List<HeartConditionExercise> exerciseList, Context myContext) {
        this.exerciseList = exerciseList;
        this.myContext = myContext;
    }

    /**
     * This method is called by LayoutManager to create a new row to represent an item in the list
     * to be created.
     *
     * @param parent   ViewGroup from layout
     * @param viewType ID of viewType
     * @return Return the view holder for every RecyclerView item
     */
    @Override
    public HeartConditionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // Inflate the layout_item
        View view = LayoutInflater.from(myContext).inflate(R.layout.layout_exercise_heart_condition, parent, false);
        // Create a ViewHolder
        final HeartConditionViewHolder viewHolder = new HeartConditionViewHolder(view, this, exerciseList);

        // Create Dialog box
        myDialog = new Dialog(myContext);
        myDialog.setContentView(R.layout.fragment_heart_condition_exercise_dialog);

        //Create view objects for Dialog box
        exerciseTitle = (TextView) myDialog.findViewById(R.id.txt_heart_condition_title);
        exerciseIntensity = (TextView) myDialog.findViewById(R.id.txt_intensity_dialog);
        exerciseDuration = (TextView) myDialog.findViewById(R.id.txt_duration_dialog);
        exerciseImage = (ImageView) myDialog.findViewById((R.id.img_exercise_dialog));
        durationSpinner = (Spinner) myDialog.findViewById(R.id.spinner_duration);
        startExerciseCounter = (Button) myDialog.findViewById(R.id.btnStartTimerExercise);
        timerDisplay = (TextView) myDialog.findViewById(R.id.txt_timer_fragment);
        timerDisplay.setVisibility(View.INVISIBLE);
        startExerciseCounter.setOnClickListener(this);

        ImageView closeDialogButton = (ImageView) myDialog.findViewById(R.id.img_cancel_heart_condition_dialog);
        closeDialogButton.setOnClickListener(this);

        viewHolder.itemContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Sets content of Dialog views
                exerciseTitle.setText(exerciseList.get(viewHolder.getAdapterPosition()).getName());
                exerciseIntensity.setText(exerciseList.get(viewHolder.getAdapterPosition()).getIntensity());
                exerciseDuration.setText(exerciseList.get(viewHolder.getAdapterPosition()).getDuration());
                exerciseImage.setImageBitmap(BitmapFactory.decodeByteArray(exerciseList.get(viewHolder.getAdapterPosition()).getExerciseImage(), 0, exerciseList.get(viewHolder.getAdapterPosition()).getExerciseImage().length));

                durationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            switch (position) {
                                case 0:
                                    startTime = TimeUnit.MINUTES.toMillis(40);
                                    timeRemaining = startTime;
                                    break;
                                case 1:
                                    startTime = TimeUnit.MINUTES.toMillis(60);
                                    timeRemaining = startTime;
                                    break;
                                default:
                                    startTime = TimeUnit.MINUTES.toMillis(40);
                                    timeRemaining = startTime;
                                    break;
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                timeRemaining = startTime;
                timerDisplay.setText(String.valueOf(startTime));
                //Displays Dialog box
                myDialog.show();
            }
        });
        updateCountdownText();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HeartConditionViewHolder holder, int position) {
        // Get the exercise object at the given position
        HeartConditionExercise exercise = exerciseList.get(position);

        holder.exerciseTitle.setText(exercise.getName());
        holder.exerciseIntensity.setText(exercise.getIntensity());
        holder.exerciseDuration.setText(exercise.getDuration());
        holder.exerciseImage.setImageBitmap(toBitmap(exercise.getExerciseImage()));

    }

    /**
     * Gets how many items are in the recycler view
     *
     * @return Returns size of the exerciseList list
     */
    @Override
    public int getItemCount() {
        // Return the size of the data
        return exerciseList.size();
    }

    /**
     * Dismisses the Dialog box when the close button gets clicked or if the user clicks anywhere else except on the Dialog
     *
     * @param v View that was clicked
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnStartTimerExercise) {
            Toast.makeText(myContext, "Started", Toast.LENGTH_SHORT).show();
            timerDisplay.setVisibility(View.VISIBLE);

            if(isTimerRunning) {
                pauseTimer();
            }
            else {
                startTimer();
            }

        }
        else if(v.getId() == R.id.img_cancel_heart_condition_dialog) {
            myDialog.dismiss();
        }
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(timeRemaining, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeRemaining = millisUntilFinished;
                updateCountdownText();
            }

            @Override
            public void onFinish() {
                isTimerRunning = false;
                startExerciseCounter.setText("Start");
                Toast.makeText(myContext, "Congratulations, you've finished your exercise!", Toast.LENGTH_SHORT).show();
            }
        }.start();

        isTimerRunning = true;
        startExerciseCounter.setText("Pause");

    }

    private void pauseTimer() {
        countDownTimer.cancel();
        isTimerRunning = false;
        startExerciseCounter.setText("Start");
    }

    private void updateCountdownText() {
        int minutes = (int) (timeRemaining / 1000) / 60;
        int seconds = (int) (timeRemaining / 1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        timerDisplay.setText(timeFormatted);
    }

    /**
     * Converts a byte array into an image
     *
     * @param image Image represented in a byte array
     * @return Returns image as a Bitmap
     */
    private static Bitmap toBitmap(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }
}