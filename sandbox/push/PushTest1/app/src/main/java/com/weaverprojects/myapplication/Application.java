package com.weaverprojects.myapplication;

import android.util.Log;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParsePush;

/**
 * Created by Weaver on 15-05-20.
 */
public class Application extends android.app.Application {
    public void onCreate() {

        try {
            Parse.enableLocalDatastore(this);
            Parse.initialize(this, "5xt0R3qzDv5LSX168n8uQxekQwoqyLxypd55G5th", "1VmvjhPQrrc5LIp2guPKHPYN6wRJ6aVlSqZB08Kx");
            Log.e("----------------", "CONNECTED TO PARSE -----------------");




        } catch (Exception e) {
            Log.e("Error-:", "Unable to connect");
        }

        try{
            /*
            ParseACL defaultACL = new ParseACL();
            defaultACL.setPublicReadAccess(true);//remove if want to be private
            ParseACL.setDefaultACL(defaultACL, true);

            //enable to receive push
            PushService.setDefaultPushCallback(this, RespondToActivity.class);
            ParseInstallation pi = ParseInstallation.getCurrentInstallation();


            //Register a channel to test push cahnnels
            Context mContext = this.getApplicationContext();
            PushService.subscribe(mContext, "usr1", RespondToActivity.class);

            pi.saveEventually();
            */
            //PushService.setDefaultPushCallback(this, RespondToActivity.class);
            ParsePush.subscribeInBackground("usr1");
            ParseInstallation.getCurrentInstallation().saveInBackground();
        }catch(Exception e){
            Log.e("ERROR:","");
        }
    }
}
