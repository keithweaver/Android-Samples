package com.weaverprojects.compasstest4;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.location.LocationServices;


public class MainActivity extends Activity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{
    private static final String TAG = "CompassActivity";

    private Compass compass;

    private static final int REQUEST_RESOLVE_ERROR = 1001;
    private boolean mResolvingError = false;
    public LocationManager mLocationManager;
    public static Location current_location;

    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GoogleApiClient client = new GoogleApiClient.Builder(this)
                .addApi(Drive.API)
                .addScope(Drive.SCOPE_FILE)
                .addOnConnectionFailedListener(this)
                .addConnectionCallbacks(this)
                .build();

        int LOCATION_REFRESH_TIME = 1000;
        int LOCATION_REFRESH_DISTANCE = 5;

        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        //try {
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_REFRESH_TIME, LOCATION_REFRESH_DISTANCE, mLocationListener);



        compass = new Compass(this);
        compass.arrowView = (ImageView) findViewById(R.id.main_image_arrow);
    }
    public void onStart() {
        super.onStart();
        Log.d(TAG, "start compass");
        compass.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        compass.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        compass.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "stop compass");
        compass.stop();
    }

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
    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            current_location = location;
            updateArrow();
        };

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
    /* ------- END OF LOCATION UPDATE ------ */
    public void updateArrow(){

        String current_latitude_str = String.valueOf(current_location.getLatitude());
        String current_longitude_str = String.valueOf(current_location.getLongitude());

        double current_latitude = current_location.getLatitude();
        double current_longitude = current_location.getLongitude();

        //double other_latitude = 44.23082952;
        double other_latitude = 44.23382952;
        double other_longitude = -76.49879964;


        double  lineOne = other_longitude - current_longitude;
        double lineTwo = other_latitude - current_latitude;

        double angle = Math.toDegrees(Math.atan(lineOne/lineTwo));
        Log.v("ANGLE1:",String.valueOf(angle));


        //ImageView arrow_image = (ImageView) findViewById(R.id.arrow_image);

        //TextView distance_label = (TextView) findViewById(R.id.distance_label);

        String label = String.valueOf(angle) + " - C:" + String.valueOf(current_longitude) + " - O:" + String.valueOf(other_longitude);
        //distance_label.setText(label);

        float temp = (float) angle;

        Log.v("ANGLE:",String.valueOf(angle));
        Log.d("Current Longitude:", String.valueOf(current_latitude));
        Log.d("Current Latitude:", String.valueOf(current_latitude));
        Log.d("Other Longitude:", String.valueOf(other_longitude));
        Log.d("Other Latitude:", String.valueOf(other_latitude));

        if(other_latitude < current_latitude){
            //point down

        }else{
            //point up
        }
        //arrow_image.setRotation(temp);
    }

    protected void startLocationUpdates(){}
    /* -------- AUTO GENERATED --------- */
    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }



    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    */




    public void onLocationChanged(Location location) {
        updateLocationUI(location.toString());
    }


    public void onStatusChanged(String provider, int status, Bundle extras) {

    }


    public void onProviderEnabled(String provider) {

    }


    public void onProviderDisabled(String provider) {

    }
    public void updateLocationUI(String currentLocation){
        //TextView location_text = (TextView) findViewById(R.id.location_text);
        //System.out.println(location_text);
        //location_text.setText(currentLocation);
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if(mResolvingError){
            return;
        }else if(connectionResult.hasResolution()){
            try{
                mResolvingError = true;
                connectionResult.startResolutionForResult(this, REQUEST_RESOLVE_ERROR);
            }catch (Exception e){
                //mGoogleApiClient.connect();
            }
        }else{

        }
    }
    protected synchronized void buildGoogleApiClient(){
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }
}
