package com.lulua.tesyant.householdmanager.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.lulua.tesyant.householdmanager.models.UnfixedNeeds;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.lulua.tesyant.householdmanager.db.DatabaseContract.TABLE_UNFIXED_NEEDS;
import static com.lulua.tesyant.householdmanager.db.DatabaseContract.UnfixedNeedsColumns.UNFIXED_NAME;

/**
 * Created by HP Envy on 23/12/2017.
 */

public class UnfixedNeedsHelper {

    private Context context;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public UnfixedNeedsHelper (Context context) {
        this.context = context;
    }

    public UnfixedNeedsHelper open() throws SQLException {
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        databaseHelper.close();
    }

    public ArrayList<UnfixedNeeds> getAllData() {
        Cursor cursor = database.query(TABLE_UNFIXED_NEEDS, null, null, null,
                null, null, _ID + " ASC", null );
        cursor.moveToFirst();
        ArrayList<UnfixedNeeds> arrayList = new ArrayList<>();
        UnfixedNeeds unfixedNeeds;
        if (cursor.getCount() > 0) {
            do {
                unfixedNeeds = new UnfixedNeeds();
                unfixedNeeds.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                unfixedNeeds.setNama(cursor.getString(cursor.getColumnIndexOrThrow(UNFIXED_NAME)));

                arrayList.add(unfixedNeeds);
                cursor.moveToNext();
            }
            while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public void insertTransaction(ArrayList<UnfixedNeeds> unfixedNeeds){
        String sql = "INSERT INTO " + TABLE_UNFIXED_NEEDS
                + " ("
                + UNFIXED_NAME + ") VALUES (?);";

        database.beginTransaction();

        SQLiteStatement statement = database.compileStatement(sql);
        for (int i = 0; i < unfixedNeeds.size(); i++) {
            statement.bindString(1, unfixedNeeds.get(i).getNama());
            statement.execute();
            statement.clearBindings();
        }
        database.setTransactionSuccessful();
        database.endTransaction();
        Log.e("Insert", "Check");
    }

    public void update(UnfixedNeeds unfixedNeeds) {
        ContentValues args = new ContentValues();
        args.put(UNFIXED_NAME, unfixedNeeds.getNama());
        database.update(TABLE_UNFIXED_NEEDS, args, _ID
                + " = '" + unfixedNeeds.getId() + "'", null);
    }

    public void delete(int id) {
        database.delete(TABLE_UNFIXED_NEEDS, _ID
                + " = '" + id + "'", null);
    }
}