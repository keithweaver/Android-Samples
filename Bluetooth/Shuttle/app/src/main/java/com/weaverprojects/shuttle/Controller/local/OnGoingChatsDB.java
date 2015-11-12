package com.weaverprojects.shuttle.Controller.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.weaverprojects.shuttle.Controller.C;
import com.weaverprojects.shuttle.Model.ConvoListItem;

import java.util.ArrayList;

/**
 * Created by Keith on 2015-10-21.
 */
public class OnGoingChatsDB extends SQLiteOpenHelper {
    public OnGoingChatsDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + C.local.on_going_chats.TABLE_NAME +
                "(" +
                C.local.on_going_chats.KEY + " integer primary key, " +
                C.local.on_going_chats.USER_NAME + " text, " +
                C.local.on_going_chats.LAST_MESSAGE + " text, " +
                C.local.on_going_chats.IMG_LINK + " text, " +
                C.local.on_going_chats.DEVICE_ID + " text" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + C.local.saved_friends_data.TABLE_NAME);
        onCreate(db);
    }
    public ArrayList<ConvoListItem> getAllConvos(){
        ArrayList<ConvoListItem> listOfChats = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + C.local.on_going_chats.TABLE_NAME, null);
        res.moveToFirst();
        while(res.isAfterLast() == false){
//            listOfNames.add(
//                    res.getString(res.getColumnIndex(C.myTable.columns.FIRST_NAME)) + " " +
//                            res.getString(res.getColumnIndex(C.myTable.columns.LAST_NAME)));
            listOfChats.add(new ConvoListItem(
                    res.getString(res.getColumnIndex(C.local.on_going_chats.DEVICE_ID)),
                    res.getString(res.getColumnIndex(C.local.on_going_chats.USER_NAME)),
                    res.getString(res.getColumnIndex(C.local.on_going_chats.LAST_MESSAGE)),
                    res.getString(res.getColumnIndex(C.local.on_going_chats.IMG_LINK))
            ));
            res.moveToNext();
        }
        return listOfChats;
    }
    public void insertRow(String userName, String lastMessage, String imgLink, String deviceId){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues tableRow =new ContentValues();
        tableRow.put(C.local.on_going_chats.USER_NAME, userName);
        tableRow.put(C.local.on_going_chats.LAST_MESSAGE, lastMessage);
        tableRow.put(C.local.on_going_chats.IMG_LINK, imgLink);
        tableRow.put(C.local.on_going_chats.DEVICE_ID, deviceId);
        db.insert(C.local.on_going_chats.TABLE_NAME, null, tableRow);
    }
}
