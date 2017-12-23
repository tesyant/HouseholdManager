package com.lulua.tesyant.householdmanager.db;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;

import com.lulua.tesyant.householdmanager.models.FixedNeeds;

/**
 * Created by tesyant on 20/12/17.
 */

public class FixedNeedsHelper {

    private final String DATABASE_TABLE = DatabaseHelper.TABLE_NAME_KEB_TETAP;
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
        return database.rawQuery("SELECT * FROM " + DATABASE_TABLE + " ORDER BY " +
                DatabaseHelper.FIELD_ID_BARANG_TETAP + " ASC", null);
    }

    public ArrayList<FixedNeeds> getAllData() {
        ArrayList<FixedNeeds> arrayList = new ArrayList<>();
        Cursor cursor = queryAllData();
        cursor.moveToFirst();
        FixedNeeds fixedNeeds;
        if (cursor.getCount() > 0) {
            do {
                fixedNeeds = new FixedNeeds();
                fixedNeeds.setName(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.FIELD_NAMA_BARANG_TETAP)));
                fixedNeeds.setHarga(cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.FIELD_HARGA)));
                fixedNeeds.setJumlah(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.FIELD_JUMLAH)));
                fixedNeeds.setTglbayar(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.FIELD_TGLBELI_TETAP)));
                arrayList.add(fixedNeeds);
                cursor.moveToNext();
            }
            while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insertTransaction(ArrayList<FixedNeeds> fixedNeeds) {
        String sql = "INSERT INTO " + DATABASE_TABLE + " ("
                + DatabaseHelper.FIELD_NAMA_BARANG_TETAP + " , "
                + DatabaseHelper.FIELD_HARGA + " , "
                + DatabaseHelper.FIELD_JUMLAH + " , "
                + DatabaseHelper.FIELD_TGLBELI_TETAP + ")"
                + " VALUES (?, ?, ?, ?);";
        database.beginTransaction();

        SQLiteStatement statement = database.compileStatement(sql);
        for (int i = 0; i < fixedNeeds.size(); i++) {
            statement.bindString(1, fixedNeeds.get(i).getName());
            statement.bindString(2, fixedNeeds.get(i).get);
        }
    }



}
