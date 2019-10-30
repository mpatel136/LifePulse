package com.example.lifepulselibrary;

public class Diary {

    private int id;
    private String name, guiltLevel, date, description;
    private byte[] image;


    public Diary(String name, String guiltLevel, String date, String description, byte[] image) {
        this.name = name;
        this.guiltLevel = guiltLevel;
        this.date = date;
        this.description = description;
        this.image = image;
    }

    public Diary() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGuiltLevel() {
        return guiltLevel;
    }

    public void setGuiltLevel(String guiltLevel) {
        this.guiltLevel = guiltLevel;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
