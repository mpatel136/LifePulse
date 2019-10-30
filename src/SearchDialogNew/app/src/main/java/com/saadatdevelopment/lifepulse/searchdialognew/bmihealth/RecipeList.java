package com.saadatdevelopment.lifepulse.searchdialognew.bmihealth;

import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.Button;

import com.saadatdevelopment.lifepulse.searchdialognew.core.ICustomRecipeInputs;
import com.saadatdevelopment.lifepulse.searchdialognew.core.IMakeRecipeFragment;
import com.saadatdevelopment.lifepulse.searchdialognew.databasecontrollers.othercontroller.RecipeController;
import com.saadatdevelopment.lifepulse.searchdialognew.R;
import com.example.lifepulselibrary.Recipe;
import com.saadatdevelopment.lifepulse.searchdialognew.fragments.CustomRecipePart2;
import com.saadatdevelopment.lifepulse.searchdialognew.fragments.CustomRecipePart3;
import com.saadatdevelopment.lifepulse.searchdialognew.fragments.RecipeFragment;
import com.saadatdevelopment.lifepulse.searchdialognew.fragments.RecipeFragmentDescription;
import com.saadatdevelopment.lifepulse.searchdialognew.core.RecipeListAdapter;
import com.saadatdevelopment.lifepulse.searchdialognew.menu.DefaultMenu;

/**
 * Activity that displays custom recipes.
 */
public class RecipeList extends DefaultMenu implements IMakeRecipeFragment, ICustomRecipeInputs, View.OnClickListener {

    //The recycler view.
    RecyclerView recyclerView;
    //Layout manager.
    RecyclerView.LayoutManager layoutManager;
    //Adapter used by the class for the recycler view.
    RecipeListAdapter adapter;
    //Button used to add more recipes
    Button add;
    //The controller for recipes.
    RecipeController recipeController;
    //Reference
    //https://inducesmile.com/android-programming/how-to-convert-drawable-to-byte-array-in-android/

    /**
     * Callback that initializes the instance variables for the activity.
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);
        ActionBar bar = getSupportActionBar();

        bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
        bar.setTitle(Html.fromHtml("<font color='#7d2709'>Recipe List</font>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Toast.makeText(this, "Recipe List", Toast.LENGTH_SHORT).show();

        //Button to add
        add = (Button) findViewById(R.id.btn_add_recipe);

        add.setOnClickListener(this);

        recipeController = new RecipeController(this);


        loadRecyclerView();

    }

    /**
     * Makes/loads the layout for the recycler view.
     */
    private void loadRecyclerView(){
        this.recyclerView = (RecyclerView) findViewById(R.id.recipe_list);

        layoutManager = new LinearLayoutManager(this);

        adapter = new RecipeListAdapter(recipeController.getRecipeList(), this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

    }

    /**
     *
     * @param recipe Recipe
     */
    @Override
    public void seeRecipe(Recipe recipe) {
        RecipeFragment recipeFragment = new RecipeFragment();
        FragmentManager fm = getSupportFragmentManager();
        Bundle bundle = new Bundle();
        bundle.putParcelable("recipe", recipe);
        //Sets bundle arg to the fragment
        recipeFragment.setArguments(bundle);

        //makes fragment
        recipeFragment.show(fm, "omg");
        recipeFragment.setCancelable(true);
    }

    @Override
    public void seeDescription(Recipe recipe) {
        DialogFragment descriptionFragment = new RecipeFragmentDescription();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putParcelable("recipe", recipe);
        descriptionFragment.setArguments(bundle);
        descriptionFragment.show(ft, "Description Fragment");
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == add.getId()){
            createDialog();
        }
    }

    //makes a fragment for the second part
    private void createDialog(){
        FragmentManager fm = getSupportFragmentManager();
        CustomRecipePart2 fragment = new CustomRecipePart2();
        fragment.setCancelable(true);

        fragment.show(fm, "part2");
    }

    @Override
    public void getDescription(String description) {

    }



    @Override
    public void dialogPart2(Bundle bundle) {
        FragmentManager fm = getSupportFragmentManager();
        CustomRecipePart3 fragment = new CustomRecipePart3();

        fragment.setCancelable(true);


        fragment.setArguments(bundle);
        fragment.show(fm, "part3");
    }

    /**
     * On finish, creates the recipe and adds it to the database.
     * @param recipe
     */
    @Override
    public void onFinishRecipe(Recipe recipe) {
        recipeController.addRecipe(recipe);
        //refresh
        loadRecyclerView();
    }

    @Override
    public void delete(Recipe recipe){
        recipeController.deleteRecipe(recipe);
        loadRecyclerView();
    }
}
