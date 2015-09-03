In the build.gradle file add:
```
compile 'com.android.support:appcompat-v7:22.+'
compile 'com.google.android.gms:play-services:6.+'
```
(The 7:22 <--- 22 is cause SDK 22)


In the Android Manifest:
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
