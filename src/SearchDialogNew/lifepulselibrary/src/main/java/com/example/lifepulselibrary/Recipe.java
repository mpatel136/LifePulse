package com.example.lifepulselibrary;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
//Allows to parse a txt file into a recipe
//Vice Versa

public class Recipe implements Parcelable {

    //IMAGE

    private String name;
    private ArrayList<String> ingredients;
    private String filter;
    private String description;
    private ArrayList<String> steps;
    private byte[] image;


    public Recipe(String name, ArrayList<String> ingredients, String filter, String description, ArrayList<String> steps){
        this.name = name;
        this.ingredients = ingredients;
        this.description = description;
        this.filter = filter;
        this.steps = steps;
    }

    protected Recipe(Parcel in) {
        name = in.readString();
        ingredients = in.createStringArrayList();
        filter = in.readString();
        description = in.readString();
        steps = in.createStringArrayList();
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    //GETTERS
    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public ArrayList<String> getIngredients(){
        return this.ingredients;
    }

    public String getFilter(){
        return this.filter;
    }

    public ArrayList getSteps(){
        return this.steps;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeStringList(ingredients);
        dest.writeString(filter);
        dest.writeString(description);
        dest.writeStringList(steps);
    }

    public void setDescription(String description){
        this.description = description;
    }
    public void setImage(byte[] image){
        this.image = image;
    }




    public Recipe clone(){
        Recipe clone = new Recipe(this.name, this.ingredients, this.filter, this.description, this.steps);
        clone.setImage(this.image);
        return clone;
    }
    public byte[] getImage(){
        return this.image;
    }

    //idk how to implement this yet
    //public Picture getPicture
}
