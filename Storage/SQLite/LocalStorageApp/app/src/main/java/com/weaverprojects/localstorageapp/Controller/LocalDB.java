package com.weaverprojects.localstorageapp.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Keith on 2015-09-25.
 */
public class LocalDB extends SQLiteOpenHelper {
    public LocalDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + C.myTable.TABLE_NAME +
                    "(" +
                    C.myTable.columns.KEY + " integer primary key, " +
                    C.myTable.columns.FIRST_NAME + " text, " +
                    C.myTable.columns.LAST_NAME + " text, " +
                    C.myTable.columns.POSITION + " text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + C.myTable.TABLE_NAME);
        onCreate(db);
    }
    public void insertRow(String firstName, String lastName, String position){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues tableRow =new ContentValues();
        tableRow.put(C.myTable.columns.FIRST_NAME, firstName);
        tableRow.put(C.myTable.columns.LAST_NAME, lastName);
        tableRow.put(C.myTable.columns.POSITION, position);
        db.insert(C.myTable.TABLE_NAME, null, tableRow);
    }
    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numberOfRows = (int) DatabaseUtils.queryNumEntries(db, C.myTable.TABLE_NAME);
        return numberOfRows;
    }
    public ArrayList<String> getAllFullNames(){
        ArrayList<String> listOfNames = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + C.myTable.TABLE_NAME, null);
        res.moveToFirst();
        while(res.isAfterLast() == false){
            listOfNames.add(
                    res.getString(res.getColumnIndex(C.myTable.columns.FIRST_NAME)) + " " +
                            res.getString(res.getColumnIndex(C.myTable.columns.LAST_NAME)));
            res.moveToNext();
        }
        return listOfNames;
    }
    public void updateRow(Integer id, String firstName, String lastName, String position){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues updateRow = new ContentValues();
        updateRow.put(C.myTable.columns.FIRST_NAME, firstName);
        updateRow.put(C.myTable.columns.LAST_NAME, lastName);
        updateRow.put(C.myTable.columns.POSITION, position);
        db.update(C.myTable.TABLE_NAME, updateRow,"id = ?", new String[]{Integer.toString(id)});
    }
    public void deleteRow(Integer id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(C.myTable.TABLE_NAME, "id = ?", new String[]{Integer.toString(id)});
    }
}
