package com.weaverprojects.compasstest;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends Activity implements SensorEventListener{
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private Sensor geoSensor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v("Start:"," App is starting up =--------");

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        geoSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);



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

    @Override
    public void onSensorChanged(SensorEvent event) {
        float azimuth_angle = event.values[0];
        float pitch_angle = event.values[1];
        float roll_angele = event.values[2];

        //Log.v("INFO:",String.valueOf(azimuth_angle));
        //Log.v("INFO:",String.valueOf(pitch_angle));
        //Log.v("INFO:",String.valueOf(roll_angele));
        double new_angle = Math.toDegrees(azimuth_angle);
        Log.v("AZIMUTH:",String.valueOf(new_angle));

        TextView main_label = (TextView) findViewById(R.id.main_label);
        TextView label2 = (TextView) findViewById(R.id.label2);
        TextView label3 = (TextView) findViewById(R.id.label3);
        main_label.setText(String.valueOf(new_angle));
        label2.setText(String.valueOf(pitch_angle));
        label3.setText(String.valueOf(roll_angele));
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
