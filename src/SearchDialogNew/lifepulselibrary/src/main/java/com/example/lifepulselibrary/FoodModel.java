package com.example.lifepulselibrary;

import java.util.Comparator;

public class FoodModel
{
    private String foodName;
    private String protein;
    private String sugar;
    private String fat;
    private String cholesterol;
    private String imageUrl;

    public FoodModel(String name, String protein, String sugar, String fat, String cholesterol, String imageUrl) {
        this.foodName = name;
        this.protein = protein;
        this.sugar = sugar;
        this.fat = fat;
        this.cholesterol = cholesterol;
        this.imageUrl = imageUrl;
    }

    public FoodModel() {
    }

    @Override
    public String toString() {
        return "favorite_foods{" +
                "name='" + foodName +'\'' +
                "protein='" + protein +'\'' +
                "sugar='" + sugar +'\'' +
                "fat='" + fat +'\'' +
                "cholesterol='" + cholesterol +'\'' +
                "imageUrl='" + imageUrl +
                '}';
    }

    public String getName() {
        return foodName;
    }

    public void setName(String name) {
        this.foodName = name;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public String getSugar() {
        return sugar;
    }

    public void setSugar(String sugar) {
        this.sugar = sugar;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public String getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(String cholesterol) {
        this.cholesterol = cholesterol;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public static final Comparator<FoodModel> FOOD_ASCENDING = new Comparator<FoodModel>() {
        @Override
        public int compare(FoodModel food1, FoodModel food2) {
            return food1.foodName.compareTo(food2.foodName);
        }
    };
}
