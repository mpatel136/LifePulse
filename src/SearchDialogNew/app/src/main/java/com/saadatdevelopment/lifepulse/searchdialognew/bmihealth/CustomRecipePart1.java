package com.saadatdevelopment.lifepulse.searchdialognew.bmihealth;

import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.saadatdevelopment.lifepulse.searchdialognew.core.ICustomRecipeInputs;
import com.saadatdevelopment.lifepulse.searchdialognew.databasecontrollers.othercontroller.RecipeController;
import com.saadatdevelopment.lifepulse.searchdialognew.R;
import com.example.lifepulselibrary.Recipe;
import com.saadatdevelopment.lifepulse.searchdialognew.fragments.CustomRecipePart3;
import com.saadatdevelopment.lifepulse.searchdialognew.fragments.CustomRecipePart2;
import com.saadatdevelopment.lifepulse.searchdialognew.menu.DefaultMenu;

//To test the custom recipe
//Unused
public class CustomRecipePart1 extends DefaultMenu implements ICustomRecipeInputs, View.OnClickListener{

    //frag manager
    FragmentManager fm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_recipe_part1);

        ((Button) findViewById(R.id.btn_startCustomRecipe)).setOnClickListener(this);
        changeActionBarTitle("Custom Recipes");

    }

    @Override
    public void onClick(View v) {
        //use a dialog fragment instead
//        FragmentTransaction ft;
//        ft = getSupportFragmentManager().beginTransaction();
//        ft.replace(R.id.layout_custom, new CustomRecipePart2());
//        ft.commit();

        createDialog();
    }





    @Override
    public void getDescription(String description) {
        ((TextView) findViewById(R.id.txt_foodDescription)).setText(description);
    }



    //makes a fragment for the second part
    private void createDialog(){
        FragmentManager fm = getSupportFragmentManager();
        CustomRecipePart2 fragment = new CustomRecipePart2();
        fragment.setCancelable(false);

        fragment.show(fm, "part2");
    }

    @Override
    public void dialogPart2(Bundle bundle) {
        FragmentManager fm = getSupportFragmentManager();
        CustomRecipePart3 fragment = new CustomRecipePart3();

        fragment.setCancelable(false);


        fragment.setArguments(bundle);
        fragment.show(fm, "part3");
    }

    @Override
    public void onFinishRecipe(Recipe recipe) {
        RecipeController recipeController = new RecipeController(this);
        recipeController.addRecipe(recipe);
    }

    @Override
    public void delete(Recipe recipe) {

    }

    //Puts all the inputs in the resul tbox
}
