package com.example.lifepulselibrary;

import android.os.Parcel;
import android.os.Parcelable;

public class Exercise implements Parcelable {

    private String username, exerciseName, durationName;
    private int id, duration, reps, sets, distance, laps;

    public Exercise(String username, String exerciseName, int duration, String durationName, int reps, int sets, int distance, int laps) {
        this.username = username;
        this.exerciseName = exerciseName;
        this.durationName = durationName;
        this.duration = duration;
        this.reps = reps;
        this.sets = sets;
        this.distance = distance;
        this.laps = laps;
    }


    public Exercise() {
    }

    // constructor for pushups, weight lifting, pullups, and running
    public Exercise(String username, String exerciseName, int duration, String durationName, int reps, int sets) {

        this.username = username;
        this.exerciseName = exerciseName;
        this.durationName = durationName;
        this.duration = duration;

        if(exerciseName.equals("Push Ups") || exerciseName.equals("Weight Lifting") || exerciseName.equals("Pull Ups")) {
            this.reps = reps;
            this.sets = sets;
        }
        else if(exerciseName.equals("Running")) {
            this.laps = reps;
            this.distance = sets;
        }

    }

    // constructor for plank
    public Exercise(String username, String exerciseName, int duration, String durationName, int sets) {

        this.username = username;
        this.exerciseName = exerciseName;
        this.durationName = durationName;
        this.duration = duration;
        this.sets = sets;
    }

    // constructor for yoga
    public Exercise(String username, String exerciseName, int duration, String durationName) {
        this.username = username;
        this.exerciseName = exerciseName;
        this.durationName = durationName;
        this.duration = duration;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getDurationName() {
        return durationName;
    }

    public void setDurationName(String durationName) {
        this.durationName = durationName;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getLaps() {
        return laps;
    }

    public void setLaps(int laps) {
        this.laps = laps;
    }

    protected Exercise(Parcel in) {
        id = in.readInt();
        username = in.readString();
        exerciseName = in.readString();
        durationName = in.readString();
        duration = in.readInt();
        sets = in.readInt();
        reps = in.readInt();
        distance = in.readInt();
        laps = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(username);
        dest.writeString(exerciseName);
        dest.writeString(durationName);
        dest.writeInt(duration);
        dest.writeInt(reps);
        dest.writeInt(sets);
        dest.writeInt(distance);
        dest.writeInt(laps);
    }

    public static final Creator<Exercise> CREATOR = new Creator<Exercise>() {
        @Override
        public Exercise createFromParcel(Parcel source) {
            return new Exercise(source);
        }

        @Override
        public Exercise[] newArray(int size) {
            return new Exercise[size];
        }
    };
}
