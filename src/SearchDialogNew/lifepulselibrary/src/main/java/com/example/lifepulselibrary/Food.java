package com.example.lifepulselibrary;

import android.graphics.drawable.Drawable;

import java.util.Comparator;

public class Food {
    private String name;
    private String description;
    private byte[] image;
    private int id;

    public Food(String name, String description, byte[] image)
    {
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public Food(String name, String description)
    {
        this.name = name;
        this.description = description;
    }

    public Food()
    {

    }


    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public byte[] getImage(){return image;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }


}
