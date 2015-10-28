package com.weaverprojects.shuttle.Controller.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.weaverprojects.shuttle.Controller.C;

/**
 * Created by Keith on 2015-10-25.
 */
public class ChatMessageHistoryDB extends SQLiteOpenHelper {
    public ChatMessageHistoryDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + C.local.chat_message_history.TABLE_NAME +
            " (" +
                C.local.chat_message_history.KEY + " integer primary key, " +
                C.local.chat_message_history.USER_CHATTING_WITH + " text, " +
                C.local.chat_message_history.TO_CHAT  + " text, " +
                C.local.chat_message_history.MESSAGE + " text, " +
                C.local.chat_message_history.TIME + " text" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + C.local.chat_message_history.TABLE_NAME);
        onCreate(db);
    }
}
