package com.weaverprojects.parseexampleapp;

import android.util.Log;

import com.parse.Parse;

/**
 * Created by Keith on 2015-09-26.
 */
public class Application extends android.app.Application {
    public void onCreate() {
        super.onCreate();
        Log.v("APP IS STARTING", "...");
        // Initialize the Parse SDK.
        try {
            Parse.initialize(this, "YOUR_APPLICATION_ID", "YOUR_CLIENT_ID");;
        } catch (Exception e) {

        }
    }
}
