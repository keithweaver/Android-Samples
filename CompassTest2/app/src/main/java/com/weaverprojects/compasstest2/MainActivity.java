package com.weaverprojects.compasstest2;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.location.LocationServices;


public class MainActivity extends Activity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, SensorEventListener {
    private static final int REQUEST_RESOLVE_ERROR = 1001;
    private boolean mResolvingError = false;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    public LocationManager mLocationManager;
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private Sensor geoSensor;
    private Location targetLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_main);
        }catch(Exception e){
            Log.e("ERROR:","001");
        }
        try {
            GoogleApiClient client = new GoogleApiClient.Builder(this)
                    .addApi(Drive.API)
                    .addScope(Drive.SCOPE_FILE)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .build();

            int LOCATION_REFRESH_TIME = 1000;
            int LOCATION_REFRESH_DISTANCE = 5;

            mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_REFRESH_TIME, LOCATION_REFRESH_DISTANCE, mLocationListener);

            mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
            mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
            geoSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        }catch(Exception e){
            Log.e("ERROR:","002");
        }
    }
    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            //code
            Log.v("onLocationChanged","LOCATION HAS BEEN CHANGED");

            mLastLocation = location;
            String latitude = String.valueOf(mLastLocation.getLatitude());
            String longitude = String.valueOf(mLastLocation.getLongitude());
            //updateLocationUI("[" + latitude + ", " + longitude + "]");

            double other_latitude = 44.23082952;
            double other_longitude = -76.49879964;

            targetLocation = new Location("");//provider name is unecessary
            targetLocation.setLatitude(other_latitude);//your coords of course
            targetLocation.setLongitude(other_longitude);
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
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
    protected synchronized void buildGoogleApiClient(){
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float azimuth_angle = event.values[0];
        float pitch_angle = event.values[1];
        float roll_angle = event.values[2];
        double azimuth = (double) Math.toDegrees(azimuth_angle);

        try{
            if(mLastLocation == null){
                //Log.v("LOCATION:","ITS NULL");
            }else{
                Log.v("LOCATION:","ITS NOT NULL");
            }
        }catch(Exception e){
            Log.e("ERROR:","004");
        }

        try{



            if(mLastLocation != null) {
                double myLocation_latitude_double = mLastLocation.getLatitude();
                double myLocation_longitude_double = mLastLocation.getLongitude();
                double myLocation_altitude_double = mLastLocation.getAltitude();

                Log.v("LATITUDE DOUBLE:", String.valueOf(myLocation_latitude_double));
                Log.v("LONGITUDE DOUBLE:", String.valueOf(myLocation_longitude_double));
                Log.v("ALTITUDE DOUBLE:", String.valueOf(myLocation_altitude_double));
                //if(myLocation_altitude != 0 && myLocation_latitude && != 0)
                /*
                    GeomagneticField geoField = new GeomagneticField(
                            (float) mLastLocation.getLatitude(),
                            (float) mLastLocation.getLongitude(),
                            (float) mLastLocation.getAltitude(),
                            System.currentTimeMillis());
                    try {
                        azimuth += geoField.getDeclination(); // converts magnetic north into true north
                        float bearing = mLastLocation.bearingTo(targetLocation); // (it's already in degrees)
                        double direction = azimuth - bearing;

                        TextView main_label = (TextView) findViewById(R.id.main_label);
                        main_label.setText(String.valueOf(direction));
                    }catch (Exception e){
                        Log.e("ERROR:","004");
                    }
                */
            }
        }catch(Exception e){
            Log.e("ERROR:","003");
        }



    }
    public void onResume(){
        super.onResume();
        mSensorManager.registerListener(this, geoSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
    public void onPause(){
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
