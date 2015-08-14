package com.parse.starter;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.ParseUser;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


        try {
            Parse.enableLocalDatastore(this);
            Parse.initialize(this, "5xt0R3qzDv5LSX168n8uQxekQwoqyLxypd55G5th", "1VmvjhPQrrc5LIp2guPKHPYN6wRJ6aVlSqZB08Kx");
            Log.e("----------------", "CONNECTED TO PARSE -----------------");


        } catch (Exception e) {
            Log.e("Error-:", "Unable to connect");
        }


        try {
            ParseUser.enableAutomaticUser();
            ParseACL defaultACL = new ParseACL();
            // Optionally enable public read access.
            // defaultACL.setPublicReadAccess(true);
            ParseACL.setDefaultACL(defaultACL, true);
        } catch (Exception e) {

        }

        try {
            ParsePush.subscribeInBackground("usr1");
            ParseInstallation.getCurrentInstallation().saveInBackground();
        } catch (Exception e) {

        }
    }
}
