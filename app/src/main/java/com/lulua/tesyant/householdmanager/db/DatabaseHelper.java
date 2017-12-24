package com.lulua.tesyant.householdmanager.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tesyant on 20/12/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static String DB_NAME = "dbKebutuhan";

    public static String TABLE_NAME_KEB_TETAP = "FixedNeedsActivity";
    public static String TABLE_NAME_KEB_TDKTETAP = "UnfixedNeedsActivity";

    public static String FIELD_ID_BARANG_TETAP = "IdBarang";
    public static String FIELD_NAMA_BARANG_TETAP = "NamaBarang";
    public static String FIELD_JANGKA_TETAP = "Jangka";
    public static String FIELD_TGLBELI_TETAP = "TglBeli";

    public static String FIELD_ID_BARANG_TDKTETAP = "IdBarangTdkTetap";
    public static String FIELD_NAMA_BARANG_TDKTETAP = "NamaBarangTdkTetap";
    public static String FIELD_TGLBELI_TDKTETAP = "TglBeliTdkTetap";

    public static String FIELD_JUMLAH = "Jumlah";
    public static String FIELD_HARGA = "Harga";


    public static String CREATE_TABLE_BARANG_TETAP = "CREATE TABLE " + TABLE_NAME_KEB_TETAP + " ( "
            + FIELD_ID_BARANG_TETAP + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + FIELD_NAMA_BARANG_TETAP + " TEXT, "
            + FIELD_JANGKA_TETAP + " INT, "
            + FIELD_TGLBELI_TETAP + " DAT, "
            + FIELD_JUMLAH + " INT, "
            + FIELD_HARGA + " DOUBLE"
            + " );";

    public static String CREATE_TABLE_BARANG_TDKTETAP = "CREATE TABLE " + TABLE_NAME_KEB_TDKTETAP + " ( "
            + FIELD_ID_BARANG_TDKTETAP + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + FIELD_NAMA_BARANG_TDKTETAP + " TEXT, "
            + FIELD_TGLBELI_TDKTETAP + " DATE"
            + FIELD_JUMLAH + " INT, "
            + FIELD_HARGA + " DOUBLE"
            + " );";


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_BARANG_TETAP);
        db.execSQL(CREATE_TABLE_BARANG_TDKTETAP);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_KEB_TETAP);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_KEB_TDKTETAP);
        onCreate(db);
    }
}
