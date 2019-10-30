package com.example.lifepulselibrary;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class DietModel implements Parcelable {
    private int hourOfTheDay;
    private int day;
    private int min;

    private boolean on = true;
    private String description;



    public DietModel(int hourOfTheDay, int min, String description, boolean on){
        Log.i("makeDietPlan","hourOfTheDay");
        this.hourOfTheDay = hourOfTheDay;
        this.min = min;
        this.description = description;
        this.on = on;
    }

    protected DietModel(Parcel in) {
        hourOfTheDay = in.readInt();
        day = in.readInt();
        min = in.readInt();
        on = in.readByte() != 0;
        description = in.readString();
    }

    public static final Creator<DietModel> CREATOR = new Creator<DietModel>() {
        @Override
        public DietModel createFromParcel(Parcel in) {
            return new DietModel(in);
        }

        @Override
        public DietModel[] newArray(int size) {
            return new DietModel[size];
        }
    };

    public String getDescription() {
        return description;
    }

    public DietModel(int hourOfTheDay, int min, int day, String description, boolean on){
        this(hourOfTheDay,min,description, on);
        this.day = day;
    }

    public int getHourOfTheDay() {
        return hourOfTheDay;
    }

    public int getDay() {
        return day;
    }

    public int getMin() {
        return min;
    }

    public boolean isOn() {
        return on;
    }

    public void changeNotificationState(){
        on = !isOn();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(hourOfTheDay);
        dest.writeInt(day);
        dest.writeInt(min);
        dest.writeByte((byte) (on ? 1 : 0));
        dest.writeString(description);
    }
}
