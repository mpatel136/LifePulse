package com.saadatdevelopment.lifepulse.searchdialognew.databasecontrollers;

import android.content.Context;

import com.saadatdevelopment.lifepulse.searchdialognew.helper.SQLiteAssetHelper;


/**
 * A helper class for accessing an existing SQLite databases.
 *
 * Created by Sleiman on 3/16/2019
 */
public class CustomDatabaseOpenHelper extends SQLiteAssetHelper {

    // You should change put your databases name instead.
    private static final String DATABASE_NAME = "lifepulsedatabase.db";
    // Keep the version number as is for now.
    private static final int DATABASE_VERSION = 1;

    public CustomDatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
