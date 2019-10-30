package com.saadatdevelopment.lifepulse.searchdialognew.databasecontrollers.othercontroller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.saadatdevelopment.lifepulse.searchdialognew.databasehelper.DBAccessController;
import com.example.lifepulselibrary.DietModel;
import com.saadatdevelopment.lifepulse.searchdialognew.diet.DietPlanRevamp.IControllerInteraction;

import java.util.ArrayList;
import java.util.List;

public class DietController {
    final String TABLE_NAME = "dietplan";
    /**
     * The helper class to open  the database.
     */
    private DBAccessController databaseAccessHelper;
    /**
     * Representation of the database.
     */
    private SQLiteDatabase sqLiteDatabase;

    //Represents the activity
    private Context context;

    public DietController(Context context){
        this.databaseAccessHelper = DBAccessController.getInstance(context);
        this.context = context;
    }


    public List<DietModel> getDietList(){
        List<DietModel> list = new ArrayList<>();

        sqLiteDatabase = this.databaseAccessHelper.openDatabase();
        Log.i("getDietList",  "At get Diet List");

        try {
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                DietModel diet;
                boolean on;

                if(Integer.parseInt(cursor.getString(cursor.getColumnIndex("ison"))) == 0){
                    on = false;
                }
                else on = true;

                int hour = Integer.parseInt(cursor.getString(cursor.getColumnIndex("hour")));
                int min = Integer.parseInt(cursor.getString(cursor.getColumnIndex("min")));
                int day = Integer.parseInt(cursor.getString(cursor.getColumnIndex("day")));
                Log.i("getDietList", day + "");

                String description = cursor.getString(cursor.getColumnIndex("description"));


                diet = new DietModel(hour,min,day,description,on);

                list.add(diet);

                cursor.moveToNext();
            }
        }catch (Exception e){
            e.getStackTrace();
        }
        finally {
            this.databaseAccessHelper.closeDatabase();
        }

        return list;
    }

    public void addDiet(DietModel model){
        Log.i("addDiet", "description : " + model.getDescription());
        Log.i("addDiet", "hours of day :" + model.getHourOfTheDay() + "");
        Log.i("addDiet", "min: " + model.getMin() + "");
        Log.i("addDiet", "isOn:" + model.isOn() + "");
        Log.i("addDiet","Days: " + model.getDay());
        ContentValues cv = new ContentValues();
        cv.put("ison",model.isOn() ? 1 : 0);
        cv.put("hour",model.getHourOfTheDay());
        cv.put("min",model.getMin());
        cv.put("day",model.getDay());
        cv.put("description",model.getDescription());

        sqLiteDatabase = this.databaseAccessHelper.openDatabase();


        sqLiteDatabase.beginTransaction();

        try {
            sqLiteDatabase.insert(TABLE_NAME, null, cv);
            sqLiteDatabase.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
            Log.wtf("addDiet","Doesn't work");
        }
        finally {
            this.sqLiteDatabase.endTransaction();

            this.databaseAccessHelper.closeDatabase();
        }
    }

    public void updateDiet(DietModel dietModel, int position){
        ContentValues cv = new ContentValues();
        cv.put("ison",dietModel.isOn() ? 1 : 0);
        cv.put("hour",dietModel.getHourOfTheDay());
        cv.put("min",dietModel.getMin());
        cv.put("day",dietModel.getDay());
        cv.put("description",dietModel.getDescription());

        sqLiteDatabase = this.databaseAccessHelper.openDatabase();

        try {
            sqLiteDatabase.update(TABLE_NAME, cv, "position=" + position,null);
        }catch (Exception e){

        }
        finally {
            sqLiteDatabase.close();
            this.databaseAccessHelper.closeDatabase();
        }
    }

    public void removeDiet(DietModel model){
        sqLiteDatabase = this.databaseAccessHelper.openDatabase();
        try {
            sqLiteDatabase.beginTransaction();
            //check up on this
            sqLiteDatabase.delete(TABLE_NAME, "hour = ? AND min = ? AND day = ? AND description = ?", new String[]{String.valueOf(model.getHourOfTheDay()), String.valueOf(model.getMin()), String.valueOf(model.getDay()), model.getDescription()});
            sqLiteDatabase.setTransactionSuccessful();
        }
        catch (Exception e){
            Log.wtf("addDiet", e.fillInStackTrace());
        }
        finally {
            sqLiteDatabase.endTransaction();
            this.databaseAccessHelper.closeDatabase();

            IControllerInteraction activity = (IControllerInteraction) context;
            activity.reloadRecycler();
        }
    }
}
