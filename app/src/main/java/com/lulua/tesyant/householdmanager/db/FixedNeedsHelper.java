package com.lulua.tesyant.householdmanager.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.lulua.tesyant.householdmanager.models.FixedNeeds;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.lulua.tesyant.householdmanager.db.DatabaseContract.FixedNeedsColumns.FIXED_NAME;
import static com.lulua.tesyant.householdmanager.db.DatabaseContract.TABLE_FIXED_NEEDS;


/**
 * Created by tesyant on 20/12/17.
 */

public class FixedNeedsHelper {

    private Context context;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public FixedNeedsHelper (Context context) {
        this.context = context;
    }

    public FixedNeedsHelper open() throws SQLException {
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        databaseHelper.close();
    }

    public ArrayList<FixedNeeds> getAllData() {
        Cursor cursor = database.query(TABLE_FIXED_NEEDS, null, null, null,
                null, null, _ID + " ASC", null );
        cursor.moveToFirst();
        ArrayList<FixedNeeds> arrayList = new ArrayList<>();
        FixedNeeds fixedNeeds;
        if (cursor.getCount() > 0) {
            do {
                fixedNeeds = new FixedNeeds();
                fixedNeeds.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                fixedNeeds.setNama(cursor.getString(cursor.getColumnIndexOrThrow(FIXED_NAME)));

                arrayList.add(fixedNeeds);
                cursor.moveToNext();
            }
            while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public void insertTransaction(ArrayList<FixedNeeds> fixedNeeds){
        String sql = "INSERT INTO " + TABLE_FIXED_NEEDS
                + " ("
                + FIXED_NAME + ") VALUES (?);";

        database.beginTransaction();

        SQLiteStatement statement = database.compileStatement(sql);
        for (int i = 0; i < fixedNeeds.size(); i++) {
            statement.bindString(1, fixedNeeds.get(i).getNama());
            statement.execute();
            statement.clearBindings();
        }
        database.setTransactionSuccessful();
        database.endTransaction();
        Log.e("Insert", "Check");
    }

    public void update(FixedNeeds fixedNeeds) {
        ContentValues args = new ContentValues();
        args.put(FIXED_NAME, fixedNeeds.getNama());
        database.update(TABLE_FIXED_NEEDS, args, _ID
                + " = '" + fixedNeeds.getId() + "'", null);
    }

    public void delete(int id) {
        database.delete(TABLE_FIXED_NEEDS, _ID
                + " = '" + id + "'", null);
    }
}
