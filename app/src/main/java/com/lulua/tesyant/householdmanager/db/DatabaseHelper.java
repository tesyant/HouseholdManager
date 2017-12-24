package com.lulua.tesyant.householdmanager.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tesyant on 20/12/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static String DB_NAME = "dbNeeds";

    private static final String SQL_CREATE_TABLE_FIXED_NEEDS = String.format (
            "CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + " %s TEXT NOT NULL,"
                    + " %s TEXT NOT NULL,"
                    + " %s TEXT NOT NULL,"
                    + " %s TEXT NOT NULL)",

            DatabaseContract.FixedNeedsColumns._ID,
            DatabaseContract.FixedNeedsColumns.FIXED_NAME,
            DatabaseContract.FixedNeedsColumns.FIXED_QUANTITY,
            DatabaseContract.FixedNeedsColumns.FIXED_PRICE,
            DatabaseContract.FixedNeedsColumns.FIXED_DATE
    );

    private static final String SQL_CREATE_TABLE_UNFIXED_NEEDS = String.format (
            "CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + " %s TEXT NOT NULL,"
                    + " %s TEXT NOT NULL,"
                    + " %s TEXT NOT NULL,"
                    + " %s TEXT NOT NULL)",

            DatabaseContract.UnfixedNeedsColumns._ID,
            DatabaseContract.UnfixedNeedsColumns.UNFIXED_NAME,
            DatabaseContract.UnfixedNeedsColumns.UNFIXED_QUANTITY,
            DatabaseContract.UnfixedNeedsColumns.UNFIXED_PRICE,
            DatabaseContract.UnfixedNeedsColumns.UNFIXED_DATE
    );

    public DatabaseHelper (Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_FIXED_NEEDS);
        db.execSQL(SQL_CREATE_TABLE_UNFIXED_NEEDS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_FIXED_NEEDS);
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_UNFIXED_NEEDS);
        onCreate(db);
    }
}
