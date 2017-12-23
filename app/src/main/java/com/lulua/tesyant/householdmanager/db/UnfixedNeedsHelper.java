package com.lulua.tesyant.householdmanager.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.lulua.tesyant.householdmanager.models.UnfixedNotes;

import java.util.ArrayList;


/**
 * Created by HP Envy on 23/12/2017.
 */

public class UnfixedNeedsHelper {
    private static String DATABASE_TABLE= DatabaseHelper.TABLE_NAME_KEB_TDKTETAP;
    private Context context;
    DatabaseHelper databaseHelper;

    private SQLiteDatabase database;

    public UnfixedNeedsHelper(Context context){
        this.context=context;
    }

    public UnfixedNeedsHelper open() throws SQLException{
        databaseHelper=new DatabaseHelper(context);
        database=databaseHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        databaseHelper.close();
    }

    public Cursor queryAllData(){
        return database.rawQuery("SELECT * FROM "+DATABASE_TABLE+"ORDER BY"+DatabaseHelper.FIELD_ID_BARANG_TDKTETAP+" DESC ", null);
    }
    public ArrayList<UnfixedNotes> getAllData(){
       ArrayList<UnfixedNotes> arrayList=new ArrayList<>();
       Cursor cursor=queryAllData();
       cursor.moveToFirst();
       UnfixedNotes unfixedNotes;
       if(cursor.getCount()>0){
           do{
               unfixedNotes=new UnfixedNotes();
               unfixedNotes.setNama(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.FIELD_NAMA_BARANG_TDKTETAP)));
               unfixedNotes.setTglBeli(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.FIELD_TGLBELI_TDKTETAP)));
               unfixedNotes.setJumlah(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.FIELD_JUMLAH)));
               unfixedNotes.setHarga(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.FIELD_HARGA)));
           }
           while(!cursor.isAfterLast());
       }
       cursor.close();
       return arrayList;
    }


    public void insertTransaction(ArrayList<UnfixedNotes> unfixedNotes){
        String sql="INSERT INTO "+DATABASE_TABLE+"("
                +DatabaseHelper.FIELD_NAMA_BARANG_TDKTETAP+","
                +DatabaseHelper.FIELD_TGLBELI_TDKTETAP+","
                +DatabaseHelper.FIELD_JUMLAH+","
                +DatabaseHelper.FIELD_HARGA+") VALUES(?,?);";
        database.beginTransaction();

        SQLiteStatement statement=database.compileStatement(sql);
        for(int i=0;i<unfixedNotes.size();i++){
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
    public void delete(int id){
        database.delete(DatabaseHelper.TABLE_NAME_KEB_TDKTETAP,DatabaseHelper.FIELD_ID_BARANG_TDKTETAP+" = "+id+"'",null);
    }
}
