package com.weaverprojects.alwaysrunninglocation;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by kweaver on 20/11/15.
 */
public class YourService extends Service implements LocationListener {
    LocationManager mLocationManager;

    public IBinder onBind(Intent intent) {
        // This won't be a bound service, so simply return null
        Log.v("WEAVER_", "Service_OnBind...");
        return null;
    }

    @Override
    public void onCreate() {
        // This will be called when your Service is created for the first time
        // Just do any operations you need in this method.
        Log.v("WEAVER_", "Service_OnCreate...");

        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1,
                1, this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.v("WEAVER_","Service_OnDestroy");
        mLocationManager.removeUpdates(this);
        mLocationManager = null;
    }
    @Override
    public void onLocationChanged(Location location) {
        Log.v("WEAVER_","Service_Location Change");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}