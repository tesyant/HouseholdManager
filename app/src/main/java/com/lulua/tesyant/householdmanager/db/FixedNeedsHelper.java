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

import static android.content.ContentValues.TAG;
import static android.provider.BaseColumns._ID;
import static com.lulua.tesyant.householdmanager.db.DatabaseContract.TABLE_FIXED_NEEDS;
import static com.lulua.tesyant.householdmanager.db.DatabaseContract.FixedNeedsColumns.FIXED_DATE;
import static com.lulua.tesyant.householdmanager.db.DatabaseContract.FixedNeedsColumns.FIXED_NAME;
import static com.lulua.tesyant.householdmanager.db.DatabaseContract.FixedNeedsColumns.FIXED_PRICE;
import static com.lulua.tesyant.householdmanager.db.DatabaseContract.FixedNeedsColumns.FIXED_QUANTITY;


/**
 * Created by tesyant on 20/12/17.
 */

public class FixedNeedsHelper {

    private static String DATABASE_TABLE = TABLE_FIXED_NEEDS;
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

    public Cursor queryAllData() {
        return database.rawQuery("SELECT * FROM " + DATABASE_TABLE + " ORDER BY " + _ID
                + " ASC", null);
    }

    public ArrayList<FixedNeeds> getAllData() {
        ArrayList<FixedNeeds> arrayList = new ArrayList<>();
        Cursor cursor = queryAllData();
        cursor.moveToFirst();
        FixedNeeds fixedNeeds;
        if (cursor.getCount()>0) {
            do {
                fixedNeeds = new FixedNeeds();
                fixedNeeds.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                fixedNeeds.setNama(cursor.getString(cursor.getColumnIndexOrThrow(FIXED_NAME)));
                fixedNeeds.setHarga(cursor.getDouble(cursor.getColumnIndexOrThrow(FIXED_PRICE)));
                fixedNeeds.setJumlah(cursor.getInt(cursor.getColumnIndexOrThrow(FIXED_QUANTITY)));
                fixedNeeds.setTanggalBayar(cursor.getString(cursor.getColumnIndexOrThrow(FIXED_DATE)));

                arrayList.add(fixedNeeds);
                cursor.moveToNext();
            }
            while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public void insertTransaction(ArrayList<FixedNeeds> fixedNeeds){
        String sql = "INSERT INTO " + DATABASE_TABLE + " ("
                + FIXED_NAME + ", "
                + FIXED_PRICE + ", "
                + FIXED_QUANTITY + ", "
                + FIXED_DATE + ") VALUES (?, ?, ?, ?)";
        database.beginTransaction();

        SQLiteStatement statement = database.compileStatement(sql);
        for (int i = 0; i < fixedNeeds.size(); i++) {
            statement.bindString(1, fixedNeeds.get(i).getNama());
            statement.bindDouble(2, fixedNeeds.get(i).getHarga());
            statement.bindString(3, String.valueOf(fixedNeeds.get(i).getJumlah()));
            statement.bindString(4, fixedNeeds.get(i).getTanggalBayar());
            statement.execute();
            statement.clearBindings();
        }
        database.setTransactionSuccessful();
        database.endTransaction();
        Log.e(TAG, "Exception");
    }

    public int update(FixedNeeds fixedNeeds) {
        ContentValues args = new ContentValues();
        args.put(FIXED_NAME, fixedNeeds.getNama());
        args.put(FIXED_PRICE, fixedNeeds.getHarga());
        args.put(FIXED_QUANTITY, fixedNeeds.getJumlah());
        args.put(FIXED_DATE, fixedNeeds.getTanggalBayar());
        return database.update(TABLE_FIXED_NEEDS, args, DatabaseContract.FixedNeedsColumns._ID
                + "= '" + fixedNeeds.getId() + "'", null);
    }

    public int delete(int id) {
        return database.delete(TABLE_FIXED_NEEDS, DatabaseContract.FixedNeedsColumns._ID
                + " = '" + id + "'", null);
    }
}
