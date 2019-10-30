package com.saadatdevelopment.lifepulse.searchdialognew.diet;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lifepulselibrary.HeartConditionExercise;
import com.saadatdevelopment.lifepulse.searchdialognew.R;

import java.util.List;



/**
 * Sets the layout of every RecyclerView item
 */
public class HeartConditionViewHolder extends RecyclerView.ViewHolder {

    private HeartConditionAdapter exerciseAdapter;

    // User interface components
    protected TextView exerciseTitle;
    protected TextView exerciseDuration;
    protected TextView exerciseIntensity;
    protected ImageView exerciseImage;


    //List of exerciseList
    private List<HeartConditionExercise> exerciseList;

    //RecyclerView item
    CardView itemContact;


    /**
     * Instantiates the view objects of every RecyclerView item
     * @param listItem  Layout of the RecyclerView item
     * @param exerciseList    List of exercise objects
     */
    public HeartConditionViewHolder(View listItem, HeartConditionAdapter birdAdapter, List<HeartConditionExercise> exerciseList) {
        super(listItem);

        this.exerciseList = exerciseList;
        this.exerciseAdapter = birdAdapter;


        // Find the user interface components
        this.exerciseTitle = (TextView) listItem.findViewById(R.id.txt_exercise_title);
        this.exerciseDuration = (TextView) listItem.findViewById(R.id.txt_exercise_duration);
        this.exerciseIntensity = (TextView) listItem.findViewById(R.id.txt_intensity);
        this.exerciseImage = (ImageView) listItem.findViewById(R.id.img_exercise_heart_condition);

        itemContact = (CardView)listItem.findViewById(R.id.exercise_list_parent_view);

    }

    /**
     *  Set content of views for each item
     * @param exercise  Bird object used to set content of views
     */
    public void setExercise(HeartConditionExercise exercise) {
        // Assign the exercise to the instance variable
        exercise = exerciseList.get(getAdapterPosition());

        // Set the content to the view
        this.exerciseTitle.setText(exercise.getName());
        this.exerciseDuration.setText(exercise.getDuration());
        this.exerciseIntensity.setText(exercise.getIntensity());

        this.exerciseImage.setImageBitmap(toBitmap(exercise.getExerciseImage()));
    }

    /**
     *  Convert byte array into image
     * @param image Byte array representation of a exercise image
     * @return  Return exercise image
     */
    private static Bitmap toBitmap(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }
}

