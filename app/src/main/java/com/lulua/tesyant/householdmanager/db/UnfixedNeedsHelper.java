package com.lulua.tesyant.householdmanager.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.lulua.tesyant.householdmanager.models.UnfixedNeeds;

import java.util.ArrayList;

import static com.lulua.tesyant.householdmanager.db.DatabaseContract.FixedNeedsColumns.FIXED_DATE;
import static com.lulua.tesyant.householdmanager.db.DatabaseContract.FixedNeedsColumns.FIXED_NAME;
import static com.lulua.tesyant.householdmanager.db.DatabaseContract.FixedNeedsColumns.FIXED_PRICE;
import static com.lulua.tesyant.householdmanager.db.DatabaseContract.FixedNeedsColumns.FIXED_QUANTITY;
import static com.lulua.tesyant.householdmanager.db.DatabaseContract.TABLE_UNFIXED_NEEDS;
import static com.lulua.tesyant.householdmanager.db.DatabaseContract.UnfixedNeedsColumns.UNFIXED_DATE;
import static com.lulua.tesyant.householdmanager.db.DatabaseContract.UnfixedNeedsColumns.UNFIXED_NAME;
import static com.lulua.tesyant.householdmanager.db.DatabaseContract.UnfixedNeedsColumns.UNFIXED_PRICE;
import static com.lulua.tesyant.householdmanager.db.DatabaseContract.UnfixedNeedsColumns.UNFIXED_QUANTITY;


/**
 * Created by HP Envy on 23/12/2017.
 */

public class UnfixedNeedsHelper {
    private static String DATABASE_TABLE = TABLE_UNFIXED_NEEDS;
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

    public ArrayList<UnfixedNeeds> query() {
        ArrayList<UnfixedNeeds> arrayList = new ArrayList<UnfixedNeeds>();
        Cursor cursor = database.query(DATABASE_TABLE, null, null, null,
                null, null, DatabaseContract.UnfixedNeedsColumns._ID + " DESC", null);
        cursor.moveToFirst();
        UnfixedNeeds unfixedNeeds;
        if (cursor.getCount()>0) {
            do {
                unfixedNeeds = new UnfixedNeeds();
                unfixedNeeds.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.UnfixedNeedsColumns._ID)));
                unfixedNeeds.setNama(cursor.getString(cursor.getColumnIndexOrThrow(UNFIXED_NAME)));
                unfixedNeeds.setHarga(cursor.getDouble(cursor.getColumnIndexOrThrow(UNFIXED_PRICE)));
                unfixedNeeds.setJumlah(cursor.getInt(cursor.getColumnIndexOrThrow(UNFIXED_QUANTITY)));
                unfixedNeeds.setTglBeli(cursor.getString(cursor.getColumnIndexOrThrow(UNFIXED_DATE)));

                arrayList.add(unfixedNeeds);
                cursor.moveToNext();
            }
            while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public void insertTransaction(ArrayList<UnfixedNeeds> unfixedNeeds){
        String sql = "INSERT INTO " + DATABASE_TABLE + " ("
                + UNFIXED_NAME + ", "
                + UNFIXED_PRICE + ", "
                + UNFIXED_QUANTITY + ", "
                + UNFIXED_DATE + ") VALUES (?, ?, ?, ?);";
        database.beginTransaction();

        SQLiteStatement statement=database.compileStatement(sql);
        for(int i=0;i<unfixedNeeds.size();i++){
            statement.bindString(1, unfixedNeeds.get(i).getNama());
            statement.bindString(2, String.valueOf(unfixedNeeds.get(i).getTglBeli()));
            statement.bindLong(4,unfixedNeeds.get(i).getJumlah());
            statement.bindDouble(4,unfixedNeeds.get(i).getHarga());
            statement.execute();
            statement.clearBindings();
        }
        database.setTransactionSuccessful();
        database.endTransaction();
    }

    public int update(UnfixedNeeds unfixedNeeds) {
        ContentValues args = new ContentValues();
        args.put(FIXED_NAME, unfixedNeeds.getNama());
        args.put(FIXED_PRICE, unfixedNeeds.getHarga());
        args.put(FIXED_QUANTITY, unfixedNeeds.getJumlah());
        args.put(FIXED_DATE, unfixedNeeds.getTglBeli());
        return database.update(DATABASE_TABLE, args, DatabaseContract.UnfixedNeedsColumns._ID
                + "= '" + unfixedNeeds.getId() + "'",null);
    }
}