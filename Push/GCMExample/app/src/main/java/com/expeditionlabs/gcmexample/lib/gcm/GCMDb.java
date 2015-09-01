package com.expeditionlabs.gcmexample.lib.gcm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.expeditionlabs.gcmexample.lib.C;

import java.util.ArrayList;

/**
 * Created by Weaver on 15-08-30.
 */
public class GCMDb extends SQLiteOpenHelper {
    public static final String TAG = "WEAVER_GCM_GCMDb_";
    public GCMDb(Context context){
        super(context, C.db.local.DB_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + C.db.local.TABLE + " " +
                        "(" +
                        C.db.local.column.KEY + " integer primary key, " +
                        C.db.local.column.PUSH_CODE + " text," +
                        C.db.local.column.PUSH_MESSAGE + " text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }
    public void insert(String pushCode, String pushMessage){
        Log.v(TAG, "Inserting...");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues tableRow = new ContentValues();
        tableRow.put(C.db.local.column.PUSH_CODE, pushCode);
        tableRow.put(C.db.local.column.PUSH_MESSAGE, pushMessage);
        db.insert(C.db.local.TABLE, null, tableRow);
    }
    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, C.db.local.TABLE);
        return numRows;
    }
    public ArrayList<String> getAllPushCodes(){
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery(("select * from " + C.db.local.TABLE), null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(C.db.local.column.PUSH_CODE)));
            res.moveToNext();
        }
        return array_list;
    }
//
//    public void updateContact(Integer id, String col1, String col2){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues updateRow = new ContentValues();
//        updateRow.put(column.COL1, col1);
//        updateRow.put(column.COL2, col2);
//        db.update("contacts", updateRow, "id = ? ", new String[]{Integer.toString(id)});
//    }
    public void deletePushRowById(Integer id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(C.db.local.TABLE, "id = ?", new String[]{Integer.toString(id)});
    }
    public boolean codeIsNotUsed(String c){
        ArrayList<String> allCodes = getAllPushCodes();
        //Log.v(TAG, "All Codes Size:" + allCodes.size());
        if(allCodes.contains(c)){
            //Log.v(TAG, "Has code");
            return false;
        }else{
            Log.v(TAG, "Does not have code");
            return true;
        }
    }
    public void deleteAllRows(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(C.db.local.TABLE, null, null);
    }
}

