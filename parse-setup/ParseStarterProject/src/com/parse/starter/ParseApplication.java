package com.parse.starter;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseCrashReporting;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class ParseApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();

    // Initialize Crash Reporting.
    ParseCrashReporting.enable(this);

    // Enable Local Datastore.
    Parse.enableLocalDatastore(this);



    // Add your initialization code here
    Parse.initialize(this, "APP_ID", "CLIENT_ID");


    ParseUser.enableAutomaticUser();
    ParseUser.getCurrentUser().saveInBackground();
    ParseACL defaultACL = new ParseACL();
    // Optionally enable public read access.
     //defaultACL.setPublicReadAccess(true);
    ParseACL.setDefaultACL(defaultACL, true);
      ParseObject testObject = new ParseObject("TestObject");
      testObject.put("itemName","item");
      testObject.saveInBackground();

  }
}
