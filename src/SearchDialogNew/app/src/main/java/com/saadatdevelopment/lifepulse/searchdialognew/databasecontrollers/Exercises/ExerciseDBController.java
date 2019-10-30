package com.saadatdevelopment.lifepulse.searchdialognew.databasecontrollers.Exercises;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.lifepulselibrary.Exercise;
import com.saadatdevelopment.lifepulse.searchdialognew.databasecontrollers.DatabaseController;
import com.saadatdevelopment.lifepulse.searchdialognew.databasehelper.DBAccessController;

import java.util.ArrayList;
import java.util.List;

public class ExerciseDBController {
    private DBAccessController databaseAccessHelper;
    private SQLiteDatabase sqLiteDatabase;
    private static final String TAG = DatabaseController.class.getSimpleName();
    private static final String TABLE_EXERCISE_LIST = "exerciseList";

    private static final String COL_USER_ID = "userId";
    private static final String COL_USER_NAME = "username";
    private static final String COL_EXERCISE_NAME = "exerciseName";
    private static final String COL_DURATION = "duration";
    private static final String COL_DURATION_NAME = "durationName";
    private static final String COL_REPS = "reps";
    private static final String COL_DISTANCE = "distance";
    private static final String COL_SETS = "sets";
    private static final String COL_LAPS = "laps";


    /**
     *  Creates a controller for controlling the interactions between a view and the contact model.
     *
     * @param context The application context that must be passed from an android view.
     */
    public ExerciseDBController(Context context) {
        this.databaseAccessHelper = DBAccessController.getInstance(context);
    }

    /**
     * Adds a new exercise to the database
     * @param exercise
     */
    public void addExercise(Exercise exercise){
        Log.i("addExercise", "addExercise: starting");
        sqLiteDatabase = this.databaseAccessHelper.openDatabase();

        String username = exercise.getUsername();
        String exerciseName = exercise.getExerciseName();
        String durationName = exercise.getDurationName();
        int duration = exercise.getDuration();
        int reps = exercise.getReps();
        int sets = exercise.getSets();
        int distance = exercise.getDistance();
        int laps = exercise.getLaps();


        Log.i("addExercise", "username:" + username + ", exerciseName: " + exerciseName + " duration: "
                + duration + " durationName" + durationName + " reps: " + reps  + " distance: " + distance +  ", sets:" + sets + ", laps:" + laps);
        try {

            sqLiteDatabase.beginTransaction();

            String sql = "INSERT INTO exerciseList(username, exerciseName, duration, durationName, reps, distance, sets, laps) VALUES " +
                    "('" +username + "','" + exerciseName + "','" + duration + "', '" + durationName +"', '"+ reps +"', '"+ distance +"', " +
                    "'"+ sets +"', '"+ laps+"')";
            sqLiteDatabase.execSQL(sql);
            sqLiteDatabase.setTransactionSuccessful();

            Log.i("addExercise", "Added successfully");

        } catch (Exception e) {
            Log.e("DatabaseController", e.toString());
        }
        finally {
            sqLiteDatabase.endTransaction();
            // close the db
            this.databaseAccessHelper.closeDatabase();
        }
    }

    /**
     * Gets all the exercises from the database
     * @return
     */
    public List<Exercise> getAllExercises() {
        List<Exercise> exercises = exercises = new ArrayList<>();
        //-- 1) Open the database.
        sqLiteDatabase = this.databaseAccessHelper.openDatabase();
        try {
            // 2) Query the database.
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_EXERCISE_LIST, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Exercise exercise = new Exercise();

                exercise.setId(cursor.getInt(cursor.getColumnIndex("userId")));
                exercise.setUsername(cursor.getString(cursor.getColumnIndex("username")));
                exercise.setExerciseName(cursor.getString(cursor.getColumnIndex("exerciseName")));
                exercise.setDuration(cursor.getInt(cursor.getColumnIndex("duration")));
                exercise.setDurationName(cursor.getString(cursor.getColumnIndex("durationName")));
                exercise.setReps(cursor.getInt(cursor.getColumnIndex("reps")));
                exercise.setDistance(cursor.getInt(cursor.getColumnIndex("distance")));
                exercise.setSets(cursor.getInt(cursor.getColumnIndex("sets")));
                exercise.setLaps(cursor.getInt(cursor.getColumnIndex("laps")));

                exercises.add(exercise);
                cursor.moveToNext();
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //-- 3) Close the DB connection database and release any locked resources.
            this.databaseAccessHelper.closeDatabase();
        }
        return exercises;
    }

    /**
     * Deletes an exercise
     * @param exercise name of the exercise
     */
    public void deleteExercise(Exercise exercise)
    {
        //Opens the database
        sqLiteDatabase = this.databaseAccessHelper.openDatabase();
        try
        {
            //Delete the item at the given position
            sqLiteDatabase.delete(TABLE_EXERCISE_LIST, "userId = ?", new String[]{exercise.getId() + ""});

            Log.i("DeleteExercise", "Delete succesfull");
        }
        catch(Exception e)
        {
            Log.wtf("DeleteExercise", "Could not delete");
        }
        finally
        {
            //Releases reference to the database
            sqLiteDatabase.close();

            //Closes the database
            this.databaseAccessHelper.closeDatabase();
        }
    }

    //
    public void updatePWPExercises(Exercise exercise, String newDuration , String newLength, String newSets, String newReps) {

        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_DURATION, newDuration);
        contentValues.put(COL_DURATION_NAME, newLength);
        contentValues.put(COL_SETS, newSets);
        contentValues.put(COL_REPS, newReps);

        sqLiteDatabase = this.databaseAccessHelper.openDatabase();

        try {
            sqLiteDatabase.beginTransaction();

            sqLiteDatabase.update(TABLE_EXERCISE_LIST, contentValues, COL_USER_ID+" = ?", new String[]{exercise.getId() + ""});

            sqLiteDatabase.setTransactionSuccessful();

            Log.i("UpdateExercise", "Exercise updated successfully");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            sqLiteDatabase.endTransaction();

            this.databaseAccessHelper.closeDatabase();
        }
    }


    public void updatePlankExercise(Exercise exercise, String newDuration , String newLength ,
                                    String newSets) {

        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_DURATION, newDuration);
        contentValues.put(COL_DURATION_NAME, newLength);
        contentValues.put(COL_SETS, newSets);

        sqLiteDatabase = this.databaseAccessHelper.openDatabase();

        try {
            sqLiteDatabase.beginTransaction();

            sqLiteDatabase.update(TABLE_EXERCISE_LIST, contentValues, COL_USER_ID +"=?", new String[]{String.valueOf(exercise.getId())});

            sqLiteDatabase.setTransactionSuccessful();

            Log.i("UpdateExercise", "Exercise updated successfully");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            sqLiteDatabase.endTransaction();

            this.databaseAccessHelper.closeDatabase();
        }

    }

    public void updateRunningExercise(Exercise exercise, String newDuration, String newLength,
                                      String newDistance, String newLaps) {

        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_DURATION, newDuration);
        contentValues.put(COL_DURATION_NAME, newLength);
        contentValues.put(COL_DISTANCE, newDistance);
        contentValues.put(COL_LAPS, newLaps);

        sqLiteDatabase = this.databaseAccessHelper.openDatabase();

        try {
            sqLiteDatabase.beginTransaction();

            sqLiteDatabase.update(TABLE_EXERCISE_LIST, contentValues, COL_USER_ID +"=?", new String[]{String.valueOf(exercise.getId())});

            sqLiteDatabase.setTransactionSuccessful();

            Log.i("UpdateExercise", "Exercise updated successfully");
        }
        catch (Exception e) {
            e.printStackTrace();
            Log.i("UpdateExercise", "Could not update exercise");
        }
        finally {
            sqLiteDatabase.endTransaction();

            this.databaseAccessHelper.closeDatabase();
        }

    }

    public void updateYogaExercise(Exercise exercise, String newDuration, String newLength) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_DURATION, newDuration);
        contentValues.put(COL_DURATION_NAME, newLength);

        sqLiteDatabase = this.databaseAccessHelper.openDatabase();

        try {
            sqLiteDatabase.beginTransaction();

            sqLiteDatabase.update(TABLE_EXERCISE_LIST, contentValues, COL_USER_ID +"=?", new String[]{String.valueOf(exercise.getId())});

            sqLiteDatabase.setTransactionSuccessful();

            Log.i("UpdateExercise", "Exercise updated successfully");
        }
        catch (Exception e) {
            e.printStackTrace();
            Log.i("UpdateExercise", "Could not update exercise");
        }
        finally {
            sqLiteDatabase.endTransaction();

            this.databaseAccessHelper.closeDatabase();
        }
    }


}
