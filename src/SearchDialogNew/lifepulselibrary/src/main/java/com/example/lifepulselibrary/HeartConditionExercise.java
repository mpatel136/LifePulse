package com.example.lifepulselibrary;

public class HeartConditionExercise {

    private String name;
    private String duration;
    private String intensity;
    private byte[] exerciseImageId;

    /**
     *
     * @param name
     * @param duration
     * @param intensity
     */
    public HeartConditionExercise(String name, String duration, String intensity, byte[] exerciseImageId) {
        this.name = name;
        this.duration = duration;
        this.intensity = intensity;
        this.exerciseImageId = exerciseImageId;
    }

    public byte[] getExerciseImage() {
        return exerciseImageId;
    }

    public void setExerciseImageId(byte[] exerciseImageId) {
        this.exerciseImageId = exerciseImageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getIntensity() {
        return intensity;
    }

    public void setIntensity(String intensity) {
        this.intensity = intensity;
    }

}
