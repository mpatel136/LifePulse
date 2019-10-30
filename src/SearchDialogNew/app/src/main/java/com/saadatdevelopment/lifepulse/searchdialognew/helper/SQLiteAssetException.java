package com.saadatdevelopment.lifepulse.searchdialognew.helper;

import android.database.sqlite.SQLiteException;

/**
 * An exception that indicates there was an error with SQLite asset retrieval or parsing.
 */
public  class SQLiteAssetException extends SQLiteException {

    public SQLiteAssetException() {}

    public SQLiteAssetException(String error) {
        super(error);
    }
}