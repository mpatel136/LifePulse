package com.saadatdevelopment.lifepulse.searchdialognew.databasehelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 *
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper {
    // The name of the databases's file that is going to be created and stored on the device after
    // deploying the application.
    private static final String DATABASE_NAME = "bird_info.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;
    /**
     *
     * @param context
     */
    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Creates a new table "artist" in the bird_info.db
     * @param db
     */

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlUsers = "CREATE TABLE users(id INTEGER PRIMARY KEY AUTOINCREMENT, firstName VARCHAR, lastName VARCHAR, favBird VARCHAR)";

        db.execSQL(sqlUsers);
        this.db = getWritableDatabase();
    }


    /**
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sqlUsers = "DROP TABLE IF EXISTS users";

        db.execSQL(sqlUsers);

        onCreate(db);
    }
}

