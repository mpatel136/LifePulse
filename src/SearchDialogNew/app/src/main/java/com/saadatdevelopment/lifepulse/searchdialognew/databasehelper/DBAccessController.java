package com.saadatdevelopment.lifepulse.searchdialognew.databasehelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.saadatdevelopment.lifepulse.searchdialognew.databasecontrollers.CustomDatabaseOpenHelper;



/**
 *  A helper class for querying an SQLite databases.
 *
 */
public class DBAccessController {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DBAccessController instance;

    /**
     * Private constructor to avoid object creation from outside this class.
     * We want to make sure there is only one signle instance of this class accessing the dabase.
     * @param context
     */
    private DBAccessController(Context context) {
        this.openHelper = new CustomDatabaseOpenHelper(context);
    }

    /**
     * Creates and returns  a singleton instance of DBAccessController.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static synchronized DBAccessController getInstance(Context context) {
        if (instance == null) {
            instance = new DBAccessController(context);
        }
        return instance;
    }

    /**
     * Opens a connection to the specified databases.
     */
    public SQLiteDatabase openDatabase() {

        this.database = openHelper.getWritableDatabase();
        return this.database;
    }

    /**
     * Closes the databases connection.
     */
    public void closeDatabase() {
        if (database != null) {
            this.database.close();
        }
    }

}
