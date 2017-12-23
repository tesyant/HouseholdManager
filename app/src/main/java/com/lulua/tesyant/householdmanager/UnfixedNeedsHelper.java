package db;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.lulua.tesyant.householdmanager.db.DatabaseHelper;
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
}
