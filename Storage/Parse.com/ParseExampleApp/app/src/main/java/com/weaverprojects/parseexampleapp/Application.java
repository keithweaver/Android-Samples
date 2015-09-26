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
            Parse.initialize(this, "x29Y0O6qp4RBjAExsvmYqjE8rFoW23l06EXgRCMm", "x0znRMyn9XimjPF4WGaoLmswxR77Hj3ugqFNSVPO");;
        } catch (Exception e) {

        }
    }
}
