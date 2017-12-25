package com.lulua.tesyant.householdmanager.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.provider.BaseColumns._ID;
import static com.lulua.tesyant.householdmanager.db.DatabaseContract.FixedNeedsColumns.FIXED_NAME;
import static com.lulua.tesyant.householdmanager.db.DatabaseContract.TABLE_FIXED_NEEDS;
import static com.lulua.tesyant.householdmanager.db.DatabaseContract.TABLE_UNFIXED_NEEDS;
import static com.lulua.tesyant.householdmanager.db.DatabaseContract.UnfixedNeedsColumns.UNFIXED_NAME;

/**
 * Created by tesyant on 20/12/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    public static String DATABASE_NAME = "dbNeeds";

    private static String CREATE_TABLE_FIXED_NEEDS = "CREATE TABLE " + TABLE_FIXED_NEEDS
            + " ("
            + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + FIXED_NAME + " TEXT NOT NULL);";

    private static final String CREATE_TABLE_UNFIXED_NEEDS = "CREATE TABLE " + TABLE_UNFIXED_NEEDS
            + " ("
            + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + UNFIXED_NAME + " TEXT NOT NULL);";

    public DatabaseHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_FIXED_NEEDS);
        Log.d("check", "db");
        db.execSQL(CREATE_TABLE_UNFIXED_NEEDS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FIXED_NEEDS);
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_UNFIXED_NEEDS);
        onCreate(db);
    }
}
