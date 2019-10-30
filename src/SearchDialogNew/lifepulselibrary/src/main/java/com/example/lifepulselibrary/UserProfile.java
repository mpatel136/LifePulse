package com.example.lifepulselibrary;

import android.os.Parcel;
import android.os.Parcelable;

public class UserProfile implements Parcelable {

    private String fullName, gender, dob, activityLevel;
    private double height, weight;
    private byte[] profilePicture;

    public UserProfile() {
    }

    public UserProfile(String fullName, String gender, String dob, String activityLevel, double height, double weight, byte[] profilePic) {
        this.fullName = fullName;
        this.gender = gender;
        this.dob = dob;
        this.activityLevel = activityLevel;
        this.height = height;
        this.weight = weight;
        this.profilePicture = profilePic;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(String activityLevel) {
        this.activityLevel = activityLevel;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    protected UserProfile(Parcel in ) {
        fullName = in.readString();
        dob = in.readString();
        gender = in.readString();
        activityLevel = in.readString();
        height = in.readInt();
        weight = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(fullName);
        dest.writeString(dob);
        dest.writeString(gender);
        dest.writeString(activityLevel);
        dest.writeDouble(height);
        dest.writeDouble(weight);

    }

    public static final Creator<UserProfile> CREATOR = new Creator<UserProfile>() {
        @Override
        public UserProfile createFromParcel(Parcel source) {
            return new UserProfile(source);
        }

        @Override
        public UserProfile[] newArray(int size) {
            return new UserProfile[size];
        }
    };
}
