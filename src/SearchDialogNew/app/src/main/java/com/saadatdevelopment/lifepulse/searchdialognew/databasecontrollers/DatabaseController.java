package com.saadatdevelopment.lifepulse.searchdialognew.databasecontrollers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.lifepulselibrary.Food;
import com.example.lifepulselibrary.UserProfile;
import com.saadatdevelopment.lifepulse.searchdialognew.databasehelper.DBAccessController;

import java.util.ArrayList;
import java.util.List;



/**
 *  A class for controlling interactions between an Android View (such as an activity/fragment),
 *  a model (that is, contact model) and an SQLite databases.
 *
 *
 */
public class DatabaseController {
    private DBAccessController databaseAccessHelper;
    private SQLiteDatabase sqLiteDatabase;
    private String DB_TABLE_NAME = "recipes";
    private String COL_IMAGE = "image";
    private static final String TAG = DatabaseController.class.getSimpleName();
    private static final String TABLE_USER_PROFILE = "userProfile";
    private static final String TABLE_EXERCISE_LIST = "exerciseList";

    private static final String COL_ID = "id";
    private static final String COL_FULLNAME = "fullName";
    private static final String COL_GENDER = "gender";
    private static final String COL_DOB = "dateOfBirth";
    private static final String COL_HEIGHT = "height";
    private static final String COL_WEIGHT = "weight";
    private static final String COL_ACTIVITY_LEVEL = "activityLevel";
    private static final String COL_PROFILE_PIC = "profilePicture";

    /**
     *  Creates a controller for controlling the interactions between a view and the contact model.
     *
     * @param context The application context that must be passed from an android view.
     */
    public DatabaseController(Context context) {
        this.databaseAccessHelper = DBAccessController.getInstance(context);
    }

    /**
     * Creates the user profile in the databases
     * @param user is the user profile
     */
    public void createUser(UserProfile user) {

        sqLiteDatabase = this.databaseAccessHelper.openDatabase();
        sqLiteDatabase.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(COL_FULLNAME, user.getFullName());
            values.put(COL_GENDER, user.getGender());
            values.put(COL_WEIGHT, user.getWeight());
            values.put(COL_HEIGHT, user.getHeight());
            values.put(COL_ACTIVITY_LEVEL, user.getActivityLevel());
            values.put(COL_PROFILE_PIC, user.getProfilePicture());
            values.put(COL_DOB, user.getDob());

            sqLiteDatabase.insert(TABLE_USER_PROFILE, TAG, values);
            sqLiteDatabase.setTransactionSuccessful();
        }
        catch (Exception e) {
            Log.w(TAG, e.fillInStackTrace());
        }

        finally {
            sqLiteDatabase.endTransaction();
            this.databaseAccessHelper.closeDatabase();
        }
    }


    /**
     *  Removes the user profile from the databases
     * @param fullName Full name of the user
     * @param dob  date of birth of the user
     */
    public void removeProfile(String fullName, String dob) {
        //-- 1) Open the databases.
        sqLiteDatabase = this.databaseAccessHelper.openDatabase();
        try {
            sqLiteDatabase.beginTransaction();
            sqLiteDatabase.delete(TABLE_USER_PROFILE, COL_FULLNAME +" = ? AND " + COL_DOB + " = ?", new String[]{fullName, dob});
            sqLiteDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            Log.wtf(TAG, e.fillInStackTrace());
        } finally {
            sqLiteDatabase.close();
            sqLiteDatabase.endTransaction();
            this.databaseAccessHelper.closeDatabase();
        }
    }

    /**
     * Update the user profile
     * @param height height of the user
     * @param weight weight of the user
     * @param activityLevel activity level of the user
     */
    public void updateUserProfile(String fullName, String height, String weight, String activityLevel) {
        ContentValues contentValues = new ContentValues();

        // position of the value
        contentValues.put(COL_HEIGHT, Double.parseDouble(height));
        contentValues.put(COL_WEIGHT, Double.parseDouble(weight));
        contentValues.put(COL_ACTIVITY_LEVEL, activityLevel);

        sqLiteDatabase = this.databaseAccessHelper.openDatabase();

        try {
            sqLiteDatabase.beginTransaction();

            // update the db
            sqLiteDatabase.update(TABLE_USER_PROFILE, contentValues, COL_FULLNAME+"=? ", new String[]{fullName});

            sqLiteDatabase.setTransactionSuccessful();

            Log.i("updateProfile", "Updated");

        }
        catch (Exception e) {
            Log.wtf("updateProfile", "Could not update");

        }
        finally {
            sqLiteDatabase.endTransaction();
            // close the db
            this.databaseAccessHelper.closeDatabase();
        }
    }

    /**
     * Reads all users from the databases.
     *
     * @return a List of users
     */
    public List<UserProfile> getUsers() {
        List<UserProfile> users = new ArrayList<>();
        //-- 1) Open the databases.
        sqLiteDatabase = this.databaseAccessHelper.openDatabase();
        try {
            // 2) Query the databases.
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_USER_PROFILE, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                UserProfile user = new UserProfile();

                user.setFullName(cursor.getString(cursor.getColumnIndex(COL_FULLNAME)));
                user.setGender(cursor.getString(cursor.getColumnIndex(COL_GENDER)));
                user.setActivityLevel(cursor.getString(cursor.getColumnIndex(COL_ACTIVITY_LEVEL)));
                user.setDob(cursor.getString(cursor.getColumnIndex(COL_DOB)));
                user.setHeight(cursor.getInt(cursor.getColumnIndex(COL_HEIGHT)));
                user.setWeight(cursor.getInt(cursor.getColumnIndex(COL_WEIGHT)));
                user.setProfilePicture(cursor.getBlob(cursor.getColumnIndex(COL_PROFILE_PIC)));
                users.add(user);
                cursor.moveToNext();

                Log.i("getProfile", "successful");
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //-- 3) Close the DB connection databases and release any locked resources.
            this.databaseAccessHelper.closeDatabase();
        }
        return users;
    }

    /**
     * Gets all foods from the database
     * @return All food names, descriptions, and pictures
     */
    public List<Food> getAllFoods() {
        List<Food> foods = foods = new ArrayList<>();
        //-- 1) Open the database.
        sqLiteDatabase = this.databaseAccessHelper.openDatabase();
        try {
            // 2) Query the database.
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DB_TABLE_NAME, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Food food = new Food();
                food.setId(cursor.getInt(cursor.getColumnIndex(COL_ID)));
                food.setName(cursor.getString(cursor.getColumnIndex("recipeName")));
                food.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                food.setImage(cursor.getBlob(cursor.getColumnIndex("image")));
                foods.add(food);
                cursor.moveToNext();
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //-- 3) Close the DB connection database and release any locked resources.
            this.databaseAccessHelper.closeDatabase();
        }
        return foods;
    }

    /**
     * Gets the username
     * @return username of the current user
     */

    public String getUsername() {
        String username = "";

        sqLiteDatabase = this.databaseAccessHelper.openDatabase();
        try {
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT fullName FROM " + TABLE_USER_PROFILE + " ORDER BY ROWID LIMIT 1", null);
            cursor.moveToFirst();

            username = cursor.getString(cursor.getColumnIndex(COL_FULLNAME));


            cursor.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            Log.i("Test", "cant load");
        }
        finally {
            //-- 3) Close the DB connection database and release any locked resources.
            this.databaseAccessHelper.closeDatabase();
        }

        return username;
    }
    /**
     * Gets the date of birth of the user
     * @param fullName full  name of the user
     * @return full name of the user
     */
    public String getDob(String fullName) {

        String dob = "";

        //-- 1) Open the databases.
        sqLiteDatabase = this.databaseAccessHelper.openDatabase();
        try {
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT dateOfBirth FROM " + TABLE_USER_PROFILE + " ORDER BY ROWID LIMIT 1", null);
            cursor.moveToFirst();

            dob = cursor.getString(cursor.getColumnIndex(COL_DOB));

            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("Test", "cant load dob");
        } finally {
            //-- 3) Close the DB connection databases and release any locked resources.
            this.databaseAccessHelper.closeDatabase();
        }
        return dob;
    }

    /**
     * Gets the gender of the user
     * @param fullName full name of the user
     * @return gender of the user
     */
    public String getGender(String fullName) {

        String gender = "";

        //-- 1) Open the databases.
        sqLiteDatabase = this.databaseAccessHelper.openDatabase();
        try {
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT gender FROM " + TABLE_USER_PROFILE + " ORDER BY ROWID LIMIT 1", null);
            cursor.moveToFirst();

            gender = cursor.getString(cursor.getColumnIndex(COL_GENDER));

            cursor.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //-- 3) Close the DB connection databases and release any locked resources.
            this.databaseAccessHelper.closeDatabase();
        }
        return gender;
    }

    /**
     * Gets the height of the user
     * @param fullName fullname of the user
     * @return height of the user
     */
    public String getHeight(String fullName) {

        String height = "";

        //-- 1) Open the databases.
        sqLiteDatabase = this.databaseAccessHelper.openDatabase();
        try {
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT height FROM " + TABLE_USER_PROFILE + " ORDER BY ROWID LIMIT 1", null);
            cursor.moveToFirst();

            height = cursor.getString(cursor.getColumnIndex(COL_HEIGHT));

            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //-- 3) Close the DB connection databases and release any locked resources.
            this.databaseAccessHelper.closeDatabase();
        }
        return height;
    }

    /**
     * Gets the weight of the user
     * @param fullName fullname of the user
     * @return weight of the user
     */
    public String getWeight(String fullName) {

        String weight = "";

        //-- 1) Open the databases.
        sqLiteDatabase = this.databaseAccessHelper.openDatabase();
        try {
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT weight FROM " + TABLE_USER_PROFILE + " ORDER BY ROWID LIMIT 1", null);
            cursor.moveToFirst();

            weight = cursor.getString(cursor.getColumnIndex(COL_WEIGHT));

            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //-- 3) Close the DB connection databases and release any locked resources.
            this.databaseAccessHelper.closeDatabase();
        }
        return weight;
    }

    /**
     * Gets the activity level of the user
     * @param fullName fullname of the user
     * @return activity level of the user
     */
    public String getActivityLevel(String fullName) {

        String activityLevel = "";

        //-- 1) Open the databases.
        sqLiteDatabase = this.databaseAccessHelper.openDatabase();
        try {
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT activityLevel FROM " + TABLE_USER_PROFILE + " ORDER BY ROWID LIMIT 1", null);
            cursor.moveToFirst();

            activityLevel = cursor.getString(cursor.getColumnIndex(COL_ACTIVITY_LEVEL));

            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //-- 3) Close the DB connection databases and release any locked resources.
            this.databaseAccessHelper.closeDatabase();
        }
        return activityLevel;
    }

    /**
     * Reads the BLOB data as byte[] images
     *
     * @param username
     * @return image as byte[]
     */
    public byte[] getProfilePicture(String username) {
        byte[] data = null;
        Cursor cursor = null;
        //-- 1) Open the database.
        sqLiteDatabase = this.databaseAccessHelper.openDatabase();
        try {
            cursor = sqLiteDatabase.rawQuery("SELECT profilePicture FROM " + TABLE_USER_PROFILE + " WHERE fullName = ?", new String[]{username});
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                data = cursor.getBlob(cursor.getColumnIndex(COL_PROFILE_PIC));
                break;  // Assumption: name is unique
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //-- 3) closeDatabase the database and release any locked resources.
            this.databaseAccessHelper.closeDatabase();
            if (cursor != null) {
                cursor.close();
            }
        }
        return data;
    }

    /**
     * Updates the user
     * @param user user object
     * @param oldUsername old user name
     */

    public void updateUser(UserProfile user, String oldUsername) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_FULLNAME, user.getFullName());
        contentValues.put(COL_DOB, user.getDob());
        contentValues.put(COL_HEIGHT, user.getHeight());
        contentValues.put(COL_WEIGHT, user.getWeight());
        contentValues.put(COL_GENDER, user.getGender());
        contentValues.put(COL_ACTIVITY_LEVEL, user.getActivityLevel());
        contentValues.put(COL_PROFILE_PIC, user.getProfilePicture());

        sqLiteDatabase = this.databaseAccessHelper.openDatabase();
        try {

            sqLiteDatabase.beginTransaction();

            // updates the user table
            String condition = "WHERE ROWID = 1";
            sqLiteDatabase.update(TABLE_USER_PROFILE, contentValues, COL_FULLNAME+"=? ", new String[]{oldUsername});

            sqLiteDatabase.setTransactionSuccessful();
            Log.i("Test", "User updated successfully");
        }
        catch (Exception e) {
            e.printStackTrace();
            Log.i("Test", "Unable to update ");
        }
        finally {
            sqLiteDatabase.endTransaction();
            this.databaseAccessHelper.closeDatabase();
        }
    }

}
