package com.saadatdevelopment.lifepulse.searchdialognew.databasecontrollers.othercontroller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.saadatdevelopment.lifepulse.searchdialognew.databasehelper.DBAccessController;
import com.example.lifepulselibrary.Recipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A controller to specifically access the custom recipes table.
 */
public class RecipeController  {


    /**
     * The name of the table to access.
     */
    private final String TABLE_NAME = "customRecipes";
    /**
     * The helper class to open  the database.
     */
    private DBAccessController databaseAccessHelper;
    /**
     * Representation of the database.
     */
    private SQLiteDatabase sqLiteDatabase;
    /**
     * Tag of this class.
     */
    private static final String TAG = RecipeController.class.getSimpleName();


    /**
     * Initializes the helper to open the database.
     * @param context Context of the application.
     */
    public RecipeController(Context context){
        this.databaseAccessHelper = DBAccessController.getInstance(context);

    }


    //read

    /**
     * Gets the a list of recipes from the database.
     * @return The list of recipes.
     */
    public List<Recipe> getRecipeList(){
        List<Recipe> list = new ArrayList<Recipe>();
        //Open the database
        sqLiteDatabase = this.databaseAccessHelper.openDatabase();
        try{


            //Get the entire table
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast())
            {
                Log.i("getRecipeList","Hello");

                //Converts the string into an arrayList
                String[] s = (cursor.getString(cursor.getColumnIndex("ingredients"))).split("/,/xzy/");
                ArrayList<String> ingredients = new ArrayList<>();
                ingredients.addAll(Arrays.asList(s));
//
                ArrayList<String> steps = new ArrayList<>();
                //Converts the string into an arrayList
                steps.addAll(Arrays.asList((cursor.getString(cursor.getColumnIndex("steps"))).split("/,/xzy/")));
                Log.i("getRecipeList",steps.get(0));

                //Recipe from the database.
                Recipe recipe = new Recipe(cursor.getString(cursor.getColumnIndex("recipeName")),
                        ingredients,
                        cursor.getString(cursor.getColumnIndex("filter")),
                        cursor.getString(cursor.getColumnIndex("description")),
                        steps);

                //
                recipe.setImage(cursor.getBlob(cursor.getColumnIndex("recipeImage")));
                list.add(recipe);

             cursor.moveToNext();
             }
            cursor.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Log.wtf("getRecipeList","Unable to get recipeList");
        }
        finally {
            this.databaseAccessHelper.closeDatabase();
        }

        return list;
    }

    //delete

    /**
     * Deletes a recipe from the table.
     */
    public void deleteRecipe(Recipe recipe){
        sqLiteDatabase = this.databaseAccessHelper.openDatabase();
        try {
            sqLiteDatabase.beginTransaction();
            sqLiteDatabase.delete(TABLE_NAME, "recipeName = ? AND description = ?", new String[]{recipe.getName(), recipe.getDescription()});
            sqLiteDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            Log.wtf(TAG, e.fillInStackTrace());
        } finally {
            sqLiteDatabase.endTransaction();
            this.databaseAccessHelper.closeDatabase();
        }
    }


    //create
    /**
     * Adds a custom recipe to the database.
     * @param recipe
     */
    public void addRecipe(Recipe recipe){

        sqLiteDatabase = this.databaseAccessHelper.openDatabase();


        String steps = "";
        for(int i = 0; i < recipe.getSteps().size(); i++){
            steps += recipe.getSteps().get(i);
            if(i != recipe.getSteps().size()-1){
                steps += "/,/xzy/";
            }
        }
        String ingredients = "";
        for(int i = 0; i < recipe.getIngredients().size(); i++){
            ingredients += recipe.getIngredients().get(i);
            if(i != recipe.getIngredients().size()-1){
                ingredients += "/,/xzy/";
            }
        }

        byte[] image = recipe.getImage();

        String recipeName = recipe.getName();
        String description = recipe.getDescription();
        String filter = recipe.getFilter();

//        Log.i("addRecipe", "addRecipe: " + steps);
//        Log.i("addRecipe", "addRecipe: " + ingredients);


        sqLiteDatabase.beginTransaction();

        try {
            ContentValues cv = new ContentValues();

            cv.put("recipeName", recipeName);
            cv.put("description", description);
            cv.put("ingredients", ingredients);
            cv.put("steps", steps);
            cv.put("recipeImage", image);
            cv.put("filter", filter);


            sqLiteDatabase.insert(TABLE_NAME, null, cv);
            sqLiteDatabase.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            this.sqLiteDatabase.endTransaction();

            this.databaseAccessHelper.closeDatabase();
        }

    }




}
