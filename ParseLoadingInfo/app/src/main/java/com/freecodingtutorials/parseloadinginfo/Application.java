package com.freecodingtutorials.parseloadinginfo;

import android.util.Log;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.PushService;

/**
 * Created by Weaver on 15-03-24.
 */
public class Application extends android.app.Application {
    ParseInstallation pi;
    public void onCreate() {
        super.onCreate();
        Log.e("APP IS STARTING", "...");
        // Initialize the Parse SDK.
        try {
            Parse.initialize(this, "ACnXHKlS8m40Xppjus7W21FJes5Fjw7cKdxA2sBX", "XxPYpJBO1XC3yqMs0l1FAUvXpM3wYqN8E7Gjf2Pq");

            // Specify an Activity to handle all pushes by default.
            //PushService.setDefaultPushCallback(this, MainActivity.class);
            //PushService.subscribe(this, "giants", MainActivity.class);
            //ParseInstallation.getCurrentInstallation().saveInBackground();
            PushService.setDefaultPushCallback(this, MainActivity.class);
            pi = ParseInstallation.getCurrentInstallation();

            //Register a channel to test push channels
            //Context ctx = this.getApplicationContext();
            //PushService.subscribe(ctx, "ch1", MainActivity.class);


            pi.saveEventually();
        }catch(Exception e){
            Log.e("ERROR:",e.toString());
        }
    }
}
