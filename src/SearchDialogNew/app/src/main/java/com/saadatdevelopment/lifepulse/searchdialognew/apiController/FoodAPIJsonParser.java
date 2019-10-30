package com.saadatdevelopment.lifepulse.searchdialognew.apiController;

import android.util.Log;

import com.example.lifepulselibrary.Food;
import com.example.lifepulselibrary.FoodModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class FoodAPIJsonParser
{
    private final String TAG = "CustomJSONParser";

    public List<FoodModel> parse(String result)
    {
        final String FOODS = "foods";
        final String NAME = "name";
        final String PROTEIN = "protein";
        final String SUGAR = "sugar";
        final String FAT = "fat";
        final String CHOLESTEROL = "cholesterol";
        final String PICTURE = "imgeUrl";

        JSONObject response = null;
        List<FoodModel> foodModelList = new ArrayList<>();
        try
        {
            response = new JSONObject(result);
            JSONArray foods = response.getJSONArray(FOODS);
            for(int i = 0; i < foods.length(); i++)
            {
                JSONObject jsonFoodObj = foods.getJSONObject(i);

                FoodModel food = new FoodModel();

                Collections.sort(foodModelList, FoodModel.FOOD_ASCENDING);

                food.setName(jsonFoodObj.getString(NAME));
                food.setProtein(jsonFoodObj.getString(PROTEIN));
                food.setSugar(jsonFoodObj.getString(SUGAR));
                food.setFat(jsonFoodObj.getString(FAT));
                food.setCholesterol(jsonFoodObj.getString(CHOLESTEROL));
                food.setImageUrl(jsonFoodObj.getString(PICTURE));

                foodModelList.add(food);
            }
            for(int i = 0; i < foodModelList.size(); i++)
            {
                Log.i(TAG, "Parsing the result" + foodModelList.get(i));
            }
        }
        catch(JSONException exception)
        {
            Log.e(TAG, exception.getMessage());
        }
        return foodModelList;
    }
}
