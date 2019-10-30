package com.example.lifepulselibrary;

import java.util.ArrayList;
import java.util.List;

public class FoodsInAPI {
    private String foodId;
    private String label;
    private List<String> nutrients;

    public FoodsInAPI(){
        nutrients = new ArrayList<>();
    }

    public List<String> getNutrients() {
        return nutrients;
    }

    public String getFoodId() {
        return foodId;
    }

    public String getLabel() {
        return label;
    }

    public void addNutrients(String nutrient) {nutrients.add(nutrient);}

    public void setFoodId(String id)
    {
        this.foodId = foodId;
    }

    public void setLabel(String nameOfFood)
    {
        this.label = label;
    }

    @Override
    public String toString()
    {
        return "Hints{" +
                "foodId='" + foodId + '\'' +
                ", label='" + label +'\'' +
                ", nutrients=" + nutrients +
                '}';
    }
}
