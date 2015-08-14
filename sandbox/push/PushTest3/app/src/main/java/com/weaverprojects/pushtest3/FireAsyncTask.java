package com.weaverprojects.pushtest3;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

/**
 * Created by kweaver on 11/08/15.
 */
public class FireAsyncTask extends AsyncTask<String, Void, String> {
    Context context;
    public FireAsyncTask(Context c){
        context = c;
    }

    @Override
    protected String doInBackground(String... params) {
        String msg = "";
        try {
            Bundle data = new Bundle();
            data.putString("my_message", "Hello World");
            data.putString("my_action",
                    "com.google.android.gcm.demo.app.ECHO_NOW");
            String id = Integer.toString(MainActivity.msgId.incrementAndGet());
            MainActivity.gcm.send(Constants.SENDER_ID + "@gcm.googleapis.com", id, data);
            msg = "Sent message";
        } catch (IOException ex) {
            msg = "Error :" + ex.getMessage();
        }
        Log.v("WEAVER", "Msg:[" + msg + "]");
        return msg;
    }
}
