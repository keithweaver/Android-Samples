In the [build.gradle](https://github.com/kweaver00/Android-Samples/blob/master/Location/GetUserLocation/GetGPSCoord/app/build.gradle) file add:
```
compile 'com.android.support:appcompat-v7:22.+'
compile 'com.google.android.gms:play-services:6.+'
```
(The 7:22 <--- 22 is cause SDK 22)


In the [Android Manifest](https://github.com/kweaver00/Android-Samples/blob/master/Location/GetUserLocation/GetGPSCoord/app/src/main/AndroidManifest.xml):
```
<permission android:name="YOUR_PACKAGE_AND_APP_NAME.permission.MAPS_RECEIVE"
        android:protectionLevel="signature"/>
<uses-permission android:name="com.weaverprojects.gpstest.permission.MAPS_RECEIVE"/>
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
<uses-permission android:name="com.google.android.providers.gsf.permission.READ_GSERVICES"/>
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>
```

and still in the Android Manifest under the 
```
<application
```
tag and before the first
```
<activity
```
tag. Add the following:
```
<meta-data android:name="com.google.android.gms.version"
            android:value="@integer/google_play_services_version"/>
```

In your activity file you will set up a location listener that updates the location depending on a few parameters. This will not work with Android M (API 23), however there is a working example of [that](https://github.com/kweaver00/Android-Samples/blob/master/Location/GetLocationAndroidM/app/src/main/java/com/weaverprojects/getlocationandroidm/MainActivity.java).

Add implentations for the listeners:
```
public class MainActivity extends Activity implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{
```

Make the listeners global. It's so you can access them in the `onCreate`, `onDestroy`, etc.
```
private Location mLastLocation;
public LocationManager mLocationManager;
```

In the onCreate, initiate your listeners.
```
int LOCATION_REFRESH_TIME = 1000;
int LOCATION_REFRESH_DISTANCE = 5;

mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION REFRESH_TIME, LOCATION_REFRESH_DISTANCE, mLocationListener);
```

Create the `mLocationListener`, outside of the onCreate:
```
private final LocationListener mLocationListener = new LocationListener() {
    @Override
    public void onLocationChanged(Location location) {
        //code
        System.out.println("onLocationChanged");
        mLastLocation = location;
		// mainLabel.setText("Latitude:" + String.valueOf(location.getLatitude()) + "\n" + "Longitude:" + String.valueOf(location.getLongitude()));
    }
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        System.out.println("onStatusChanged");
    }
    @Override
    public void onProviderEnabled(String provider) {
        System.out.println("onProviderEnabled");
    }
    @Override
    public void onProviderDisabled(String provider) {
        System.out.println("onProviderDisabled");
        //turns off gps services
    }
};
```
