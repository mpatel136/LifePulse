package com.saadatdevelopment.lifepulse.searchdialognew.core;

import android.os.Bundle;

import com.example.lifepulselibrary.Recipe;

/*
Temp
 */
public interface ICustomRecipeInputs {

    void getDescription(String description);


    void dialogPart2(Bundle bundle);

    void onFinishRecipe(Recipe recipe);

    void delete(Recipe recipe);
}
