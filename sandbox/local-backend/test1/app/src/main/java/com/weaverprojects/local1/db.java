package com.weaverprojects.local1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Weaver on 15-06-26.
 */
public class db extends SQLiteOpenHelper{
    public static String DB_NAME = "testing";
    public static class column{
        public static String KEY = "id";
        public static String COL1 = "col1";
        public static String COL2 = "col2";
    }


    public db(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table contacts " +
                        "(" +
                        column.KEY + " integer primary key, " +
                        column.COL1 + " text," +
                        column.COL2 + " text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }
    public void insert(String col1, String col2){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues tableRow = new ContentValues();
        tableRow.put(column.COL1, col1);
        tableRow.put(column.COL2, col2);
        db.insert("contacts", null, tableRow);
    }
    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, "contacts");
        return numRows;
    }
    public ArrayList<String> getAllCotacts(){
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(column.COL2)));
            res.moveToNext();
        }
        return array_list;
    }
    public void updateContact(Integer id, String col1, String col2){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues updateRow = new ContentValues();
        updateRow.put(column.COL1, col1);
        updateRow.put(column.COL2, col2);
        db.update("contacts", updateRow, "id = ? ", new String[]{Integer.toString(id)});
    }
    public void deleteContact(Integer id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("contacts", "id = ?", new String[]{Integer.toString(id)});
    }

}
