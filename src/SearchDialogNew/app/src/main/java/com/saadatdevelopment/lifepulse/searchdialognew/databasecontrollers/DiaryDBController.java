package com.saadatdevelopment.lifepulse.searchdialognew.databasecontrollers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.lifepulselibrary.Diary;
import com.example.lifepulselibrary.Exercise;
import com.saadatdevelopment.lifepulse.searchdialognew.databasehelper.DBAccessController;

import java.util.ArrayList;
import java.util.List;

public class DiaryDBController {

    private DBAccessController databaseAccessHelper;
    private SQLiteDatabase sqLiteDatabase;
    private static final String TAG = DatabaseController.class.getSimpleName();
    private static final String TABLE_DIARY = "diary";

    private static final String COL_DIARY_ID = "id";
    private static final String COL_NAME = "name";
    private static final String COL_GUILT_LEVEL = "guilt_level";
    private static final String COL_DATE = "date";
    private static final String COL_DESCRIPTION= "description";
    private static final String COL_IMAGE = "image";

    /**
     *  Creates a controller for controlling the interactions between a view and the contact model.
     *
     * @param context The application context that must be passed from an android view.
     */
    public DiaryDBController(Context context) {
        this.databaseAccessHelper = DBAccessController.getInstance(context);
    }

    /**
     * Allows the user to create a new bird of their choice
     * @param diary Diary object
     */
    public void createDiary(Diary diary) {

        sqLiteDatabase = this.databaseAccessHelper.openDatabase();
        sqLiteDatabase.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(COL_NAME, diary.getName());
            values.put(COL_GUILT_LEVEL, diary.getGuiltLevel());
            values.put(COL_DATE, diary.getDate());
            values.put(COL_IMAGE, diary.getImage());
            values.put(COL_DESCRIPTION, diary.getDescription());


            sqLiteDatabase.insert(TABLE_DIARY, TAG, values);
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
     * Reads all diaries from the database.
     *
     * @return a List of contacts
     */
    public List<Diary> getAllDiaries() {
        List<Diary> diaries = diaries = new ArrayList<>();
        //-- 1) Open the database.
        sqLiteDatabase = this.databaseAccessHelper.openDatabase();
        try {
            // 2) Query the database.
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_DIARY, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Diary diary = new Diary();
                diary.setId(cursor.getInt(cursor.getColumnIndex(COL_DIARY_ID)));
                diary.setName(cursor.getString(cursor.getColumnIndex(COL_NAME)));
                diary.setDate(cursor.getString(cursor.getColumnIndex(COL_DATE)));
                diary.setDescription(cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION)));
                diary.setGuiltLevel(cursor.getString(cursor.getColumnIndex(COL_GUILT_LEVEL)));
                diary.setImage(cursor.getBlob(cursor.getColumnIndex(COL_IMAGE)));
                diaries.add(diary);
                cursor.moveToNext();
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //-- 3) Close the DB connection database and release any locked resources.
            this.databaseAccessHelper.closeDatabase();
        }
        return diaries;
    }

}
