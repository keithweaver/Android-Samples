package com.weaverprojects.shuttle.Controller.local;

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
public class SavedFriendsDataDB extends SQLiteOpenHelper {
    public SavedFriendsDataDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + C.local.saved_friends_data.TABLE_NAME +
                "(" +
                C.local.saved_friends_data.KEY + " integer primary key, " +
                C.local.saved_friends_data.USER_NAME + " text, " +
                C.local.saved_friends_data.NAME + " text" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + C.local.saved_friends_data.TABLE_NAME);
        onCreate(db);
    }
    public String getName(String u){
        String name = "";
        //rawQuery("SELECT * FROM ? where Category = ?", new String[] {tableName, categoryex});
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor res = db.rawQuery(
                    "SELECT * FROM " + C.local.saved_friends_data.TABLE_NAME + " WHERE " +
                            C.local.saved_friends_data.USER_NAME + "=?", new String[]{u});
            res.moveToFirst();
            while (res.isAfterLast() == false) {
                name = res.getString(res.getColumnIndex(C.local.saved_friends_data.NAME));
                res.moveToNext();
            }
        }catch (Exception e){

        }
        return name;
    }
}
