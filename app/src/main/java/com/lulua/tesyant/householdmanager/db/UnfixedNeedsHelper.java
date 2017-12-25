package com.lulua.tesyant.householdmanager.db;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.lulua.tesyant.householdmanager.models.UnfixedNeeds;

import java.util.ArrayList;


/**
 * Created by HP Envy on 23/12/2017.
 */

public class UnfixedNeedsHelper {
    private static String DATABASE_TABLE = DatabaseHelper.TABLE_NAME_KEB_TDKTETAP;
    private Context context;
    DatabaseHelper databaseHelper;

    private SQLiteDatabase database;

    public UnfixedNeedsHelper(Context context){
        this.context = context;
    }

    public UnfixedNeedsHelper open() throws SQLException{
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        databaseHelper.close();
    }

    public Cursor queryAllData() {
        return database.rawQuery("SELECT * FROM "+  DATABASE_TABLE + "ORDER BY" + DatabaseHelper
                .FIELD_ID_BARANG_TDKTETAP + " ASC ", null);
    }

    public ArrayList<UnfixedNeeds> getAllData() {
       ArrayList<UnfixedNeeds> arrayList = new ArrayList<>();
       Cursor cursor = queryAllData();
       cursor.moveToFirst();
       UnfixedNeeds unfixedNeeds;
       if(cursor.getCount() > 0) {
           do {
               unfixedNeeds =new UnfixedNeeds();
               unfixedNeeds.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.FIELD_ID_BARANG_TDKTETAP)));
               unfixedNeeds.setNama(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.FIELD_NAMA_BARANG_TDKTETAP)));
               unfixedNeeds.setTglBeli(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.FIELD_TGLBELI_TDKTETAP)));
               unfixedNeeds.setJumlah(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.FIELD_JUMLAH)));
               unfixedNeeds.setHarga(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.FIELD_HARGA)));
           }
           while (!cursor.isAfterLast());
       }

       cursor.close();
       return arrayList;
    }


    public void insertTransaction(ArrayList<UnfixedNeeds> unfixedNotes) {
        String sql = "INSERT INTO "+DATABASE_TABLE + "("
                + DatabaseHelper.FIELD_NAMA_BARANG_TDKTETAP + ","
                + DatabaseHelper.FIELD_TGLBELI_TDKTETAP + ","
                + DatabaseHelper.FIELD_JUMLAH + ","
                + DatabaseHelper.FIELD_HARGA + ") VALUES(?, ?, ?, ?);";
        database.beginTransaction();

        SQLiteStatement statement = database.compileStatement(sql);
        for (int i=0; i<unfixedNotes.size(); i++) {
            statement.bindString(1,unfixedNotes.get(i).getNama());
            statement.bindString(2, String.valueOf(unfixedNotes.get(i).getTglBeli()));
            statement.bindLong(3,unfixedNotes.get(i).getJumlah());
            statement.bindDouble(4,unfixedNotes.get(i).getHarga());
            statement.execute();
            statement.clearBindings();
        }
        database.setTransactionSuccessful();
        database.endTransaction();
    }

    public void delete(int id) {
        database.delete(DatabaseHelper.TABLE_NAME_KEB_TDKTETAP,DatabaseHelper
                .FIELD_ID_BARANG_TDKTETAP + " = " + id + "'",null);
    }
}